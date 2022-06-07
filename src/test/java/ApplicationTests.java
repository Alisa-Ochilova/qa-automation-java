import com.tcs.edu.decorator.OrderedDistinctedMessageService;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.domain.MessageService;
import com.tcs.edu.domain.LogException;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.repository.HashMapMessageRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static com.tcs.edu.enums.Doubling.DISTINCT;
import static com.tcs.edu.enums.Doubling.DOUBLES;
import static com.tcs.edu.enums.MessageOrder.ASC;
import static com.tcs.edu.enums.MessageOrder.DESC;
import static com.tcs.edu.enums.Severity.MAJOR;
import static com.tcs.edu.enums.Severity.MINOR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ApplicationTests {

    MessageService service;
    Message message1;
    Message message2;
    Message message3;
    Message message4;

    @BeforeEach
    public void setUp() {
        service = new OrderedDistinctedMessageService(
                new HashMapMessageRepository(),
                new TimestampMessageDecorator()
        );
        message1 = new Message(null, null);
        message2 = new Message(MINOR, "Hello world 2!");
        message3 = new Message(MAJOR, "Hello world 3!");
        message4 = new Message(MAJOR, "Hello world 3!");
    }


    @Nested
    @DisplayName("Бизнес-логика")
    class FindOne {
        @Test
        @DisplayName("Декорирование сообщение")
        public void checkDecorating() {
            service.process(message2, message3);
            assertThat(message2.getBody(), is("1 " + LocalDate.now() + " Hello world 2!  ()"));
            assertThat(message3.getBody(), is("2 " + LocalDate.now() + " Hello world 3!  (!!!) \r\n---"));
        }

        @Test
        @DisplayName("Наличие тела и северити")
        public void checkMessage() {
            assertThat(message2.getBody(), is("Hello world 2!"));
            assertThat(message3.getLevel(), is(MAJOR));
        }


        @Test
        @DisplayName("Последовательный порядок сохранения")
        public void checkOrderingASC() {
            service.process(ASC, message2, message3);
            assertThat(service.findAll().stream().findFirst().get(), is(message2));
        }

        @Test
        @DisplayName("Обратный порядок сохранения")
        public void checkOrderingDESC() {
            service.process(DESC, message2, message3);
            assertThat(service.findAll().stream().findFirst().get(), is(message3));
        }

        @Test
        @DisplayName("Сохранения дублей")
        public void checkDoublingDOUBLES() {
            service.process(ASC, DOUBLES, message2, message3, message3);
            assertThat(service.findAll().size(), is(3));
        }

        @Test
        @DisplayName("Удаления дублей")
        public void checkDoublingDISTINCT() {
            service.process(ASC, DISTINCT, message2, message3, message3);
            assertThat(service.findAll().size(), is(2));
        }
    }


    @Nested
    @DisplayName("Проверка исключений")
    class FindTwo {
        @Test
        @DisplayName("Отсутсвует тело сообщение")
        public void checkNullBody() {
            LogException e = Assertions.assertThrows(LogException.class, () -> service.process(message1, message2));
            assertThat(e.getMessage(), is("Message body cannot be null"));
        }

        @Test
        @DisplayName("Отсутсвует параметр порядка вывода")
        public void checkNullOrder() {
            LogException e = Assertions.assertThrows(LogException.class, () -> service.process(null, Doubling.DISTINCT, message2, message3, message4));
            assertThat(e.getMessage(), is("Order cannot be null"));
        }

        @Test
        @DisplayName("Отсутсвует параметр проверки на дубли")
        public void checkNullDistinct() {
            LogException e = Assertions.assertThrows(LogException.class, () -> service.process(MessageOrder.ASC, (Doubling) null, message2, message3, message4));
            assertThat(e.getMessage(), is("Doubling cannot be null"));
        }
    }
}

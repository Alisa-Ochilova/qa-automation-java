package com.tcs.edu.decorator;

import com.tcs.edu.domain.*;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.repository.MessageRepository;


import java.util.Collection;
import java.util.UUID;

import static com.tcs.edu.decorator.DoubleCheck.*;
import static com.tcs.edu.enums.MessageOrder.*;
import static com.tcs.edu.enums.Doubling.*;

public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {

    private MessageRepository repository;
    private MessageDecorator messageDecorator;

    /**
     * Конструктор, принимающий параметры принтера и декоратора
     */
    public OrderedDistinctedMessageService(MessageRepository repository, MessageDecorator messageDecorator) {
        this.repository = repository;
        this.messageDecorator = messageDecorator;
    }

    /**
     * Проверка входных параметровров на null
     */

    public void process(Message message, Message... messages) throws LogException {
        try {
            super.isArgsValid(message);
            repository.create(messageDecorator.decorate(message));

            for (Message current : messages) {
                repository.create(messageDecorator.decorate(current));
            }
        } catch (IllegalArgumentException e) {
            throw new LogException(e.getMessage(), e);
        }
    }

    /**
     * Перегруженный метод, определяющий порядок вывода сообщений для последовательности строковых параметров vararg
     */

    public void process(MessageOrder order, Message message, Message... messages) throws LogException{

        try {
            super.isArgsValid(order, message);
            if (order == DESC) {

                for (int current = messages.length - 1; current >= 0; current--) {
                    repository.create(messageDecorator.decorate(messages[current]));
                }
                repository.create(messageDecorator.decorate(message));

            } else if (order == ASC) process(message, messages);
        } catch (IllegalArgumentException e) {
            throw new LogException(e.getMessage(), e);
        }
    }

    /**
     * Перегруженный метод, определяющий характер дублирования значений сообщений последовательности строковых параметров
     */

    public void process(MessageOrder order, Doubling doubling, Message message, Message... messages) throws LogException{

        try {
            super.isArgsValid(order, doubling, message);
            if (doubling == DISTINCT) {
                process(order, message, getArrayWithoutDoubles(messages));
            } else if (doubling == DOUBLES) {
                process(order, message, messages);
            }
        } catch (IllegalArgumentException e) {
            throw new LogException(e.getMessage(), e);
        }

    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return repository.findByPrimaryKey(key);
    }

    @Override
    public Collection<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return repository.findBySeverity(by);
    }
}

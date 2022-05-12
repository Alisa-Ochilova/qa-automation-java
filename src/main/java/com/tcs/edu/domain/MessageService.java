package com.tcs.edu.domain;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;

public interface MessageService {
    void process(MessageOrder order, Doubling doubling, Message message, Message... messages);

    void process(MessageOrder order, Message message, Message... messages);

    void process(Message message, Message... messages);
}

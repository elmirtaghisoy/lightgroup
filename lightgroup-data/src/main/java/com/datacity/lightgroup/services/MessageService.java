package com.datacity.lightgroup.services;

import com.datacity.lightgroup.commands.MessageCommand;
import com.datacity.lightgroup.model.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageCommand messageCommand);

    List<Message> getAllMessage(int status);

    MessageCommand findMessageById(Long id);

    void deleteById(String id);
}

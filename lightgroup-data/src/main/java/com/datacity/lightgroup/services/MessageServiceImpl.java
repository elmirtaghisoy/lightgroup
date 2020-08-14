package com.datacity.lightgroup.services;

import com.datacity.lightgroup.commands.MessageCommand;
import com.datacity.lightgroup.converters.CommandToMessage;
import com.datacity.lightgroup.converters.MessageToCommand;
import com.datacity.lightgroup.model.Message;
import com.datacity.lightgroup.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageToCommand messageToCommand;
    private final CommandToMessage commandToMessage;

    public MessageServiceImpl(MessageRepository messageRepository, MessageToCommand messageToCommand, CommandToMessage commandToMessage) {
        this.messageRepository = messageRepository;
        this.messageToCommand = messageToCommand;
        this.commandToMessage = commandToMessage;
    }

    @Override
    public void sendMessage(MessageCommand messageCommand) {
        messageRepository.save( commandToMessage.convert( messageCommand ) );
    }

    @Override
    public List<Message> getAllMessage(int status) {
        switch (status) {
            case 1:
                return messageRepository.findAllByStatus( 1 );
            case 2:
                return messageRepository.findAllByStatus( 2 );
            default:
                return messageRepository.findAll();
        }
    }

    @Override
    public MessageCommand findMessageById(Long id) {
        return messageToCommand.convert( messageRepository.getOne( id ) );
    }

    @Override
    public void deleteById(String id) {
        messageRepository.deleteById( Long.valueOf( id ) );
    }

}

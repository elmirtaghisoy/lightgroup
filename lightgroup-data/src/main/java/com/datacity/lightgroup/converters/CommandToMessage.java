package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.MessageCommand;
import com.datacity.lightgroup.model.Message;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToMessage implements Converter<MessageCommand, Message> {

    private final CommandToBusinessType businessTypeConverter;

    public CommandToMessage(CommandToBusinessType businessTypeConverter) {
        this.businessTypeConverter = businessTypeConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Message convert(MessageCommand source) {
        if (source == null) {
            return null;
        }
        final Message message = new Message();
        message.setId( source.getId() );
        message.setName( source.getName() );
        message.setSurname( source.getSurname() );
        message.setBusinessName( source.getBusinessName() );
        message.setBusinessType( businessTypeConverter.convert( source.getBusinessTypeCommand() ) );
        message.setEmail( source.getEmail() );
        message.setStatus( source.getStatus() );
        message.setPhone( source.getPhone() );
        message.setActive( source.isActive() );
        return message;
    }

}

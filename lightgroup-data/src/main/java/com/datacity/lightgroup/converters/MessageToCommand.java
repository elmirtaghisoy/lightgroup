package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.MessageCommand;
import com.datacity.lightgroup.model.Message;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MessageToCommand implements Converter<Message, MessageCommand> {

    private final BusinessTypeToCommand businessTypeConverter;

    public MessageToCommand(BusinessTypeToCommand businessTypeConverter) {
        this.businessTypeConverter = businessTypeConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public MessageCommand convert(Message source) {
        if (source == null) {
            return null;
        }

        final MessageCommand messageCommand = new MessageCommand();
        messageCommand.setId( source.getId() );
        messageCommand.setName( source.getName() );
        messageCommand.setSurname( source.getSurname() );
        messageCommand.setBusinessName( source.getBusinessName() );
        messageCommand.setBusinessTypeCommand( businessTypeConverter.convert( source.getBusinessType() ) );
        messageCommand.setEmail( source.getEmail() );
        messageCommand.setStatus( source.getStatus() );
        messageCommand.setPhone( source.getPhone() );
        messageCommand.setActive( source.isActive() );
        return messageCommand;
    }

}

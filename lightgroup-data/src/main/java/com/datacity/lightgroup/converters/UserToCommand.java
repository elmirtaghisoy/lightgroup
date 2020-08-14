package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.UserCommand;
import com.datacity.lightgroup.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class UserToCommand implements Converter<User, UserCommand> {

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User source) {
        if (source == null) {
            return null;
        }

        final UserCommand userCommand = new UserCommand();
        userCommand.setUsername( source.getUsername() );
        userCommand.setEmail( source.getEmail() );
        userCommand.setPassword( source.getPassword() );
        userCommand.setToken( source.getToken() );
        userCommand.setActive( source.isActive() );
        userCommand.setId( source.getId() );
        return userCommand;
    }

}
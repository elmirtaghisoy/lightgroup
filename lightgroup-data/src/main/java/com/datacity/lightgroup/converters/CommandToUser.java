package com.datacity.lightgroup.converters;

import com.datacity.lightgroup.commands.UserCommand;
import com.datacity.lightgroup.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class CommandToUser implements Converter<UserCommand, User> {

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand source) {
        if (source == null) {
            return null;
        }

        final User user = new User();
        user.setUsername( source.getUsername() );
        user.setEmail( source.getEmail() );
        user.setPassword( source.getPassword() );
        user.setToken( source.getToken() );
        user.setActive( source.isActive() );
        user.setId( source.getId() );
        return user;
    }

}
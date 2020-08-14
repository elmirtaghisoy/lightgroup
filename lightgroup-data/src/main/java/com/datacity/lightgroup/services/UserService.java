package com.datacity.lightgroup.services;

import com.datacity.lightgroup.model.User;
import com.datacity.lightgroup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername( username );
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername( username );
            userBuilder.disabled( !user.isActive() );
            userBuilder.password( user.getPassword() );

            String[] authoritiesArr = {"ADMIN"};
            userBuilder.authorities( authoritiesArr );
        } else {
            throw new UsernameNotFoundException( "User not found !!!" );
        }
        return userBuilder.build();
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder( 8 );

    public void updatePassword(String oldPass, String newPass) {
        User user = userRepository.getOne( 1L );
        if (passwordEncoder.matches( oldPass, user.getPassword() )) {
            User newUser = new User();
            newUser.setId( 1L );
            newUser.setPassword( passwordEncoder.encode( newPass ) );
            newUser.setUsername( user.getUsername() );
            userRepository.save( newUser );
        } else {
            System.out.println( "err" );
        }
    }

    public void updateUsername(String oldUsername, String newUsername) {
        User user = userRepository.getOne( 1L );
        if (oldUsername.equals( user.getUsername() )) {
            User newUser = new User();
            newUser.setId( 1L );
            newUser.setPassword( user.getPassword() );
            newUser.setUsername( newUsername );
            userRepository.save( newUser );
        } else {
            System.out.println( "err" );
        }
    }
}

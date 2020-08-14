package com.datacity.lightgroup.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCommand extends BaseEntityCommand {
    private String username;
    private String password;
    private String email;
    private String token;
}

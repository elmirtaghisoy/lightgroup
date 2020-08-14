package com.datacity.lightgroup.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MessageCommand extends BaseEntityCommand{
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String businessName;
    private int status;
    private BusinessTypeCommand businessTypeCommand;
}

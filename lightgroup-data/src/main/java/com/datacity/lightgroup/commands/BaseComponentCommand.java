package com.datacity.lightgroup.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseComponentCommand extends BaseEntityCommand{
    private String title;
    private Byte[] img ;
}

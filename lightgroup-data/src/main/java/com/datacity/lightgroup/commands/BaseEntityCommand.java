package com.datacity.lightgroup.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityCommand {
    private Long id;
    private boolean active = true;
}

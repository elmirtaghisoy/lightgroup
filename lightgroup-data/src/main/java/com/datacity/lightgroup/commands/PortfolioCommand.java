package com.datacity.lightgroup.commands;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@Component
public class PortfolioCommand extends BaseComponentCommand{
    private String description;
}

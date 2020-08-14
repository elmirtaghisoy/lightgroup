package com.datacity.lightgroup.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Component
public class BusinessTypeCommand extends BaseEntityCommand {
    @NotBlank(message = "Biznes növü daxi edilməyib.")
    private String typeName;
}

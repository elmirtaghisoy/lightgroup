package com.datacity.lightgroup.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeaderCommand {
    private Long id;
    private String textHeader;
    private String aboutUs;
    private Byte[] leftImg;
    private Byte[] rightImg;
}

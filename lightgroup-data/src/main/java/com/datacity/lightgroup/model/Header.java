package com.datacity.lightgroup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "header")
public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String textHeader;
    @Lob
    private String aboutUs;
    @Lob
    private Byte[] leftImg;
    @Lob
    private Byte[] rightImg;
}

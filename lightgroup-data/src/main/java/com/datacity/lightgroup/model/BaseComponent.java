package com.datacity.lightgroup.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseComponent extends BaseEntity {

    public BaseComponent(Long id, boolean active, String title, Byte[] img) {
        super( id, active );
        this.title = title;
        this.img = img;
    }

    private String title;
    @Lob
    private Byte[] img;
}

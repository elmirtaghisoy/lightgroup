package com.datacity.lightgroup.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "portfolio")
@Entity
public class Portfolio extends BaseComponent {

    public Portfolio(Long id, boolean active, String title, Byte[] img, String description) {
        super( id, active, title, img );
        this.description = description;
    }

    private String description;
}

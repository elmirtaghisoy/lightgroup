package com.datacity.lightgroup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "services")
@Entity
@NoArgsConstructor
public class Service extends BaseComponent {
    public Service(Long id, boolean active, String title, Byte[] img) {
        super( id, active, title, img );
    }
}

package com.datacity.lightgroup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "business_types")
public class BusinessType extends BaseEntity {

    public BusinessType(Long id, boolean active, String typeName, List<Message> messages) {
        super( id, active );
        this.typeName = typeName;
        this.messages = messages;
    }

    private String typeName;

    @OneToMany(mappedBy = "businessType",cascade = CascadeType.ALL)
    private List<Message> messages;

}

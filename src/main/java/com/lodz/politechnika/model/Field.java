package com.lodz.politechnika.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Field {

    @Id
    @GeneratedValue
    private Long fieldId;
    private String name;
    private String city;
    private String street;
    private byte number;
    @OneToOne(mappedBy = "field")
    private Team team;
    @Version
    private int optimisticLock;
}

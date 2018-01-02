package com.lodz.politechnika.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue
    private Long teamId;
    private String name;
    @OneToOne(optional = false)
    private Field field;
    @OneToMany(mappedBy = "team")
    private List<FootballPlayer> players;
    @Version
    private int optimisticLock;
}

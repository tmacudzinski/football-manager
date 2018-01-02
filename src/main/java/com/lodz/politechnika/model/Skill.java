package com.lodz.politechnika.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Skill {

    @Id
    @GeneratedValue
    private Long skillId;
    private String name;
    @ManyToMany(mappedBy = "skills")
    private List<FootballPlayer> players;
    @Version
    private int optimisticLock;
}

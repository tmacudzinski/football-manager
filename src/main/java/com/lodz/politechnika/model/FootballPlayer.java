package com.lodz.politechnika.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
public class FootballPlayer {

    @Id
    @GeneratedValue
    private Long playerId;
    private String firstname;
    private String lastname;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;
    @ManyToMany
    @JoinTable(name = "Player_Skill",
            joinColumns = {
                    @JoinColumn(
                            name = "fk_player_id",
                            referencedColumnName = "playerId"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "fk_skill_id",
                            referencedColumnName = "skillId"
                    )
            }
    )
    private List<Skill> skills = Collections.emptyList();
    @Version
    private int optimisticLock;
}

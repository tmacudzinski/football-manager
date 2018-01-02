package com.lodz.politechnika.repository;

import com.lodz.politechnika.model.Team;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TeamRepository extends Repository<Team, Long> {

    void save(Team team);
    List<Team> findAll();
    Team findOne(Long teamId);
}

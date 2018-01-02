package com.lodz.politechnika.repository;

import com.lodz.politechnika.model.FootballPlayer;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FootballPlayerRepository extends Repository<FootballPlayer, Long> {

    void save(FootballPlayer player);
    List<FootballPlayer> findAll();
    FootballPlayer findOne(Long playerId);
}

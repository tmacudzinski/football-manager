package com.lodz.politechnika.repository;

import com.lodz.politechnika.model.Skill;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SkillRepository extends Repository<Skill, Long> {

    void save(Skill skill);
    List<Skill> findAll();
    Skill findOne(Long skillId);
}

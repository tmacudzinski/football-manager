package com.lodz.politechnika.repository;

import com.lodz.politechnika.model.Field;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FieldRepository extends Repository<Field, Long> {

    void save(Field field);
    List<Field> findAll();
    Field findOne(Long fieldId);
}

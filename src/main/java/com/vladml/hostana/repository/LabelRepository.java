package com.vladml.hostana.repository;


import com.vladml.hostana.model.Label;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabelRepository extends CrudRepository<Label, Long> {
        List<Label> findAllByOrderByNameAsc();
}

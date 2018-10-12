package com.vladml.hostana.labels;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabelRepository extends CrudRepository<Label, Long> {
        List<Label> findAllByOrderByNameAsc();
}

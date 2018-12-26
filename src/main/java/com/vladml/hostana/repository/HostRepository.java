package com.vladml.hostana.repository;


import com.vladml.hostana.model.Host;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface HostRepository extends CrudRepository<Host, Long> {
    Host findByHostName(String hostName);
    List<Host> findAllByOrderBySortOrder();
}
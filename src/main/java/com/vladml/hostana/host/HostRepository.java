package com.vladml.hostana.host;


import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface HostRepository extends CrudRepository<Host, Long> {
    Host findByHostName(String hostName);
    List<Host> findAllByOrderBySortOrder();
}
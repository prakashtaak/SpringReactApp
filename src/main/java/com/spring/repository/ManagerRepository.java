package com.spring.repository;

import com.spring.domain.Employee;
import com.spring.domain.Manager;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource(exported = false)
public interface ManagerRepository extends Repository<Manager,Long> {

    Manager save(Employee employee);

    Manager findByName(String name);

}

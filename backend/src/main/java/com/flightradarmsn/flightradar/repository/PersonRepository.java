package com.flightradarmsn.flightradar.repository;

import com.flightradarmsn.flightradar.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

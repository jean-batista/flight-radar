package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.PersonDTO;
import com.flightradarmsn.flightradar.model.entities.Person;
import com.flightradarmsn.flightradar.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonDTO save(PersonDTO person) {
        Person entity = ObjectMapper.parseObject(person, Person.class);
        return ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        Person entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        return ObjectMapper.parseObject(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        List<Person> list = repository.findAll();
        return ObjectMapper.parseListObjects(list, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        update(entity, person);
        return ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        repository.delete(entity);
    }

    private void update(Person entity, PersonDTO person) {
        entity.setName(person.getName());
        entity.setBirthDate(person.getBirthDate());
        entity.setEmail(person.getEmail());
    }

}

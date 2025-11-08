package com.flightradarmsn.flightradar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.AccountCredentials;
import com.flightradarmsn.flightradar.model.dto.PersonDTO;
import com.flightradarmsn.flightradar.model.dto.RegisterDTO;
import com.flightradarmsn.flightradar.model.dto.UserDTO;
import com.flightradarmsn.flightradar.model.entities.Person;
import com.flightradarmsn.flightradar.model.entities.User;
import com.flightradarmsn.flightradar.model.enums.Roles;
import com.flightradarmsn.flightradar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public UserDTO save(RegisterDTO register, PersonDTO person) {
        User entity = new User();
        entity.setUsername(register.getEmail());
        entity.setPassword(generateHashedPassword(register.getPassword()));
        entity.setRoles(List.of(Roles.USER));
        entity.setPerson(ObjectMapper.parseObject(person, Person.class));
        return ObjectMapper.parseObject(repository.save(entity), UserDTO.class);
    }

    public UserDTO findById(Long id) {
        User entity = repository.findById(id).orElseThrow();
        return ObjectMapper.parseObject(entity, UserDTO.class);
    }

    public UserDTO findByUsername(String username) {
        User entity = repository.findByUsername(username);
        return ObjectMapper.parseObject(entity, UserDTO.class);
    }

    public List<UserDTO> findAll() {
        List<UserDTO> list = new ArrayList<>();
        for(User user : repository.findAll()) {
            list.add(ObjectMapper.parseObject(user, UserDTO.class));
        }
        return list;
    }

    public UserDTO update(UserDTO user) {

        if(user == null) throw new RuntimeException();
        if(user.getUsername() == null || user.getUsername().isBlank()) throw new RuntimeException();
        if(user.getRoles() == null || user.getRoles().isEmpty()) throw new RuntimeException();

        User entity = repository.findByUsername(user.getUsername());
        entity.setUsername(user.getUsername());
        entity.setRoles(user.getRoles());

        return ObjectMapper.parseObject(repository.save(entity), UserDTO.class);
    }

    public void delete(Long id) {
        User entity = repository.findById(id).orElseThrow();
        repository.delete(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User " + username + " not found!");
        return user;
    }

    private String generateHashedPassword(String password) {
        PasswordEncoder pbkd2Enconder = new Pbkdf2PasswordEncoder(
                "",
                8,
                185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
        );
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkd2Enconder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        return passwordEncoder.encode(password);
    }
    
}

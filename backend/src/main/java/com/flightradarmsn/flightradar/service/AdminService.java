package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.exceptions.AuthenticationException;
import com.flightradarmsn.flightradar.exceptions.UserException;
import com.flightradarmsn.flightradar.model.dto.DeleteProfileDTO;
import com.flightradarmsn.flightradar.model.dto.ProfileMinDTO;
import com.flightradarmsn.flightradar.model.entities.Person;
import com.flightradarmsn.flightradar.model.entities.User;
import com.flightradarmsn.flightradar.model.enums.Roles;
import com.flightradarmsn.flightradar.repository.PersonRepository;
import com.flightradarmsn.flightradar.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void updateUserProfile(ProfileMinDTO profileDTO) {
        User userEntity = userRepository.findById(profileDTO.getUserId()).orElseThrow(
                () -> new UserException("Não foi possível encontrar um usuário com o id: " + profileDTO.getUserId())
        );
        Person personEntity = userEntity.getPerson();
        update(userEntity, personEntity, profileDTO);
        userRepository.save(userEntity);
        personRepository.save(personEntity);
    }

    public void deleteUser(DeleteProfileDTO profileDTO) {
        User contextUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(StringUtils.isBlank(profileDTO.getPassword())) throw new AuthenticationException("A senha está vazia!");
        if(!passwordEncoder.matches(profileDTO.getPassword(), contextUser.getPassword())) {
            throw new AuthenticationException("Senha incorreta!");
        }

        User user = userRepository.findById(profileDTO.getUserId()).orElseThrow(
                () -> new UserException("Usuário não encontrado")
        );

        if(user.getRoles().contains(Roles.ADMIN)) {
            throw new UserException("Não é possível excluir a conta de outro administrador!");
        }

        Person person = user.getPerson();

        userRepository.delete(user);
        personRepository.delete(person);
    }

    private void update(User entity, Person personEntity, ProfileMinDTO profileDTO) {
        entity.setUsername(profileDTO.getEmail());
        personEntity.setName(profileDTO.getName());
        personEntity.setEmail(profileDTO.getEmail());
    }

}

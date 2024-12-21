package com.backend.studycenter.common.controller.security;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.dto.person.PersonDTO;
import com.backend.studycenter.common.dto.security.RoleDTO;
import com.backend.studycenter.common.dto.security.UserDTO;
import com.backend.studycenter.common.dto.security.UserDTOcu;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.exception.UserNotFoundException;
import com.backend.studycenter.common.mapper.person.PersonMapper;
import com.backend.studycenter.common.mapper.security.RoleMapper;
import com.backend.studycenter.common.mapper.security.UserMapper;
import com.backend.studycenter.common.messaging.EmailService;
import com.backend.studycenter.common.model.security.User;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.person.PersonService;
import com.backend.studycenter.common.service.security.RoleService;
import com.backend.studycenter.common.service.security.UserService;
import com.backend.studycenter.configs.JwtGenerator;
import com.backend.studycenter.configs.rabbitmq.producer.PersonProducer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private PersonProducer personProducer;

    //
    @PostMapping("/api/v1/users/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            User user = userService.getUserByNameAndPassword(userDTO.getUsername(), userDTO.getPassword());
            if (user == null) {
                throw new UserNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(userDTO), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/api/v1/users")
    public ResponseEntity<ArrayList<UserDTO>> getUsers() {
        ArrayList<User> users = userService.getUsers();
        ArrayList<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(UserMapper.INSTANCE.toDTO(user));
        }
        if (userDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(userDTOs);
        }
    }


    @GetMapping(value = "/api/v1/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long userId) throws EntityNotFoundException {
        UserDTO userDTO = UserMapper.INSTANCE.toDTO(userService.getUserById(userId));
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userDTO);
        }
    }

    @PostMapping(value = "/api/v1/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> signupUser(@RequestBody UserDTOcu userDTOcu) {
        try {
            if (userService.getUserByName(userDTOcu.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            UserDTO userDTO = getUserDTO(userDTOcu);
            PersonDTO personDTO = PersonMapper.INSTANCE.toDTO(userDTO.getPerson());
            personProducer.sendMessage(personDTO);
            return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(userService.addUser(userDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @NotNull
    private UserDTO getUserDTO(UserDTOcu userDTOcu) throws EntityNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDTOcu.getId());
        userDTO.setUsername(userDTOcu.getUsername());
        userDTO.setPassword(userDTOcu.getPassword());
        userDTO.setActive(userDTOcu.getActive());
        if (userDTOcu.getRoleIds().length > 0) {
            List<RoleDTO> roleDTOlist = new ArrayList<>();
            for (Long i : userDTOcu.getRoleIds()) {
                roleDTOlist.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(i)));
            }
            userDTO.setRoleDTOs(roleDTOlist);
        }
        userDTO.setDataCreated(LocalDateTime.now());
        userDTO.setDataExpired(userDTOcu.getDataExpired());
        userDTO.setPerson(personService.getPersonById(userDTOcu.getPersonId()));
        return userDTO;
    }

    @PutMapping(value = "/api/v1/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTOcu userDTOcu, @PathVariable(name = "id") Long userId) throws EntityNotFoundException {
        try {
            UserDTO userDTO = UserMapper.INSTANCE.toDTO(userService.getUserById(userId));
            //TODO: Check new username for dublicate in DB
            userDTO.setUsername(userDTOcu.getUsername());
            userDTO.setPassword(userDTOcu.getPassword());
            userDTO.setActive(userDTOcu.getActive());
            if (userDTOcu.getRoleIds().length > 0) {
                List<RoleDTO> roleDTOlist = new ArrayList<>();
                for (Long i : userDTOcu.getRoleIds()) {
                    roleDTOlist.add(RoleMapper.INSTANCE.toDTO(roleService.getRoleById(i)));
                }
                userDTO.setRoleDTOs(roleDTOlist);
            }
            userDTO.setDataExpired(userDTOcu.getDataExpired());
            userDTO.setPerson(personService.getPersonById(userDTOcu.getPersonId()));
            return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(userService.updateUser(userDTO, userId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/api/v1/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long userId) throws EntityNotFoundException {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    //TODO: impl following

    @PostMapping(value = "/api/v1/users/{userId}/add-role/{roleId}")
    public ResponseEntity<UserDTO> addRoleToUser(@PathVariable(name = "userId") Long userId, @PathVariable(name = "roleId") Long roleId) {
        try {
//            return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(userService.addUser(userDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setActive(true);
        return ResponseEntity.ok(userDTO);
    }


}

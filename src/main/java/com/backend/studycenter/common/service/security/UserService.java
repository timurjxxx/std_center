package com.backend.studycenter.common.service.security;

import com.backend.studycenter.common.dto.security.UserDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.exception.UserNotFoundException;
import com.backend.studycenter.common.mapper.security.UserMapper;
import com.backend.studycenter.common.model.security.Role;
import com.backend.studycenter.common.model.security.User;
import com.backend.studycenter.common.repository.security.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long currentPersonId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        if (!user.isPresent()) {
            return null;
        } else {
            return user.get().getPerson().getId();
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        //String userRolesAsString = user.getRolesString();
        List<Role> userRoles = user.getRoles();
        List<GrantedAuthority> authorities = userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        if (passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        } else {
            throw new UserNotFoundException("Password incorrect!");
        }
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public User getUserById(Long userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id = " + userId));
        return user;
    }

    public User addUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toModel(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id = " + userId));
        userRepository.delete(user);
    }

    public User updateUser(UserDTO userDTO, Long userId) throws EntityNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id = " + userId));
        User updatedUser = UserMapper.INSTANCE.toModel(userDTO);
        updatedUser.setId(existingUser.getId());
        updatedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(updatedUser);
    }

    public void clear() {
        userRepository.deleteAll();
    }
}

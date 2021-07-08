package org.com.service.Impl;

import org.com.entity.User;
import org.com.repository.UserRepository;
import org.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository uRepository;

    @Override
    public User readByLogin(String login) {
        return uRepository.findByLogin(login);
    }

    @Override
    public User read(Long id) {
        return uRepository.findById(id).get();
    }

    @Override
    public List<User> readAll() {
        return uRepository.findAll();
    }

    @Override
    public void save(User entity) {
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        uRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        uRepository.deleteById(id);
    }

    @Override
    public void edit(User entity) {
        User user = uRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        user.setLogin(entity.getLogin() != null ? entity.getLogin() : user.getLogin());
        user.setPassword(entity.getPassword() != null ? new BCryptPasswordEncoder().encode(entity.getPassword()) : user.getPassword());
        user.setAuthority(entity.getAuthority() != null ? entity.getAuthority() : user.getAuthority());
        uRepository.save(user);
    }
}

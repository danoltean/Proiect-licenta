package com.dan.ticketing.service;

import com.dan.ticketing.models.User;
import com.dan.ticketing.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @PostConstruct
    void test (){
     /*   // add mock data
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        User user = new User("admin", "admin", b.encode("admin"), "admin","admin");
        User user1 = new User("operator", "operator", b.encode("operator"), "operator","operator");
        User user2 = new User("resolver", "resolver", b.encode("resolver"), "resolver","resolver");
       // User user1 = new User("Pop", "Gheorghe", "123", "salahor");

        userRepo.save(user);
        userRepo.save(user1);
        userRepo.save(user2);*/
    }

    BCryptPasswordEncoder b = new BCryptPasswordEncoder();
    public void save(User user) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        user.setPass(b.encode(user.getPass()));
        userRepo.save(user); }

    public List<User> getAllUsers(){ return userRepo.findAll(); }

    public void delete(Integer id) { userRepo.deleteById(id); }

    public User getUserByUsername(String firstname) {
        return userRepo.findByUsername(firstname);
    }
}

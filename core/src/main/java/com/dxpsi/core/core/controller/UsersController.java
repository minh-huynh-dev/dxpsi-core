package com.dxpsi.core.core.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxpsi.core.core.Exception.ResourceNotFoundException;
import com.dxpsi.core.core.model.Users;
import com.dxpsi.core.core.repository.UsersRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/v1/")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/create") //Hint: requestmethod prevents cors. i think `
    public void createUser(@RequestBody Users user) {
        usersRepository.insert(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public void deleteUser(@PathVariable String id) {
        usersRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Users> listUsers() {
        return usersRepository.findAll();
    }
    
    @RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users userDetails,@PathVariable String id){   
        Users user = usersRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Server Side Update User Error"));

        user.setAge(userDetails.getAge());
        user.setFullName(userDetails.getFullName());
        user.setNickName(userDetails.getNickName());
        user.setPassword(userDetails.getPassword());
        user.setId(userDetails.getId());

        Users updatedUser = usersRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

}

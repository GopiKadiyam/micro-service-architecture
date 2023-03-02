package com.gk.user.controller;

import com.gk.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    static List<User> userList=new ArrayList<>();
    static {
        userList.add(new User(1L,"gopi","Guntur","male"));
        userList.add(new User(2L,"cddu","Tpty","female"));
        userList.add(new User(3L,"bhagi","Nellore","female"));
        userList.add(new User(4L,"sai","Rjm","male"));
    }

    @GetMapping("/all")
    public List<User> allUsers(){
        return userList;
    }

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id){
        return userList.get((int) (id-1));
    }

    @PostMapping("/few")
    public List<User> selectedUsers(@RequestBody List<Long> userIds){
        return userList.stream().filter(u->userIds.contains(u.getId())).collect(Collectors.toList());
    }

}

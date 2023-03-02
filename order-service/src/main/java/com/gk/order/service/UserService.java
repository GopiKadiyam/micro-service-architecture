package com.gk.order.service;

import com.gk.order.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="user-service")
public interface UserService {

    @GetMapping("/user/{id}")
    User user(@PathVariable("id") Long id);

    @PostMapping("/users")
    List<User> selectedUsers(@RequestBody List<Long> userIds);
}

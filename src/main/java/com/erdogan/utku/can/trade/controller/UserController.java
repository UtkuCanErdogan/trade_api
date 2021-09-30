package com.erdogan.utku.can.trade.controller;

import com.erdogan.utku.can.trade.dto.CreateUserRequest;
import com.erdogan.utku.can.trade.dto.UpdateUserRequest;
import com.erdogan.utku.can.trade.dto.UserDetailsDto;
import com.erdogan.utku.can.trade.dto.UserDto;
import com.erdogan.utku.can.trade.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/user{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping("/user/getAllUserDetails")
    public ResponseEntity<List<UserDetailsDto>> getAllUserDetails(){
        return ResponseEntity.ok(service.getAllUserDetails());
    }


    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request){
        return ResponseEntity.ok(service.createUser(request));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserRequest request, @RequestBody Long id){
        return ResponseEntity.ok(service.updateUser(request, id));
    }

    @PatchMapping("/{id}/active}")
    public ResponseEntity<Void> toActiveUser(@PathVariable("id") Long id){
        service.toActiveUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/deActive}")
    public ResponseEntity<Void> deActiveUser(@PathVariable("id") Long id){
        service.deActiveUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}

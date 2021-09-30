package com.erdogan.utku.can.trade.controller;

import com.erdogan.utku.can.trade.dto.CreateUserDetailsRequest;
import com.erdogan.utku.can.trade.dto.UpdateUserDetailsRequest;
import com.erdogan.utku.can.trade.dto.UserDetailsDto;
import com.erdogan.utku.can.trade.service.UserDetailsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/userDetails")
public class UserDetailsController {

    private final UserDetailsService service;

    public UserDetailsController(UserDetailsService service) {
        this.service = service;
    }

    @PostMapping("/createUserDetail")
    public ResponseEntity<UserDetailsDto> createUserDetail(@RequestBody CreateUserDetailsRequest request){
        return ResponseEntity.ok(service.createUserDetails(request));
    }

    @PutMapping("/updateUserDetail")
    public ResponseEntity<UserDetailsDto> updateUserDetail(@RequestBody UpdateUserDetailsRequest request,
                                                            @RequestBody Long userDetailId){
        return ResponseEntity.ok(service.updateUserDetails(request,userDetailId));
    }

    @DeleteMapping("{id}/deleteUserDetail")
    public ResponseEntity<Void> deleteUserDetail(@PathVariable("id") Long userDetailId){
        service.deleteUserDetail(userDetailId);
        return ResponseEntity.ok().build();
    }
}

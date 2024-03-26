package restful.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import restful.demo.model.request.UserRequest;
import restful.demo.model.response.UserResponse;
import restful.demo.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("user")
public class UiController {


    @Autowired
    UserService userService;

    @GetMapping
    public Map<String,UserResponse> getUsers(@RequestParam(value="page", defaultValue = "1") 
                int page,@RequestParam(value="limit", defaultValue = "100") int limit){
        return userService.getUsers(page, limit);
    }
    @GetMapping(path="/{userid}", produces = {org.springframework.http.MediaType.APPLICATION_XML_VALUE,
        org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable String userid ){

        UserResponse response=null;
        response=userService.getUser(userid);
        if(response!=null)
        return new ResponseEntity<>(response,HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{userId}", produces = { org.springframework.http.MediaType.APPLICATION_XML_VALUE,
            org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserRequest toUpdate) {

        UserResponse update = userService.updateUser(userId, toUpdate);
        return update;
    }

    @PostMapping (consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, 
    produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> creatUser(@Valid @RequestBody UserRequest user){

        UserResponse response=userService.createUser(user);

        return new ResponseEntity<UserResponse>(response,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId ){

        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public String patchUser(){
        return "patch";
    }

}

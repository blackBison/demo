package restful.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import restful.demo.model.request.UserRequest;
import restful.demo.model.response.UserResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    Map<String,UserResponse> users=null;

    @GetMapping
    public Map<String,UserResponse> getUsers(@RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value="limit", defaultValue = "100") int limit){
        return users;
    }
    @GetMapping(path="/{userid}", produces = {org.springframework.http.MediaType.APPLICATION_XML_VALUE,
        org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable String userid ){
        UserResponse response=null;
        if(users!=null){
            response=users.get(userid);
        }

        if(response!=null)
        return new ResponseEntity<>(response,HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        //new ResponseEntity<>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        //new ResponseEntity<User>(new User("prem", "das", "test@test.com"),HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", produces = { org.springframework.http.MediaType.APPLICATION_XML_VALUE,
            org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserRequest toUpdate) {
        UserResponse existingUser = null, update = null;
        if (users != null) {
            existingUser = users.get(userId);
        }
        if (existingUser != null) {
            update = new UserResponse();

            update.setUserid(userId);

            if (toUpdate.getFirstName() == null)
                update.setFirstName(existingUser.getFirstName());
            else
                update.setFirstName(toUpdate.getFirstName());

            if (toUpdate.getLastname() == null)
                update.setLastname(existingUser.getLastname());
            else
                update.setLastname(toUpdate.getLastname());

            if (toUpdate.getEmail() == null)
                update.setEmail(existingUser.getEmail());
            else
                update.setEmail(toUpdate.getEmail());
        }
        users.put(userId, update);

        return update;
    }

    @PostMapping (consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, 
    produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> creatUser(@Valid @RequestBody UserRequest user){

        UserResponse response=new UserResponse(user.getFirstName(),user.getLastname(),user.getEmail());

        if(users==null)
        users=new HashMap<String,UserResponse>();
        String userId=UUID.randomUUID().toString();
        response.setUserid(userId);
        users.put(userId,response);

        return new ResponseEntity<UserResponse>(response,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId ){
        if(users!=null){
            if(users.get(userId)!=null){
                users.remove(userId);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public String patchUser(){
        return "patch";
    }

}

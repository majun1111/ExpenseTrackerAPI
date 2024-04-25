package in.mallikarjun.expenseTrackerAPI.Controller;

import in.mallikarjun.expenseTrackerAPI.entity.User;
import in.mallikarjun.expenseTrackerAPI.entity.UserModel;
import in.mallikarjun.expenseTrackerAPI.exceptions.ResourceNotFoundException;
import in.mallikarjun.expenseTrackerAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> read(@PathVariable Long id){
        return new ResponseEntity<User>(userService.readUser(id),HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserModel userModel, @PathVariable Long id){
        return new ResponseEntity<User>(userService.updateUser(userModel,id),HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id)throws ResourceNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}

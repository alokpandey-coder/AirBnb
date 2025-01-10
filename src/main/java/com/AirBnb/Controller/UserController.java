package com.AirBnb.Controller;

import com.AirBnb.Payload.UserDto;
import com.AirBnb.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/new")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
// URL : http://localhost:8080/api/new/register/newUser
    @PostMapping("/register/newUser")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto dto){

       UserDto submittedRecord = userService.registerNewUser(dto);

        return new ResponseEntity<>(submittedRecord, HttpStatus.CREATED);
    }
    // URL : http://localhost:8080/api/new/deleteExistingUser
    @DeleteMapping("/deleteExistingUser")
    public ResponseEntity<String> deleteExistingUser(@RequestParam Long userId){
        userService.deleteExistingUser(userId);
        return new ResponseEntity<>("Delete user with userId" + userId ,HttpStatus.OK);

    }
    // URL : http://localhost:8080/api/new/updateUser

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateExistingUser(@RequestParam Long userId,@RequestBody UserDto dto){
        UserDto updatedRecord = userService.updateExistingUser(userId,dto);

        return new ResponseEntity<>(updatedRecord,HttpStatus.OK);
    }

    // URL : http://localhost:8080/api/new/listOfUsers
    @GetMapping("/listOfUsers")
    public ResponseEntity<List<UserDto>> getAllEmployee(){
       List<UserDto> list = userService.getAllEmployees();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}

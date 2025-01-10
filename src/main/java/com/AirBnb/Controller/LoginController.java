package com.AirBnb.Controller;

import com.AirBnb.Payload.JWTToken;
import com.AirBnb.Payload.LoginDto;
import com.AirBnb.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // URL : http://localhost:8080/api/auth/login

    @PostMapping("/login")
      public ResponseEntity<?> loginIntoApplication(@RequestBody LoginDto dto){

        String token = loginService.loginIntoApplication(dto);

        if(token!=null){
            JWTToken jwtToken = new JWTToken();
            jwtToken.setType("JWT");
            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }

        return new ResponseEntity<>("Login Failed. Please check your credentials and try again",HttpStatus.EXPECTATION_FAILED);
      }
}

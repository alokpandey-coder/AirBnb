package com.AirBnb.Service;

import com.AirBnb.Model.Users;
import com.AirBnb.Payload.LoginDto;
import com.AirBnb.Repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UsersRepository usersRepository;
    private final JWTService jwtService;

    public LoginService(UsersRepository usersRepository, JWTService jwtService) {
        this.usersRepository = usersRepository;
        this.jwtService = jwtService;
    }

    public String loginIntoApplication(LoginDto dto) {

     Optional<Users> opUserName = usersRepository.findByUserName(dto.getUserName());

     if(opUserName.isPresent()){
         Users user = opUserName.get();

         if(BCrypt.checkpw(dto.getPassword(),user.getPassword())){
             String token = jwtService.generateToken(user.getUserName());

             return token;
         }
     }
     return "null";
    }
}

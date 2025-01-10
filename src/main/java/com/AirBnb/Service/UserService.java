package com.AirBnb.Service;

import com.AirBnb.Model.Roles;
import com.AirBnb.Model.Users;
import com.AirBnb.Payload.UserDto;
import com.AirBnb.Repository.RolesRepository;
import com.AirBnb.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final RolesRepository rolesRepository;

    public UserService(UsersRepository usersRepository, ModelMapper modelMapper,
                       RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.rolesRepository = rolesRepository;
    }

    UserDto mapToDto(Users user){
        return modelMapper.map(user,UserDto.class);
    }

   Users mapToEntity(UserDto dto){
        return modelMapper.map(dto,Users.class);
    }


    public UserDto registerNewUser(UserDto dto) {

     Optional<Users> opUserName = usersRepository.findByUserName(dto.getUserName());
      if(opUserName.isPresent()){
          throw new RuntimeException(opUserName + " UserName is already Present in Record!!!!");
      }

      Optional<Users> opEmailId = usersRepository.findByEmail(dto.getEmail());
      if(opEmailId.isPresent()){
          throw new RuntimeException(opEmailId + " Email is already present in record!!!");
      }

      Optional<Users> opMobile = usersRepository.findByMobile(dto.getMobile());
      if(opMobile.isPresent()){
          throw new RuntimeException(opMobile + " Mobile No. is already present in Record");
      }

      Users user =mapToEntity(dto);

      //Fetch roles from role table(If u create new Table(Or do Many to Many Mapping) for Role than u have to add manually)

        Set<Roles> rolesSet = new HashSet<>();
        for (String roleName : dto.getRoles()) {
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role " + roleName + " not found"));
            rolesSet.add(role);
        }

        user.setRoles(rolesSet);

      user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12)));
      user.setRecordDate(new Timestamp(System.currentTimeMillis()));

      usersRepository.save(user);
      UserDto dtoUser =mapToDto(user);
      return dtoUser;
    }

    public void deleteExistingUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    public UserDto updateExistingUser(Long userId, UserDto dto) {

       Optional<Users> Op_user = usersRepository.findById(userId);

         if(Op_user.isEmpty()){
             throw new RuntimeException("User is not present in the Record");
         }

         Users user =Op_user.get();

          user.setFirstName(dto.getFirstName());
          user.setLastName(dto.getLastName());
          user.setUserName(dto.getUserName());
          user.setEmail(dto.getEmail());
          user.setMobile(dto.getMobile());
          user.setGender(dto.getGender());
          user.setDob(dto.getDob());


        //For Password if u want to set

        if(dto.getPassword()!=null && !dto.getPassword().isEmpty()){
            user.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(12)));
        }

           usersRepository.save(user);
           UserDto recordsDto = mapToDto(user);
           return recordsDto;
    }



      public List<UserDto> getAllEmployees() {
         List<Users> listOfUsers = usersRepository.findAll();

       return  listOfUsers.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}

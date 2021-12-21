package vuonghieu.project.mapper.mapperDto.impl;

import org.springframework.stereotype.Component;
import vuonghieu.project.dto.RegisterDTO;
import vuonghieu.project.entity.Admin;

@Component
public class RegisterMapperDTO {


   public Admin convertRegisterToAdmin(RegisterDTO registerDTO){
        Admin admin = new Admin();
        admin.setNameAdmin(registerDTO.getNameAdmin());
        admin.setAddress(registerDTO.getAddress());
        admin.setEmail(registerDTO.getEmail());
        admin.setPhoneNumber(registerDTO.getPhoneNumber());
        return admin;
    }
}

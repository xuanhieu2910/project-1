package vuonghieu.project.mapper.mapperDto.impl;

import org.springframework.stereotype.Component;
import vuonghieu.project.dto.AdminDTO;
import vuonghieu.project.dto.AdminUpdateDTO;
import vuonghieu.project.entity.Admin;
import vuonghieu.project.mapper.mapperDto.MapperDTO;
@Component
public class AdminMapperDTO {


    public Admin convertEntityToDTO(AdminUpdateDTO adminUpdateDTO) {
        Admin admin = new Admin();
        admin.setNameAdmin(adminUpdateDTO.getNameAdmin());
        admin.setAddress(adminUpdateDTO.getAddress());
        admin.setPhoneNumber(adminUpdateDTO.getPhoneNumber());
        admin.setContact(adminUpdateDTO.getContact());
        admin.setDateOfBirth(adminUpdateDTO.getDateOfBirth());
        return admin;
    }

}

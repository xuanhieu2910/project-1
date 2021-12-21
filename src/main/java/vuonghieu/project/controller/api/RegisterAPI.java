package vuonghieu.project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vuonghieu.project.dto.RegisterDTO;
import vuonghieu.project.entity.Admin;
import vuonghieu.project.mapper.mapperDto.impl.RegisterMapperDTO;
import vuonghieu.project.service.impl.AdminServiceImpl;

@RestController
@RequestMapping("/api/register")
public class RegisterAPI {


    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    RegisterMapperDTO  registerMapperDTO;

    @PostMapping
    public ResponseEntity<String> createNewRegister(@RequestBody RegisterDTO registerDTO){
        Admin admin = registerMapperDTO.convertRegisterToAdmin(registerDTO);
        adminService.save(admin, registerDTO.getPassword());
        return new ResponseEntity<>("Đăng ký thành công!", HttpStatus.OK);
    }
}

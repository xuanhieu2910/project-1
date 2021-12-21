package vuonghieu.project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vuonghieu.project.dto.AdminDTO;
import vuonghieu.project.dto.AdminUpdateDTO;
import vuonghieu.project.entity.Admin;
import vuonghieu.project.mapper.mapperDto.impl.AdminMapperDTO;
import vuonghieu.project.service.impl.AdminServiceImpl;
import vuonghieu.project.service.impl.FileUploadService;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class AdminAPI {

    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    FileUploadService fileUploadService;

   @Autowired
    AdminMapperDTO  adminMapperDTO;
    @GetMapping
    public Admin informationAccount(){
            return adminService.findAdmin();
    }

    @PostMapping(value = "/update-account")
    public ResponseEntity<Object> updateAdmin(@RequestBody AdminUpdateDTO adminUpdateDTO){
        Admin admin = adminMapperDTO.convertEntityToDTO(adminUpdateDTO);
        adminService.updateById(admin);
        return ResponseEntity.ok().build();
    }

}

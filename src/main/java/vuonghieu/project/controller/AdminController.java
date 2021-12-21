package vuonghieu.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vuonghieu.project.entity.Admin;
import vuonghieu.project.service.impl.AdminServiceImpl;
import vuonghieu.project.service.impl.FileUploadService;

import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping("/tai-khoan")
    public String account(){return "account";}

    @RequestMapping("/chinh-sua-tai-khoan")
    public String updateAccount(){return "updateAccount";}

    @PostMapping("/update-image")
    public String updateImageAvatar(@RequestParam("image")MultipartFile file){
        String image = file.getOriginalFilename();
        try {
            fileUploadService.uploadMultiPart(file);
            adminService.updateImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/tai-khoan";
    }
}

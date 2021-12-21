package vuonghieu.project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vuonghieu.project.entity.Login;
import vuonghieu.project.service.impl.LoginServiceImpl;

@RestController
@RequestMapping("/api/login")
public class LoginAPI {

    @Autowired
    LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<Object> loginSystem(@RequestBody Login login){
        if(loginService.checkLogin(login.getEmailAccount(),login.getPassword())){
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.status(500).body("Tài khoản hoặc mật khẩu không đúng");
    }
}

package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.LoginDaoImpl;
import vuonghieu.project.entity.Login;


import java.util.List;

@Component
public class LoginServiceImpl {

    @Autowired
    LoginDaoImpl loginDao;



    public Login save(Login login) {
        return null;
    }


    public List<Login> findAll() {
        return null;
    }


    public Login findByEmail(String email) {
        return loginDao.findByEmail(email);
    }

    public Boolean checkLogin(String email,String passwordLogin){
        Login login = findByEmail(email);
        if(login==null){
            return false;
        }
        else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            return bCryptPasswordEncoder.matches(passwordLogin, login.getPassword());
        }
    }

    public Login updateById(Login login, int id) {
        return null;
    }

    public void deleteById(int id) {

    }

    public Boolean checkLogin(Login login){
        return null;
    }


}

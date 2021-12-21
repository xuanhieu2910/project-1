package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.AdminDaoImpl;
import vuonghieu.project.entity.Admin;
import vuonghieu.project.service.RootService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class AdminServiceImpl  {

    public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");


    @Autowired
    AdminDaoImpl adminDao;

    public String bCryptPasswordEncoder(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }


    public void save(Admin admin,String password) {
        password = bCryptPasswordEncoder(password);
        Date dateNow = new Date();
        String dateFormat = SIMPLE_DATE_FORMAT.format(dateNow);
        Date createdOn =null;
        Date modifiedOn = null;
        try{
            createdOn = SIMPLE_DATE_FORMAT.parse(dateFormat);
            modifiedOn = SIMPLE_DATE_FORMAT.parse(dateFormat);
        }catch (Exception e){
            e.printStackTrace();
        }
        admin.setImage(null);
        admin.setCreatedOn(createdOn);
        admin.setModifiedOn(modifiedOn);
        admin.setDateOfBirth(null);
        adminDao.save(admin);
        adminDao.saveLogin(admin,password);
    }




    public Admin findAdmin() {
        return adminDao.findAdmin();
    }


    public void updateById(Admin admin) {
        Date dateNow = new Date();
        String dateModifiedOn = SIMPLE_DATE_FORMAT.format(dateNow);
        Date modifiedOn=null;
        try {
            modifiedOn = SIMPLE_DATE_FORMAT.parse(dateModifiedOn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        admin.setModifiedOn(modifiedOn);
        adminDao.updateAdmin(admin);
    }


    public void deleteById(int id) {

    }

    public void updateImage(String image){
        adminDao.updateImage(image);
    }
}

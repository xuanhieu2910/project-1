package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.RootDao;
import vuonghieu.project.entity.Admin;
import vuonghieu.project.mapper.row.AdminRowMapper;

import java.util.HashMap;
import java.util.List;
@Component
public class AdminDaoImpl  {


    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void save(Admin admin) {
        String query ="INSERT INTO admin(name_admin,address,phone_number,email,created_on,modified_on,contact,date_of_birth,image)" +
                "VALUES(:nameAdmin,:address,:phoneNumber,:email,:createdOn,:modifiedOn,:contact,:dateOfBirth,:image)";
        HashMap<String,Object>params = new HashMap<>();
        params.put("nameAdmin",admin.getNameAdmin());
        params.put("address",admin.getAddress());
        params.put("phoneNumber",admin.getPhoneNumber());
        params.put("email",admin.getEmail());
        params.put("createdOn",admin.getCreatedOn());
        params.put("modifiedOn",admin.getModifiedOn());
        params.put("contact",admin.getContact());
        params.put("dateOfBirth",admin.getDateOfBirth());
        params.put("image",admin.getImage());
        namedParameterJdbcTemplate.update(query,params);
        return;
    }

    public void saveLogin(Admin admin,String passwordBcrty){
        String query ="INSERT INTO login(email_account,password) VALUES(:emailAccount,:password)";
        HashMap<String,Object>params = new HashMap<>();
        params.put("emailAccount",admin.getEmail());
        params.put("password",passwordBcrty);
        namedParameterJdbcTemplate.update(query,params);
    }




    public Admin findAdmin() {
        String query="SELECT*FROM admin";
        return jdbcTemplate.queryForObject(query,new AdminRowMapper());
    }


    public void updateAdmin(Admin admin) {
        String query = "UPDATE admin SET name_admin=:nameAdmin,phone_number=:phoneNumber,modified_on=:modifiedOn,date_of_birth=:dateOfBirth,contact=:contact," +
                "address=:address where email='hieuxuanvuong29102000@gmail.com'";
        HashMap<String,Object>params = new HashMap<>();
        params.put("nameAdmin",admin.getNameAdmin());
        params.put("phoneNumber",admin.getPhoneNumber());
        params.put("modifiedOn",admin.getModifiedOn());
        params.put("dateOfBirth",admin.getDateOfBirth());
        params.put("contact",admin.getContact());
        params.put("address",admin.getAddress());
        namedParameterJdbcTemplate.update(query,params);
    }


    public void deleteById(String id) {

    }

    public void updateImage(String nameImage){
        String query ="UPDATE admin SET image=:nameImage WHERE email='hieuxuanvuong29102000@gmail.com'";
        HashMap<String,Object>param = new HashMap<>();
        param.put("nameImage",nameImage);
        namedParameterJdbcTemplate.update(query,param);
    }

}

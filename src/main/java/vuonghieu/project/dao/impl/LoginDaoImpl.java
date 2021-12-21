package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.RootDao;
import vuonghieu.project.entity.Login;
import vuonghieu.project.mapper.row.LoginMapper;

import java.util.HashMap;
import java.util.List;
@Component
public class LoginDaoImpl  {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Login save(Login login) {
        return null;
    }


    public List<Login> findAll() {
        return null;
    }


    public Login findByEmail(String email) {
        String query ="SELECT*FROM login WHERE email_account=:email";
        HashMap<String,Object>param = new HashMap<>();
        param.put("email",email);
        return namedParameterJdbcTemplate.queryForObject(query,param,new LoginMapper());
    }


    public Login updateById(Login login, String id) {
        return null;
    }


    public void deleteById(String id) {

    }

}

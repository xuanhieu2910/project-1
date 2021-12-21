package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.RootDao;
import vuonghieu.project.entity.Students;
import vuonghieu.project.mapper.row.StudentRowMapper;

import java.util.HashMap;
import java.util.List;
@Component
public class StudentsDaoImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<Students> findAll() {
        String query = "SELECT*FROM student";
        return jdbcTemplate.query(query,new StudentRowMapper());
    }


    public Students findById(int mssv) {
        String query  = "SELECT*FROM student WHERE  mssv=:mssv";
        HashMap<String,Object>params = new HashMap<>();
        params.put("mssv",mssv);
        return namedParameterJdbcTemplate.queryForObject(query,params,new StudentRowMapper()) ;
    }

    public List<Students> findStudentByMssvs(List<Integer>students){
        String query = "SELECT*FROM student WHERE student.mssv in(:students)";
        HashMap<String,Object>param = new HashMap<>();
        param.put("students",students);
        return namedParameterJdbcTemplate.query(query,param,new StudentRowMapper());
    }
}

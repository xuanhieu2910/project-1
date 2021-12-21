package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.RootDao;
import vuonghieu.project.entity.Major;
import vuonghieu.project.mapper.row.MajorRowMapper;

import java.util.HashMap;
import java.util.List;
@Component
public class MajorDaoImpl {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public Major save(Major major) {
        return major;
    }


    public List<Major> findAll() {
        String sql = "SELECT*FROM majors";
        List<Major> majors = namedParameterJdbcTemplate.query(sql,new MajorRowMapper());
        return majors;
    }


    public Major findById(int id) {
        String sql = "SELECT*FROM majors WHERE id =: id_majors";
        HashMap<String,Integer>queryParams = new HashMap<>();
        return namedParameterJdbcTemplate.queryForObject(sql,queryParams,Major.class);
    }


    public Major updateById(Major major, int id) {
        String sql="UPDATE majors SET name_major = :name_major WHERE id = :id";
        jdbcTemplate.update(sql,major.getNameMajor(),id);
        return major;
    }


    public void deleteById(int id) {
        String sql = "DELETE FROM majors WHERE id = :id";
        HashMap<String,Integer>queryParam = new HashMap<>();
        queryParam.put("id",id);
        namedParameterJdbcTemplate.queryForObject(sql,queryParam,Major.class);
    }
}

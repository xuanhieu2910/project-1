package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.RootDao;
import vuonghieu.project.entity.Category;
import vuonghieu.project.mapper.row.CategoryRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryDaoImpl implements RootDao<Category> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Category save(Category category) {
        String query = "INSERT INTO category(name_category,code_category,created_on,modified_on) VALUES(?,?,?,?)";
        jdbcTemplate.update(query,category.getNameCategory(),category.getCodeCategory(),category.getCreatedOn(),category.getModifiedOn());
        return null;
    }

    @Override
    public List<Category> findAll() {
        String query = "SELECT*FROM category";
        return jdbcTemplate.query(query,new CategoryRowMapper());
    }

    @Override
    public Category findById(String id) {
        return null;
    }

    @Override
    public Category updateById(Category category, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }


    public void deleteByCodeCategory(String codeBook){
        String query = "DELETE FROM category WHERE code_category='"+codeBook+"'";
        jdbcTemplate.update(query);
        return;
    }

    public List<String> findAllCodeCategories(){
         String query = "SELECT category.code_category FROM category";
         List<String>codeCategories = jdbcTemplate.queryForList(query,String.class);
         return codeCategories;
    }

}

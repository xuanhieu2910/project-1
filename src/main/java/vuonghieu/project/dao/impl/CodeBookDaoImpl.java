package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.RootDao;
import vuonghieu.project.entity.CodeBook;
import vuonghieu.project.mapper.row.CodeBookMapper;

import java.util.HashMap;
import java.util.List;
@Component
public class CodeBookDaoImpl  {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CodeBook save(CodeBook codeBook) {
        String query = "INSERT INTO code_book(code_book_child,code_book,enable) VALUES(?,?,?)";
        jdbcTemplate.update(query,codeBook.getCodeBookChild(),codeBook.getCodeBook(),0);
        return null;
    }


    public List<CodeBook> findAll() {
        return null;
    }


    public CodeBook findByStringCodeBookChild(String id) {

        return null;
    }


    public CodeBook updateById(CodeBook codeBook, String id) {
        return null;
    }


    public void deleteById(String id) {

    }


    public void deleteCodeBookChildByCodeBookParent(String codeBookParent){
        String query ="DELETE FROM code_book WHERE code_book=:codeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("codeBook",codeBookParent);
        namedParameterJdbcTemplate.update(query,params);
    }

    public int getCountByCodeBook(String codeBookParent){
        String query = "SELECT COUNT(code_book_child) from code_book where code_book=?";
        HashMap<String,Object>params = new HashMap<>();
        params.put("codeBookParent",codeBookParent);
        return jdbcTemplate.queryForObject(query,new Object[]{codeBookParent},Integer.class);
    }

    public List<CodeBook> getAllCodeBookByEnable(){
        String query = "SELECT*FROM codebook where enable=0";
       return jdbcTemplate.query(query,new CodeBookMapper());
    }

    public void updateCodeBookBorrow(String codeBookChild){
        String query = "UPDATE code_book SET enable =1 WHERE code_book_child=?";
        jdbcTemplate.update(query,codeBookChild);
    }

    public void updateCodeBookPaymentBorrow(String codeBookChild){
        String query ="UPDATE code_book SET enable=0 WHERE code_book_child=?";
        jdbcTemplate.update(query,codeBookChild);
    }

    public String getCodeBookByCodeBookChild(String codeBookChild){
        String query = "SELECT code_book FROM code_book WHERE code_book_child=?";
        return jdbcTemplate.queryForObject(query,new Object[]{codeBookChild},String.class);
    }

    public void updateOldCodeBook(String oldCodeBook){
        String query="UPDATE code_book SET enable=0 WHERE code_book_child=:oldCodeBook";
        HashMap<String,Object>param = new HashMap<>();
        param.put("oldCodeBook",oldCodeBook);
        namedParameterJdbcTemplate.update(query,param);
    }
    public  void updateNewCodeBook(String newCodeBook){
        String query="UPDATE code_book SET enable=1 WHERE code_book_child=:newCodeBook";
        HashMap<String,Object>param=new HashMap<>();
        param.put("newCodeBook",newCodeBook);
        namedParameterJdbcTemplate.update(query,param);
    }
}

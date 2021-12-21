package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import vuonghieu.project.entity.BorrowDetails;
import vuonghieu.project.mapper.row.BorrowDetailsMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class BorrowDetailsDaoImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<BorrowDetails> findAllBorrowDetails(){
        String query = "SELECT*FROM borrow_details";
        return jdbcTemplate.query(query,new BorrowDetailsMapper());
    }


    public void saveBorrowDetails(List<BorrowDetails>borrowDetails){
        String query = "INSERT INTO borrow_details(id_borrow,code_book_child,createdOn,modifiedOn,expiry,datePayment,price,borrowed)"+
                "VALUES(?,?,?,?,?,?,?,?)";
        for (BorrowDetails borrowDetails1:borrowDetails) {
            jdbcTemplate.update(query,borrowDetails1.getCodeBorrow(),borrowDetails1.getCodeBookChild(),borrowDetails1.getCreatedOn(),borrowDetails1.getModifiedOn(),
            borrowDetails1.getExpiry(),borrowDetails1.getDatePayment(),borrowDetails1.getPrice(),borrowDetails1.getBorrowed());
        }
    }

    public List<BorrowDetails> findAllBorrowDetailsByCodeBorrow(String codeBorrow) {
        return null;
    }


    public BorrowDetails findById(String id) {
        return null;
    }


    public BorrowDetails updateById(BorrowDetails borrowDetails, String id) {
        return null;
    }

    public void deleteById(String id) {

    }

    public List<BorrowDetails> getAllNotBorrowedByCodeBorrow(List<String>codeBorrowed){
        String query = "SELECT*FROM borrow_details WHERE borrow_details.id_borrow IN (:codesBorrowed) AND borrow_details.borrowed=0";
        HashMap<String,Object>params = new HashMap<>();
        params.put("codesBorrowed",codeBorrowed);
        List<BorrowDetails> borrowDetails =  namedParameterJdbcTemplate.query(query,params,new BorrowDetailsMapper());

        return borrowDetails;
    }


    public void paymentBorrowByCodeBorrow(String codeBorrow, String codeBook, Date modifiedOn, Date datePayment,Float price){
        String query = "UPDATE borrow_details SET borrowed=1 , modifiedOn=:modifiedOn,datePayment=:datePayment , price=:price WHERE id_borrow=:codeBorrow and code_book_child =:codeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("modifiedOn",modifiedOn);
        params.put("datePayment",datePayment);
        params.put("price",price);
        params.put("codeBorrow",codeBorrow);
        params.put("codeBook",codeBook);
        namedParameterJdbcTemplate.update(query,params);
    }

    public Integer getCountBorrowDetailsByCodeBorrow(String codeBorrow){
        String query = "select COUNT(borrow_details.borrowed) FROM borrow_details WHERE id_borrow=:codeBorrow and borrowed=0";
        HashMap<String,Object>param = new HashMap<>();
        param.put("codeBorrow",codeBorrow);
        return namedParameterJdbcTemplate.queryForObject(query,param,Integer.class);
    }


    public void updateNewCodeBook(String codeBorrow,String newCodeBook,String oldCodeBook,Date modifiedOn){
        String query ="UPDATE borrow_details SET code_book_child=:newCodeBook , modifiedOn=:modifiedOn WHERE id_borrow=:codeBorrow AND code_book_child=:oldCodeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("newCodeBook",newCodeBook);
        params.put("modifiedOn",modifiedOn);
        params.put("codeBorrow",codeBorrow);
        params.put("oldCodeBook",oldCodeBook);
        namedParameterJdbcTemplate.update(query,params);
    }

    public void deleteBookBorrowDetails(String codeBorrow,String codeBook){
        String query ="DELETE FROM borrow_details WHERE id_borrow=:codeBorrow AND code_book_child=:codeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("codeBorrow",codeBorrow);
        params.put("codeBook",codeBook);
        namedParameterJdbcTemplate.update(query,params);
    }

    public Integer sumTotalSalesByMssv(int mssv){
        String query = "select count(borrowed) AS numberBorrow from borrow_details where id_borrow like :mssv";
        HashMap<String,Object>params = new HashMap<>();
        params.put("mssv",mssv+"%");
        return namedParameterJdbcTemplate.queryForObject(query,params,Integer.class);
    }

    public Integer sumTotalBorrowedByMssv(int mssv){
        String query = "select count(borrowed) from borrow_details where id_borrow like (:mssv) and borrowed=1";
        HashMap<String,Object>params = new HashMap<>();
        params.put("mssv",mssv+"%");
        return namedParameterJdbcTemplate.queryForObject(query,params,Integer.class);
    }

    public Integer sumTotalUnBorrowedByMssv(int mssv){
        String query = "select count(borrowed)  from borrow_details where id_borrow like (:mssv) and borrowed=0";
        HashMap<String,Object>params = new HashMap<>();
        params.put("mssv",mssv+"%");
        return namedParameterJdbcTemplate.queryForObject(query,params,Integer.class);
    }


    public Float sumPriceByMssv(int mssv){
        String query ="select SUM(price)  from borrow_details where id_borrow like (:mssv) and borrowed=1";
        HashMap<String,Object>params = new HashMap<>();
        params.put("mssv",mssv+"%");
        return namedParameterJdbcTemplate.queryForObject(query,params,Float.class);
    }

    public List<BorrowDetails>historyBorrowByMssv(int mssv){
        String query ="SELECT*FROM borrow_details WHERE id_borrow like (:mssv) and borrowed=1";
        HashMap<String,Object>param = new HashMap<>();
        param.put("mssv",mssv+"%");
        return namedParameterJdbcTemplate.query(query,param,new BorrowDetailsMapper());
    }

    public String getNameBookByCodeBookBorrow(String codeBookBorrow){
        String query ="SELECT book.name_book FROM book , code_book WHERE book.code_book = code_book.code_book AND code_book.code_book_child=:codeBookBorrow";
        HashMap<String,Object>param = new HashMap<>();
        param.put("codeBookBorrow",codeBookBorrow);
        return namedParameterJdbcTemplate.queryForObject(query,param,String.class);
    }


}

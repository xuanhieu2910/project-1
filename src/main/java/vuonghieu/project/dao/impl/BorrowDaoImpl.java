package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.RootDao;
import vuonghieu.project.entity.Borrow;
import vuonghieu.project.mapper.row.BorrowMapper;

import java.util.HashMap;
import java.util.List;
@Component
public class BorrowDaoImpl{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Borrow borrow) {
        String query = "INSERT INTO borrow(codeBorrow,mssv,createdOn,total_quantity,status) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(query,borrow.getIdBorrow(),borrow.getMssv(),borrow.getCreatedOn(),borrow.getTotalQuantity(),borrow.getStatus());
    }


    public List<Borrow> findAll() {
        String query="SELECT*FROM borrow";
        return jdbcTemplate.query(query,new BorrowMapper());
    }


    public Borrow findByCodeBorrow(String codeBorrow) {
        return null;
    }


    public Borrow updateBorrowByCodeBorrow(Borrow borrow, String id) {
        return null;
    }


    public void deleteById(String codeBorrow) {

    }

    public List<Integer> getAllMssvWithStatusNot(){
        String query ="select DISTINCT mssv  from borrow where status='NOT'";
        List<Integer> mssv = jdbcTemplate.queryForList(query,Integer.class);
        return mssv;
    }


    public Integer getCountBorrowByMssv(int mssv){
        String query ="SELECT COUNT(borrow.codeBorrow) FROM borrow where borrow.mssv=?";
        return  jdbcTemplate.queryForObject(query,new Object[]{mssv},Integer.class);
    }

    public List<String> findCodeBooksNotPaymentByMssv(int mssv){
        String query = "SELECT borrow.codeBorrow FROM borrow WHERE  borrow.mssv=? AND borrow.status='NOT'";
       return jdbcTemplate.queryForList(query,String.class,mssv);
    }

    public  void updateStatusByCodeBorrow(String codeBorrow){
        String query = "UPDATE borrow SET status='YES' WHERE codeBorrow=:codeBorrow";
        HashMap<String,Object>param = new HashMap<>();
        param.put("codeBorrow",codeBorrow);
        namedParameterJdbcTemplate.update(query,param);
    }
    public Integer getTotalQuantityByCodeBorrow(String codeBorrow){
        String query = "SELECT total_quantity FROM borrow WHERE codeBorrow=:codeBorrow";
        HashMap<String,Object>param= new HashMap<>();
        param.put("codeBorrow",codeBorrow);
        return namedParameterJdbcTemplate.queryForObject(query,param,Integer.class);
    }

    public void updateQuantityTotal(String codeBorrow,Integer quantity){
        String query ="UPDATE borrow SET total_quantity=:quantity WHERE codeBorrow=:codeBorrow";
        HashMap<String,Object>params = new HashMap<>();
        params.put("quantity",quantity);
        params.put("codeBorrow",codeBorrow);
        namedParameterJdbcTemplate.update(query,params);
    }

    public List<Integer> listStudentBorrowed(){
        String query ="SELECT distinct borrow.mssv FROM borrow";
        List<Integer>studentsBorrowed = jdbcTemplate.queryForList(query,Integer.class);
        return studentsBorrowed;
    }
}

package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.RootDao;
import vuonghieu.project.entity.Book;
import vuonghieu.project.entity.CodeBook;
import vuonghieu.project.mapper.row.BookRowMapper;

import java.util.HashMap;
import java.util.List;
@Component
public class BookDaoImpl implements RootDao<Book> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Book save(Book book) {
        String query = "INSERT INTO book(code_book,name_book,author,company,created_on," +
                "description,inventory,modified_on,price," +
                "quantity,sales,code_major,code_category) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,book.getCodeBook(),book.getNameBook(),book.getAuthor(),book.getCompany(),book.getCreatedOn(),
                book.getDescription(),book.getInventory(),book.getModifiedOn(),book.getPrice(),book.getQuantity(),book.getSales(),
                book.getCodeMajor(),book.getCodeCategory());
        return book;
    }

    @Override
    public List<Book> findAll() {
        String query = "select*from book;";
        List<Book> books= jdbcTemplate.query(query,new BookRowMapper());
        if(books.isEmpty()){
            return null;
        }
        return books;
    }

    @Override
    public Book findById(String id) {
        return null;
    }

    @Override
    public Book updateById(Book book, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }


    public void updateBookByCodeBook(String codeBook, Book book){
        String query = "UPDATE book SET name_book=:nameBook,author=:author,company=:company,modified_on=:modifiedOn,description=:description," +
                "price=:price,code_major=:codeMajor,code_category=:codeCategory WHERE code_book=:codeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("nameBook",book.getNameBook());
        params.put("author",book.getAuthor());
        params.put("company",book.getCompany());
        params.put("modifiedOn", book.getModifiedOn());
        params.put("description",book.getCompany());
        params.put("price",book.getPrice());
        params.put("codeMajor",book.getCodeMajor());
        params.put("codeCategory",book.getCodeCategory());
        params.put("codeBook",book.getCodeBook());
        namedParameterJdbcTemplate.update(query,params);
    }


    public  void deleteBookByCodeBook(String codeBook){
        String query = "DELETE FROM book WHERE code_book=:codeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("codeBook",codeBook);
        namedParameterJdbcTemplate.update(query,params);
    }


    public Book findBookByCodeBook(String codeBook){
        String query = "SELECT*FROM book WHERE code_book=:codeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("codeBook",codeBook);
        Book book=namedParameterJdbcTemplate.queryForObject(query,params,new BookRowMapper());
        return book;
    }


    public void updateQuantity(String codeBook, int newQuantity,int newSales){
        String query = "UPDATE book SET quantity=:newQuantity, sales=:newSales WHERE code_book=:codeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("newQuantity",newQuantity);
        params.put("newSales",newSales);
        params.put("codeBook",codeBook);
        namedParameterJdbcTemplate.update(query,params);
    }

    public int getQuantitySales(String codeBook){
        String query ="SELECT book.sales FROM book WHERE book.code_book=?";
        return jdbcTemplate.queryForObject(query,new Object[]{codeBook},Integer.class);
    }

    public void updateQuantitySales(int quantitySalesAfter,String codeBook){
        String query ="update book set book.sales = ? where code_book=?";
        jdbcTemplate.update(query,quantitySalesAfter,codeBook);
    }



    public  void updateQuantitySalesAfterPayment(String codeBook,int quantitySales){
        String query = "UPDATE book SET sales=:quantitySales WHERE code_book=:codeBook";
        HashMap<String,Object>params = new HashMap<>();
        params.put("quantitySales",quantitySales);
        params.put("codeBook",codeBook);
        namedParameterJdbcTemplate.update(query,params);
    }
}

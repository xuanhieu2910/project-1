package vuonghieu.project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import vuonghieu.project.dto.BookBorrowDTO;
import vuonghieu.project.mapper.row.BookBorrowRowMapper;

import java.util.*;
@Component
public class BookBorrowDaoImpl {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<BookBorrowDTO> findAllBookBorrowEnable(){
        String query ="select code_book.code_book_child AS codeBookChild, book.name_book AS nameBook, majors.name_major AS nameMajor, category.name_category AS nameCategory , book.author AS nameAuthor , code_book.enable AS enable from " +
                "code_book,book,majors,category " +
                "where book.code_major = majors.code_major " +
                "and book.code_category = category.code_category " +
                "and code_book.code_book = book.code_book " +
                "and code_book.enable=0";
        List<BookBorrowDTO>bookBorrowDTOS = jdbcTemplate.query(query,new BookBorrowRowMapper());
        return bookBorrowDTOS;
    }

}

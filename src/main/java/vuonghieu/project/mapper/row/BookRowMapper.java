package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setCodeBook(rs.getString("code_book"));
        book.setNameBook(rs.getString("name_book"));
        book.setAuthor(rs.getString("author"));
        book.setCompany(rs.getString("company"));
        book.setCreatedOn(rs.getDate("created_on"));
        book.setModifiedOn(rs.getDate("modified_on"));
        book.setQuantity(rs.getInt("quantity"));
        book.setInventory(rs.getInt("inventory"));
        book.setSales(rs.getInt("sales"));
        book.setDescription(rs.getString("description"));
        book.setPrice(rs.getFloat("price"));
        book.setCodeMajor(rs.getString("code_major"));
        book.setCodeCategory(rs.getString("code_category"));
        return book;
    }
}

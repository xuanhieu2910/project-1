package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.dto.BookBorrowDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBorrowRowMapper implements RowMapper<BookBorrowDTO> {
    @Override
    public BookBorrowDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookBorrowDTO bookBorrowDTO = new BookBorrowDTO();
        bookBorrowDTO.setCodeBookChild(rs.getString("codeBookChild"));
        bookBorrowDTO.setNameBook(rs.getString("nameBook"));
        bookBorrowDTO.setNameMajor(rs.getString("nameMajor"));
        bookBorrowDTO.setNameCategory(rs.getString("nameCategory"));
        bookBorrowDTO.setNameAuthor(rs.getString("nameAuthor"));
        bookBorrowDTO.setEnable(rs.getInt("enable"));
        return bookBorrowDTO;
    }
}

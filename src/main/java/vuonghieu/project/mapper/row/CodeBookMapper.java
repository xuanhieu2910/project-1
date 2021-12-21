package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.CodeBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeBookMapper implements RowMapper<CodeBook> {
    @Override
    public CodeBook mapRow(ResultSet rs, int rowNum) throws SQLException {
        CodeBook codeBook = new CodeBook();
        codeBook.setCodeBookChild(rs.getString("code_book_child"));
        codeBook.setCodeBook(rs.getString("code_book"));
        codeBook.setEnable(rs.getInt("enable"));
        return codeBook;
    }
}

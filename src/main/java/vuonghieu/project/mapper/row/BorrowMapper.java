package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.Borrow;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowMapper implements RowMapper<Borrow> {
    @Override
    public Borrow mapRow(ResultSet rs, int rowNum) throws SQLException {
        Borrow borrow = new Borrow();
        borrow.setIdBorrow(rs.getString("codeBorrow"));
        borrow.setMssv(rs.getInt("mssv"));
        borrow.setTotalQuantity(rs.getInt("total_quantity"));
        borrow.setCreatedOn(rs.getDate("createdOn"));
        borrow.setStatus(rs.getString("status"));
        return borrow;
    }
}

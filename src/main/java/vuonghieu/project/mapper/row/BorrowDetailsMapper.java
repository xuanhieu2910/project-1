package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.BorrowDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowDetailsMapper implements RowMapper<BorrowDetails> {
    @Override
    public BorrowDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        BorrowDetails borrowDetails = new BorrowDetails();
        borrowDetails.setCodeBorrow(rs.getString("id_borrow"));
        borrowDetails.setCodeBookChild(rs.getString("code_book_child"));
        borrowDetails.setCreatedOn(rs.getDate("createdOn"));
        borrowDetails.setModifiedOn(rs.getDate("modifiedOn"));
        borrowDetails.setExpiry(rs.getDate("expiry"));
        borrowDetails.setDatePayment(rs.getDate("datePayment"));
        borrowDetails.setPrice(rs.getFloat("price"));
        borrowDetails.setBorrowed(rs.getInt("borrowed"));
        return borrowDetails;
    }
}

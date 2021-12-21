package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin = new Admin();
        admin.setNameAdmin(rs.getString("name_admin"));
        admin.setEmail(rs.getString("email"));
        admin.setPhoneNumber(rs.getInt("phone_number"));
        admin.setAddress(rs.getString("address"));
        admin.setImage(rs.getString("image"));
        admin.setDateOfBirth(rs.getDate("date_of_birth"));
        admin.setContact(rs.getString("contact"));
        return admin;
    }
}

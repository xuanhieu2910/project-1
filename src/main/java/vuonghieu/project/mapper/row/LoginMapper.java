package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMapper implements RowMapper<Login> {
    @Override
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login login  = new Login();
        login.setEmailAccount(rs.getString("email_account"));
        login.setPassword(rs.getString("password"));
        return login;
    }
}

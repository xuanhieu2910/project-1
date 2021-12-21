package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.Students;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Students> {
    @Override
    public Students mapRow(ResultSet rs, int rowNum) throws SQLException {
        Students students = new Students();
        students.setMssv(rs.getInt("mssv"));
        students.setNameStudent(rs.getString("name"));
        students.setMajor(rs.getString("major"));
        students.setEmail(rs.getString("email"));
        students.setPhone(rs.getInt("phone"));
        return students;
    }
}

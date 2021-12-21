package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.Major;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MajorRowMapper implements RowMapper<Major> {
    @Override
    public Major mapRow(ResultSet rs, int rowNum) throws SQLException {
        Major major = new Major();
        major.setNameMajor(rs.getString("name_major"));
        major.setCodeMajor(rs.getString("code_major"));
        return major;
    }
}

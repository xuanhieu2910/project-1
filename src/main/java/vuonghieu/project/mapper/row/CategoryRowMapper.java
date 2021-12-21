package vuonghieu.project.mapper.row;

import org.springframework.jdbc.core.RowMapper;
import vuonghieu.project.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setNameCategory(rs.getString("name_category"));
        category.setCodeCategory(rs.getString("code_category"));
        category.setCreatedOn(rs.getString("created_on"));
        category.setModifiedOn(rs.getString("modified_on"));
        return category;
    }
}

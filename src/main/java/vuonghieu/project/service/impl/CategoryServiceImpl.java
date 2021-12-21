package vuonghieu.project.service.impl;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.CategoryDaoImpl;
import vuonghieu.project.entity.Category;
import vuonghieu.project.service.RootService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class CategoryServiceImpl implements RootService<Category> {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    @Autowired
    CategoryDaoImpl categoryDao;

    @Override
    public Category save(Category category) {
        return null;
    }

    public Category save(String nameCategory, String codeCategory){
        Category category = new Category();
        category.setNameCategory(nameCategory);
        category.setCodeCategory(codeCategory);
        Date dateNow = new Date();
        String createdOn = SIMPLE_DATE_FORMAT.format(dateNow);
        String modifiedOn=SIMPLE_DATE_FORMAT.format(dateNow);
        category.setCreatedOn(createdOn);
        category.setModifiedOn(modifiedOn);
        categoryDao.save(category);
        return category;
    }


    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public Category updateById(Category category, int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
    public void deleteByCodeCategory(String code){
        categoryDao.deleteByCodeCategory(code);
    }

    public  List<String> findAllCodeCategories(){
       return  categoryDao.findAllCodeCategories();
    }
}

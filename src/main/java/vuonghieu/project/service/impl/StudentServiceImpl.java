package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.StudentsDaoImpl;
import vuonghieu.project.entity.Students;
import vuonghieu.project.service.RootService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class StudentServiceImpl implements RootService<Students> {

    @Autowired
    StudentsDaoImpl studentsDao;

    @Override
    public Students save(Students students) {
        return null;
    }

    @Override
    public List<Students> findAll() {
        return studentsDao.findAll();
    }

    @Override
    public Students findById(int mssv) {
        return studentsDao.findById(mssv);
    }

    @Override
    public Students updateById(Students students, int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {}

    public List<Students> findStudentByMssvs(List<Integer>students){
        return studentsDao.findStudentByMssvs(students);
    }
}

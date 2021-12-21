package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.MajorDaoImpl;
import vuonghieu.project.entity.Major;
import vuonghieu.project.service.RootService;

import java.util.List;

@Component
public class MajorServiceImpl implements RootService<Major> {

    @Autowired
    MajorDaoImpl majorDao;
 

    @Override
    public Major save(Major major) {
        return majorDao.save(major);
    }

    @Override
    public List<Major> findAll() {
        return majorDao.findAll();
    }

    @Override
    public Major findById(int id) {
        return null;
    }

    @Override
    public Major updateById(Major major, int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}

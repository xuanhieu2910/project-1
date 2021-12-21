package vuonghieu.project.service;

import java.util.List;

public interface RootService<T>{
    T save(T t);
    List<T> findAll();
    T findById(int id);
    T updateById(T t,int id);
    void deleteById(int id);
}

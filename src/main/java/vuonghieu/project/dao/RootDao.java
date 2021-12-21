package vuonghieu.project.dao;
import java.util.*;
public interface RootDao<T>{
    T save(T t);
    List<T> findAll();
    T findById(String id);
    T updateById(T t,String id);
    void deleteById(String id);
}

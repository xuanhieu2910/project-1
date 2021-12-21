package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.BookBorrowDaoImpl;
import vuonghieu.project.dto.BookBorrowDTO;
import java.util.*;
@Component
public class BookBorrowServiceImpl {

    @Autowired
    BookBorrowDaoImpl bookBorrowDao;



    public List<BookBorrowDTO> findAllBookBorrowEnable(){
            return bookBorrowDao.findAllBookBorrowEnable();
    }
}

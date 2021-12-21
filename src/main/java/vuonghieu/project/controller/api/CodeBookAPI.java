package vuonghieu.project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuonghieu.project.dto.BookBorrowDTO;
import vuonghieu.project.service.impl.BookBorrowServiceImpl;
import java.util.*;
@RestController
@RequestMapping("/api/code_book_child")
public class CodeBookAPI {

    @Autowired
    BookBorrowServiceImpl bookBorrowService;

    @GetMapping
    public List<BookBorrowDTO> findAllBookBorrowDTO(){return bookBorrowService.findAllBookBorrowEnable();}
}

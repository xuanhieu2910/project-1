package vuonghieu.project.controller.api;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vuonghieu.project.dto.AddNewQuantityBook;
import vuonghieu.project.dto.BookDTO;
import vuonghieu.project.dto.InventoryDTO;
import vuonghieu.project.entity.Book;
import vuonghieu.project.mapper.mapperDto.impl.BookMapperDTO;
import vuonghieu.project.mapper.mapperDto.impl.InventoryMapperDTO;
import vuonghieu.project.service.impl.BookServiceImpl;
import vuonghieu.project.service.impl.CodeBookServiceImpl;
import vuonghieu.project.service.impl.FileUploadService;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
@RestController
@RequestMapping("/api/book")
public class BookAPI {


    @Autowired
    BookMapperDTO bookMapperDTO;


    @Autowired
    BookServiceImpl bookService;

    @Autowired
    CodeBookServiceImpl codeBookService;

    @Autowired
    InventoryMapperDTO inventoryMapperDTO;

    @Autowired



    /*Get All Book in Inventory*/
    @GetMapping("/getAll")
    public List<InventoryDTO> findAllBooks(){
        return bookService.findAllBookDTOS();
    }

    /*Get book by codeBook*/
    @GetMapping("/{codeBook}")
    public InventoryDTO getBookByCodeBook(@PathVariable("codeBook") String codeBook){

        Book book  = bookService.findBookByCodeBook(codeBook);
        InventoryDTO inventoryDTO  =inventoryMapperDTO.convertBookToDTOS(book);
        return inventoryDTO;
    }


    /*Save book*/
    @PostMapping("/save")
    public void createNewBook(@RequestBody  BookDTO bookDTO){
        Book book  = bookMapperDTO.convertBookDTOtoBook(bookDTO);
        bookService.save(book);
        return;
    }

    /*Delete book*/
    @DeleteMapping("/delete/{codeBook}")
    public void deleteBookByCodeBook(@PathVariable("codeBook") String codeBook){
        codeBookService.deleteCodeBookChildByCodeBookParent(codeBook);
        bookService.deleteBookByCodeBook(codeBook);
    }

    /*Update book*/
    @PutMapping("/update/{codeBook}")
    public void updateBookByCodeBook(@PathVariable("codeBook") String codeBook,@RequestBody BookDTO bookDTO){
        Book book = bookMapperDTO.convertBookDTOtoBook(bookDTO);
        bookService.updateBookByCodeBook(codeBook,book);
        return;
    }


    @PostMapping("/update/addNewBook/{codeBook}")
    public void addNewBookByCodeBook(@PathVariable("codeBook")String codeBook,@RequestBody AddNewQuantityBook addNewQuantityBook){
        bookService.addNewQuantityBook(addNewQuantityBook.getCodeBook(), addNewQuantityBook.getNewQuantity());
    }

    @PostMapping("/create-new-book/files")
    public void createNewBookFiles(@RequestParam("file") MultipartFile file){
        bookService.createNewBookByFileExcel(file);
    }


}

package vuonghieu.project.mapper.mapperDto.impl;

import org.springframework.stereotype.Component;
import vuonghieu.project.dto.BookDTO;
import vuonghieu.project.entity.Book;
@Component
public class BookMapperDTO {


    public Book convertBookDTOtoBook(BookDTO bookDTO){
        Book book = new Book();
        book.setCodeBook(bookDTO.getCodeBook());
        book.setNameBook(bookDTO.getNameBook());
        book.setCodeCategory(bookDTO.getCodeCategory());
        book.setCodeMajor(bookDTO.getCodeMajor());
        book.setAuthor(bookDTO.getAuthor());
        book.setCompany(bookDTO.getCompany());
        book.setQuantity(bookDTO.getQuantity());
        book.setSales(bookDTO.getQuantity());
        book.setPrice(bookDTO.getPrice());
        book.setDescription(bookDTO.getDescription());
        return  book;
    }

}

package vuonghieu.project.service.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vuonghieu.project.dao.impl.BookDaoImpl;
import vuonghieu.project.dao.impl.CodeBookDaoImpl;
import vuonghieu.project.dto.BookExcel;
import vuonghieu.project.dto.InventoryDTO;
import vuonghieu.project.entity.Book;
import vuonghieu.project.entity.Category;
import vuonghieu.project.entity.CodeBook;
import vuonghieu.project.entity.Major;
import vuonghieu.project.mapper.mapperDto.impl.InventoryMapperDTO;
import vuonghieu.project.service.RootService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BookServiceImpl implements RootService<Book> {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
    public List<Category>categories;
    public List<Major>majors;
    public List<Book>books;


    @Autowired
    BookDaoImpl bookDao;

    @Autowired
    CodeBookServiceImpl codeBookService;


    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    MajorServiceImpl majorService;


    @Autowired
    InventoryMapperDTO inventoryMapperDTO;


    @Autowired
    CodeBookServiceImpl codeBookChildServiceImpl;

    @Autowired
    CodeBookDaoImpl codeBookDao;


    @Autowired
    FileUploadService fileUploadService;



    @Override
    public Book save(Book book){
        Date dateNow = new Date();
        String createdOn = SIMPLE_DATE_FORMAT.format(dateNow);
        String modifiedOn = SIMPLE_DATE_FORMAT.format(dateNow);
        try {
            book.setCreatedOn(SIMPLE_DATE_FORMAT.parse(createdOn));
            book.setModifiedOn(SIMPLE_DATE_FORMAT.parse(modifiedOn));
            book.setSales(book.getQuantity());
            book.setInventory(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        bookDao.save(book);
        codeBookService.save(book,book.getQuantity());
        return null;
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public Book updateById(Book book, int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    public void deleteBookByCodeBook(String codeBook){
        bookDao.deleteBookByCodeBook(codeBook);
    }

    public void updateBookByCodeBook(String codeBook, Book book){
        Date dateNowModifiedOn = new Date();
        String dateFormatModifiedOn = SIMPLE_DATE_FORMAT.format(dateNowModifiedOn);
        try {
            Date modifiedOn = SIMPLE_DATE_FORMAT.parse(dateFormatModifiedOn);
            book.setModifiedOn(modifiedOn);
        }catch(Exception e){
            e.printStackTrace();
        }
        bookDao.updateBookByCodeBook(codeBook,book);
    }




    public List<InventoryDTO> findAllBookDTOS(){
        List<Category> categories = categoryService.findAll();
        List<Major> majors = majorService.findAll();
        List<Book> books = bookDao.findAll();
        List<InventoryDTO>inventoryDTOS = inventoryMapperDTO.inventoryDTOS(books);
            for (int i = 0; i < inventoryDTOS.size(); i++) {
                inventoryDTOS.get(i).setNameCategory(getNameCategory(categories, books.get(i).getCodeCategory()));
                inventoryDTOS.get(i).setNameMajor(getNameMajor(majors, books.get(i).getCodeMajor()));
            }
        return inventoryDTOS;
    }

    public  String getNameCategory(List<Category>categories,String codeCategory){
        for (Category category: categories) {
            if(category.getCodeCategory().equals(codeCategory)){
                return category.getNameCategory();
            }
        }
        return null;
    }

    public String getNameMajor(List<Major>majors , String codeMajor){
        for(Major major:majors){
            if(major.getCodeMajor().equals(codeMajor)){
                return major.getNameMajor();
            }
        }
        return null;

    }

    public Book findBookByCodeBook(String codeBook) {
        if (bookDao.findBookByCodeBook(codeBook) == null) {
            return null;
        }
            return bookDao.findBookByCodeBook(codeBook);
    }


    public void addNewQuantityBook(String codeBookParent,int quantity){
        int quantityCodeBookChild  = codeBookChildServiceImpl.getCountByCodeBook(codeBookParent);
        int result = quantityCodeBookChild + quantity;
        Book book = findBookByCodeBook(codeBookParent);
        bookDao.updateQuantity(book.getCodeBook(),book.getQuantity()+quantity,book.getSales()+quantity);
        String genCodeChild = book.getCodeCategory()+"-"+book.getCodeMajor()+"-"+book.getCodeBook()+"-";
        for(int i=quantityCodeBookChild+1; i<=result;i++){
            CodeBook codeBook = new CodeBook();
            codeBook.setCodeBook(codeBookParent);
            codeBook.setCodeBookChild(genCodeChild+i);
            codeBookDao.save(codeBook);
        }
    }

    public void handleQuantitySales(List<String>codeBookParent){
        for (int i=0;i<codeBookParent.size();i++){
            String codeBook = codeBookParent.get(i);
            int quantitySales = bookDao.getQuantitySales(codeBook);
            int quantitySalesAfter = quantitySalseAfter(quantitySales);
            bookDao.updateQuantitySales(quantitySalesAfter,codeBook);
        }
    }

    public int quantitySalseAfter(int sales){
        return  sales-1;
    }





    public void updateQuantitySalesAfterPayment(String codeBook,int quantitySales){
            bookDao.updateQuantitySalesAfterPayment(codeBook,quantitySales);
    }


    public void createNewBookByFileExcel(MultipartFile multipartFile){
        List<BookExcel>bookListExcel = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            String decription=null;
            for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
                XSSFRow row = sheet.getRow(i);
                if(!String.valueOf(row.getCell(0)).equals("")) {
                    decription = String.valueOf(row.getCell(9));
                    BookExcel book = new BookExcel();
                    book.setCodeBook(row.getCell(1).getStringCellValue());
                    book.setNameBook(row.getCell(2).getStringCellValue());
                    book.setNameCategory(row.getCell(3).getStringCellValue());
                    book.setNameMajor(row.getCell(4).getStringCellValue());
                    book.setAuthor(row.getCell(5).getStringCellValue());
                    book.setNameCompany(row.getCell(6).getStringCellValue());
                    book.setPrice((float) row.getCell(7).getNumericCellValue());
                    book.setQuantity((int) row.getCell(8).getNumericCellValue());
                    book.setDescription(decription);
                    bookListExcel.add(book);
                }
                else{
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createBookExcelToBook(bookListExcel);
    }


    public void createBookExcelToBook(List<BookExcel>bookExcels){
        for(int i=0;i<bookExcels.size();i++){
            if(checkCodeBook(bookExcels.get(i).getCodeBook())){ // Có.
                addNewQuantityBook(bookExcels.get(i).getCodeBook(),bookExcels.get(i).getQuantity());
            }
            else{                                                     // Chưa có.
                Book book = new Book();
                Date dateNow = new Date();
                String dateFormatNow = SIMPLE_DATE_FORMAT.format(dateNow);
                try {
                    Date createdOn = SIMPLE_DATE_FORMAT.parse(dateFormatNow);
                    Date modifiedOn = SIMPLE_DATE_FORMAT.parse(dateFormatNow);
                    book.setCreatedOn(createdOn);
                    book.setModifiedOn(modifiedOn);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                book.setCodeBook(bookExcels.get(i).getCodeBook());
                book.setNameBook(bookExcels.get(i).getNameBook());
                book.setAuthor(bookExcels.get(i).getAuthor());
                book.setCompany(bookExcels.get(i).getNameCompany());
                book.setQuantity(bookExcels.get(i).getQuantity());
                book.setSales(bookExcels.get(i).getQuantity());
                book.setPrice(bookExcels.get(i).getPrice());
                book.setInventory(0);
                book.setDescription(bookExcels.get(i).getDescription());
                book.setCodeMajor(getCodeMajor(bookExcels.get(i).getNameMajor()));
                book.setCodeCategory(getCodeCategory(bookExcels.get(i).getNameCategory()));
                // Save book trước -> Save code book
                 bookDao.save(book);
                 //-> Save code book
                codeBookService.save(book,book.getQuantity());
            }
        }
    }

    public boolean checkCodeBook(String codeBookExcel){
        books = bookDao.findAll();
        for(Book book:books){
            if(book.getCodeBook().equals(codeBookExcel)){
                return true;
            }
        }
        return false;
    }

    public String getCodeMajor(String nameMajor){
        majors = majorService.findAll();
        for (Major major:majors) {
            if(major.getNameMajor().equals(nameMajor)){
                return major.getCodeMajor();
            }
        }
        return null;
    }

    public String getCodeCategory(String category){
        categories = categoryService.findAll();
        for (Category category1:categories) {
            if(category1.getNameCategory().equals(category)){
                return category1.getCodeCategory();
            }
        }
        return null;
    }
    

}

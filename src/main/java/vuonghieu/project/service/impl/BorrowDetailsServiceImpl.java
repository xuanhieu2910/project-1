package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.BorrowDetailsDaoImpl;
import vuonghieu.project.dto.*;
import vuonghieu.project.entity.Book;
import vuonghieu.project.entity.Borrow;
import vuonghieu.project.entity.BorrowDetails;
import vuonghieu.project.entity.CodeBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Component
public class BorrowDetailsServiceImpl {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");

    @Autowired
    BorrowDetailsDaoImpl borrowDetailsDao;

    @Autowired
    BorrowServiceImpl borrowService;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    CodeBookServiceImpl codeBookService;



    public void saveBorrowDetails(CreateNewBorrowDTO createNewBorrowDTO, Borrow borrow) {
        List<BorrowDetails>borrowDetailsList = new ArrayList<>();
        List<String>codeBooksChildren = createNewBorrowDTO.getCodeBooksBorrowed();
        String codeBorrow = createNewBorrowDTO.getCodeBorrow();
        Date createdOn = borrow.getCreatedOn();
        Calendar cal = Calendar.getInstance();
        cal.setTime(createdOn);
        cal.add(Calendar.DATE, 90);
        String dateAfterThreeMonth = SIMPLE_DATE_FORMAT.format(cal.getTime());
        try {
            cal.setTime(SIMPLE_DATE_FORMAT.parse(dateAfterThreeMonth));
        }catch (Exception e){
            e.printStackTrace();
        }
        Date expiry = cal.getTime();

        for(int i=0;i<codeBooksChildren.size();i++){
            BorrowDetails borrowDetails = new BorrowDetails();
            borrowDetails.setCodeBorrow(codeBorrow);
            borrowDetails.setCodeBookChild(codeBooksChildren.get(i));
            borrowDetails.setCreatedOn(createdOn);
            borrowDetails.setModifiedOn(createdOn);
            borrowDetails.setExpiry(expiry);
            borrowDetails.setDatePayment(null);
            borrowDetails.setPrice((float) 0);
            borrowDetails.setBorrowed(0);
            borrowDetailsList.add(borrowDetails);
        }
        borrowDetailsDao.saveBorrowDetails(borrowDetailsList);
        return;
    }



    public List<BorrowDetails> findAllBorrowDetails(){
        return  borrowDetailsDao.findAllBorrowDetails();
    }

    public BorrowDetails findBorrowDetailsByCode(String code){
        return null;
    }

    public void updateBorrowDetailsByCodeBook(BorrowDetails borrowDetails,String codeBook){
        return;
    }

    public void deleteBookBorrowDetails(String codeBorrow,String codeBook){
        //Delete in borrow details
        borrowDetailsDao.deleteBookBorrowDetails(codeBorrow,codeBook);
        //Update code_book with codeBook;
        codeBookService.updateCodeBook(codeBook);
        //update quantity borrow
        Integer totalQuantityAfter = borrowService.getQuantityTotalByCodeBorrow(codeBorrow)-1;
        borrowService.updateQuantityTotal(codeBorrow,totalQuantityAfter);
        //Update quantity sales after delete book borrow
        String codeBookParent = codeBookService.findByStringCodeBookChild(codeBook);
        Book book = bookService.findBookByCodeBook(codeBookParent);
        int quantitySalesAfterPayment = book.getSales()+1;
        bookService.updateQuantitySalesAfterPayment(book.getCodeBook(),quantitySalesAfterPayment);

    }

    public List<BorrowDetails> getAllNotBorrowedByCodeBorrow(int mssv){
       List<String>codeBorrows= borrowService.findCodeBooksNotPaymentByMssv(mssv);

       List<BorrowDetails>borrowDetails = borrowDetailsDao.getAllNotBorrowedByCodeBorrow(codeBorrows);


       Date dateNow = new Date();
        for (BorrowDetails details:borrowDetails){
            if(details.getExpiry().compareTo(dateNow)<0){
                int ticket = (int) ((dateNow.getTime()-details.getExpiry().getTime())/(24*60*60*1000));
                details.setPrice((float) (ticket*1000));
            }
        }
       return borrowDetails;
    }





    public void paymentBorrow(int mssv, List<PaymentBorrowDTO>paymentBorrowDTOS){

        Date modifiedOn = new Date();
        Date datePayment = new Date();
        Date now = new Date();
        String dateNowString = SIMPLE_DATE_FORMAT.format(now);
        try {
            modifiedOn = SIMPLE_DATE_FORMAT.parse(dateNowString);
            datePayment= SIMPLE_DATE_FORMAT.parse(dateNowString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //DONE update borrow-details.
        for(int i=0;i<paymentBorrowDTOS.size();i++){
            String codeBorrow = paymentBorrowDTOS.get(i).getCodeBorrow();
            for(PaymentCodeBookDTO paymentCodeBookDTO : paymentBorrowDTOS.get(i).getCodeBookss()){
                String codeBook = paymentCodeBookDTO.getCodeBook();
                Float price = Float.parseFloat(paymentCodeBookDTO.getPrice());
                borrowDetailsDao.paymentBorrowByCodeBorrow(codeBorrow,codeBook,modifiedOn,datePayment,price);
            }
        }
        //Handel update quantity sales book.
        for(int i=0;i<paymentBorrowDTOS.size();i++){
            for(PaymentCodeBookDTO paymentCodeBookDTO: paymentBorrowDTOS.get(i).getCodeBookss()){
                // Từ codeBookChild-> codeBookParert - > Book;
                String codeBook = codeBookService.findByStringCodeBookChild(paymentCodeBookDTO.getCodeBook());
                Book book = bookService.findBookByCodeBook(codeBook);
                int quantitySalesAfterPayment = book.getSales()+1;
                bookService.updateQuantitySalesAfterPayment(book.getCodeBook(),quantitySalesAfterPayment);
            }
        }
        //Handel update status borrow.
        for(int i=0;i<paymentBorrowDTOS.size();i++){
            String codeBorrow = paymentBorrowDTOS.get(i).getCodeBorrow();
            if(getCountBorrowDetailsByCodeBorrow(codeBorrow)){
                borrowService.updateStatusByCodeBorrow(codeBorrow);
            }
        }
        //Handel update code book payment borrow;
        for(int i=0;i<paymentBorrowDTOS.size();i++){
            List<String>codeBookChildren = new ArrayList<>();
            for (PaymentCodeBookDTO paymentCodeBook:paymentBorrowDTOS.get(i).getCodeBookss()) {
                codeBookChildren.add(paymentCodeBook.getCodeBook());
            }
            codeBookService.updateCodeBookPaymentBorrow(codeBookChildren);
        }
    }


    // get count borrow details by code borrow to set status borrow
    public Boolean getCountBorrowDetailsByCodeBorrow(String codeBorrow){
        Integer count = borrowDetailsDao.getCountBorrowDetailsByCodeBorrow(codeBorrow);
        if(count.equals(0)){
            return true;
        }
        else{
            return false;
        }
    }

    public void updateNewCodeBook(String codeBorrow,String codeBookRequest){
        String[] codeBook = codeBookRequest.split(" ");
        String newCodeBook = codeBook[0];
        String oldCodeBook = codeBook[1];
        //update in borrow-details
        Date dateNow = new Date();
        String dateStringNow = SIMPLE_DATE_FORMAT.format(dateNow);
        Date modifiedOn = null;
        try {
            modifiedOn = SIMPLE_DATE_FORMAT.parse(dateStringNow);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        borrowDetailsDao.updateNewCodeBook(codeBorrow,newCodeBook,oldCodeBook,modifiedOn);
        //update in code_book
        codeBookService.updateCodeBook(newCodeBook,oldCodeBook);
    }
    public Integer sumBorrow(int mssv){
        return borrowDetailsDao.sumTotalSalesByMssv(mssv);
    }

    public Integer sumTotalBorrowedByMssv(int mssv){
        return borrowDetailsDao.sumTotalBorrowedByMssv(mssv);
    }
    public Integer sumTotalUnBorrowedByMssv(int mssv){
        return borrowDetailsDao.sumTotalUnBorrowedByMssv(mssv);
    }
    public Float sumPriceByMssv(int mssv) {
        return borrowDetailsDao.sumPriceByMssv(mssv);
    }


    public List<HistoryBorrowDetailsDTO>historyBorrowByMssv(int mssv){
        List<BorrowDetails> borrowDetails = borrowDetailsDao.historyBorrowByMssv(mssv);
        List<HistoryBorrowDetailsDTO>historyBorrowDetailsDTOS = new ArrayList<>();
        for (BorrowDetails borrow:borrowDetails) {
            HistoryBorrowDetailsDTO historyBorrowDetailsDTO = new HistoryBorrowDetailsDTO();
            historyBorrowDetailsDTO.setNameBook(borrowDetailsDao.getNameBookByCodeBookBorrow(borrow.getCodeBookChild()));
            historyBorrowDetailsDTO.setCodeBook(borrow.getCodeBookChild());
            historyBorrowDetailsDTO.setCreatedOn(borrow.getCreatedOn());
            historyBorrowDetailsDTO.setExpiry(borrow.getExpiry());
            historyBorrowDetailsDTO.setDatePayment(borrow.getDatePayment());
            historyBorrowDetailsDTO.setPrice(borrow.getPrice());
            historyBorrowDetailsDTOS.add(historyBorrowDetailsDTO);

        }
        return historyBorrowDetailsDTOS;
    }
}

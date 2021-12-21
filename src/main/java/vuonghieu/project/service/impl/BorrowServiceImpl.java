package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.BorrowDaoImpl;
import vuonghieu.project.dto.CreateNewBorrowDTO;
import vuonghieu.project.entity.Borrow;
import vuonghieu.project.service.RootService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class BorrowServiceImpl {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");

    @Autowired
    BorrowDaoImpl borrowDao;

    @Autowired
    BorrowDetailsServiceImpl borrowDetailsService;

    @Autowired
    CodeBookServiceImpl codeBookService;

    @Autowired
    BookServiceImpl bookService;

    public void save(CreateNewBorrowDTO createNewBorrowDTO) {
        Borrow borrow = new Borrow();
        String codeBorrow = createNewBorrowDTO.getCodeBorrow();
        int mssv = createNewBorrowDTO.getMssv();
        int totalQuantity = createNewBorrowDTO.getCodeBooksBorrowed().size();
        Date now = new Date();
        String dateCreated = SIMPLE_DATE_FORMAT.format(now);
        Date createdOn = null;
        try {
            createdOn = SIMPLE_DATE_FORMAT.parse(dateCreated);
        }catch (Exception e){
            e.printStackTrace();
        }
        borrow.setIdBorrow(codeBorrow);
        borrow.setMssv(mssv);
        borrow.setTotalQuantity(totalQuantity);
        borrow.setCreatedOn(createdOn);
        borrow.setStatus("NOT");
        borrowDao.save(borrow);
        borrowDetailsService.saveBorrowDetails(createNewBorrowDTO,borrow);
        codeBookService.updateCodeBookBorrow(createNewBorrowDTO.getCodeBooksBorrowed());
        List<String> codeBookParent = codeBookService.getCodeBookByCodeChild(createNewBorrowDTO.getCodeBooksBorrowed());
        bookService.handleQuantitySales(codeBookParent);
        return;
    }


    public List<Borrow> findAllBorrow() {
        return   borrowDao.findAll();
    }

    public List<Integer> getAllMssvWithStatusNot(){
        return  borrowDao.getAllMssvWithStatusNot();
    }

    public Borrow findByMssv(int mssv) {
        return null;
    }


    public Borrow updateByCodeBorrow(Borrow borrow,String codeBorrow) {
        return null;
    }


    public void deleteByCodeBorrow(String codeBorrow) {

    }
    public Integer findQuantityCodeBorrowByMssv(int mssv){
        return borrowDao.getCountBorrowByMssv(mssv);
    }

    public List<String> findCodeBooksNotPaymentByMssv(int mssv){
        return borrowDao.findCodeBooksNotPaymentByMssv(mssv);
    }

    public void updateStatusByCodeBorrow(String codeBorrow){
        borrowDao.updateStatusByCodeBorrow(codeBorrow);
    }

    public Integer getQuantityTotalByCodeBorrow(String codeBorrow){
        return borrowDao.getTotalQuantityByCodeBorrow(codeBorrow);
    }

    public void updateQuantityTotal(String codeBorrow,Integer quantity){
        borrowDao.updateQuantityTotal(codeBorrow,quantity);
    }

    public List<Integer>listStudentBorrowed(){
       return borrowDao.listStudentBorrowed();
    }
}

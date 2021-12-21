package vuonghieu.project.controller.api;


import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import vuonghieu.project.dto.HistoryBorrowDetailsDTO;
import vuonghieu.project.dto.PaymentBorrowDTO;
import vuonghieu.project.entity.BorrowDetails;
import vuonghieu.project.service.impl.BorrowDetailsServiceImpl;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
@RestController
@RequestMapping("/api/borrow-details")
public class BorrowDetailsAPI {

    @Autowired
    BorrowDetailsServiceImpl borrowDetailsService;






    @GetMapping("/{mssv}")
    public List<BorrowDetails> findAll(@PathVariable("mssv") int mssv){

       return  borrowDetailsService.getAllNotBorrowedByCodeBorrow(mssv);
    }


    @PostMapping(value="/payment-borrow/{mssv}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void paymentBorrow(@PathVariable("mssv")int mssv, @RequestBody List<PaymentBorrowDTO>paymentBorrowDTOS){
        borrowDetailsService.paymentBorrow(mssv,paymentBorrowDTOS);
    }

    @PutMapping("/update-book-borrow/{codeBorrow}/{codeBook}")
    public void updateNewBookBorrowByCodeBook(@PathVariable("codeBorrow")String codeBorrow, @PathVariable("codeBook") String codeBook){
        borrowDetailsService.updateNewCodeBook(codeBorrow,codeBook);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCodeBookBorrow(@RequestParam(value="codeBorrow")String codeBorrow, @RequestParam("codeBook")String codeBook){
        borrowDetailsService.deleteBookBorrowDetails(codeBorrow,codeBook);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/history-details")
    public List<HistoryBorrowDetailsDTO> historyBorrow(@RequestParam("mssv")int mssv){
       return borrowDetailsService.historyBorrowByMssv(mssv);
    }

}

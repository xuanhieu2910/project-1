package vuonghieu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("/muon-tra-sach")
public class BorrowController {

    @RequestMapping
    public String borrow(){return "borrow";}


    @RequestMapping("/tao-moi-muon-sach")
    public String createNewBorrowBook(){return "createNewBorrow";}

    @RequestMapping("/hoan-tra/{mssv}")
    public String borrowedDetails(@PathVariable("mssv")int mssv){return "borrow-details";}

    @RequestMapping("/chinh-sua-muon-tra/{codeBorrow}/{codeBookChild}")
    public String updateBorrowDetailByCodeBook(){
        return "updateCodeBorrow";
    }
}

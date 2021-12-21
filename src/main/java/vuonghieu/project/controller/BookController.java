package vuonghieu.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class BookController {

    @RequestMapping("/sach")
    public String books(){return "managerBooks";}

    @RequestMapping("/ma-sach")
    public String codeBook(){return "createCodeBook";}

    @RequestMapping("/the-loai-sach")
    public String category(){return "category";}

    @RequestMapping("/sach/tao-moi-sach")
    public String createNewBook(){return "createNewBook";}

    @RequestMapping("/sach/chinh-sua-sach/{codeBook}")
    public String updateBookById(@PathVariable("codeBook") String codeBook){
        return "updateBook";
    }

    @RequestMapping("/sach/chinh-sua-sach/them-sach/{codeBook}")
    public String addNewQuantityBook(@PathVariable("codeBook")String codeBook){return "createNewQuantityBook";}

//    @RequestMapping(value = "/sach/tao-moi-sach/theo-danh-sach",method = RequestMethod.POST)
//    public

}

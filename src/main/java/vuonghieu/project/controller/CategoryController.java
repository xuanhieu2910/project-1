package vuonghieu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/the-loai-sach")
public class CategoryController {

    @RequestMapping("/chinh-sua-danh-muc-sach/{codeBook}")
    public String updateCategory(@PathVariable("codeBook") String codeBook){
        return "updateCategory";
    }

    @RequestMapping("/tao-moi-the-loai-sach")
    public String createNewCategory(){
        return "createNewCategory";
    }


}

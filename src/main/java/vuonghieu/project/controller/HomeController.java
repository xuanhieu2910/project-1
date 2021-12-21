package vuonghieu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping
    public String homePage(){
        return "redirect:/login";
    }

    @RequestMapping("/trang-chu")
    public String homePage2(){
        return "index";
    }

}

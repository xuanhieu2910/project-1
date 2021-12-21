package vuonghieu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/doc-gia")
public class StudentsController {

    @RequestMapping
    public String students(){
        return "manager-student";
    }

    @RequestMapping("/lich-su-muon-tra")
    public String historyStudents(@RequestParam("mssv")int mssv){
        return "history-borrow";
    }
}

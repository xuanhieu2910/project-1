package vuonghieu.project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuonghieu.project.dto.StudentChairDTO;
import vuonghieu.project.service.impl.StudentChairServiceImpl;

import java.util.List;
@RestController
@RequestMapping("/student-borrowed")
public class StudentChairAPI {

    @Autowired
    StudentChairServiceImpl studentChairService;

    @GetMapping
    public List<StudentChairDTO> getStudentsBorrowed(){
        return studentChairService.studentBorrowed();
    }
}

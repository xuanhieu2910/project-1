package vuonghieu.project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vuonghieu.project.dto.StudentBorrowDTO;
import vuonghieu.project.dto.StudentChairDTO;
import vuonghieu.project.entity.Students;
import vuonghieu.project.mapper.mapperDto.impl.StudentMapperDTO;
import vuonghieu.project.service.impl.StudentServiceImpl;

import java.util.*;
@RestController
@RequestMapping("/api/students")
public class StudentsAPI {

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    StudentMapperDTO studentMapperDTO;

    @GetMapping
    public List<StudentBorrowDTO> findAllStudentsBorrow(){
        return studentMapperDTO.covertStudentBorrowDtoFromStudents(studentService.findAll());
    }

    @GetMapping("/{mssv}")
    public Students findStudentByMssv(@PathVariable("mssv") int mssv){
        return studentService.findById(mssv);
    }



}

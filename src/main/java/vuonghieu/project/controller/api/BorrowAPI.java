package vuonghieu.project.controller.api;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vuonghieu.project.dto.BorrowDTO;
import vuonghieu.project.dto.CreateNewBorrowDTO;
import vuonghieu.project.entity.Students;
import vuonghieu.project.mapper.mapperDto.impl.BorrowMapperDTO;
import vuonghieu.project.service.impl.BorrowDetailsServiceImpl;
import vuonghieu.project.service.impl.BorrowServiceImpl;
import vuonghieu.project.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/api/borrow")
public class BorrowAPI {

    @Autowired
    BorrowServiceImpl borrowService;

    @Autowired
    BorrowDetailsServiceImpl borrowDetailsService;

    @Autowired
    BorrowMapperDTO borrowMapperDTO;


    @Autowired
    StudentServiceImpl studentService;

    @PostMapping("/save")
    public void saveNewBorrow(@RequestBody CreateNewBorrowDTO createNewBorrowDTO){
        borrowService.save(createNewBorrowDTO);
    }


    @GetMapping("/number-borrow/{mssv}")
    public int getCountBorrowByMssv(@PathVariable("mssv") int mssv){
        return borrowService.findQuantityCodeBorrowByMssv(mssv);
    }

    @GetMapping
    public List<BorrowDTO> findAllBorrow(){
       List<Integer> borrows = borrowService.getAllMssvWithStatusNot();
       List<Students>students = studentService.findAll();
       List<BorrowDTO>borrowDTOS = borrowMapperDTO.covertBorrowToBorrowDTO(borrows,students);
        return borrowDTOS;
    }



}

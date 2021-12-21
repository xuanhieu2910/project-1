package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vuonghieu.project.dto.StudentChairDTO;
import vuonghieu.project.entity.Students;

import java.util.*;
@Component
public class StudentChairServiceImpl {

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    BorrowServiceImpl borrowService;

    @Autowired
    BorrowDetailsServiceImpl borrowDetailsService;

    public List<StudentChairDTO>studentBorrowed(){
        List<StudentChairDTO>studentChairDTOS = new ArrayList<>();
        List<Integer>listStudentsBorrowed = borrowService.listStudentBorrowed();
        List<Students>students = studentService.findStudentByMssvs(listStudentsBorrowed);
        for(int i=0;i<listStudentsBorrowed.size();i++){
            int totalBorrow = borrowDetailsService.sumBorrow(listStudentsBorrowed.get(i));//Tổng số đã mượn
            int totalUnBorrow=borrowDetailsService.sumTotalUnBorrowedByMssv(listStudentsBorrowed.get(i));//Tổng số chưa trả
            int borrowed=borrowDetailsService.sumTotalBorrowedByMssv(listStudentsBorrowed.get(i)); // tổng số đã trả
            float totalPrice = borrowDetailsService.sumPriceByMssv(listStudentsBorrowed.get(i));// Tổng giá tiền đã trả
            StudentChairDTO studentChairDTO = new StudentChairDTO();
            for (Students student:students){
                if(student.getMssv().equals(listStudentsBorrowed.get(i))){
                    studentChairDTO.setMssv(student.getMssv());
                    studentChairDTO.setNameStudent(student.getNameStudent());
                    studentChairDTO.setSumBorrowed(totalBorrow);
                    studentChairDTO.setSumPaymentBorrowed(borrowed);
                    studentChairDTO.setSumDontPaymentBorrowed(totalUnBorrow);
                    studentChairDTO.setSumPrice(totalPrice);
                    studentChairDTOS.add(studentChairDTO);
                    break;
                }
            }
        }
        return studentChairDTOS;
    }
}

package vuonghieu.project.mapper.mapperDto.impl;

import org.springframework.stereotype.Component;
import vuonghieu.project.dto.StudentBorrowDTO;
import vuonghieu.project.entity.Students;
import java.util.*;
@Component
public class StudentMapperDTO {



    public List<StudentBorrowDTO> covertStudentBorrowDtoFromStudents(List<Students> students){
        List<StudentBorrowDTO>studentBorrowDTOS = new ArrayList<>();
        for(Students student:students){
            StudentBorrowDTO studentBorrowDTO = new StudentBorrowDTO();
            studentBorrowDTO.setMssv(student.getMssv());
            studentBorrowDTO.setNameStudent(student.getNameStudent());
            studentBorrowDTO.setMajor(student.getMajor());
            studentBorrowDTO.setEmail(student.getEmail());
            studentBorrowDTO.setPhone(student.getPhone());
            studentBorrowDTOS.add(studentBorrowDTO);
        }
        return studentBorrowDTOS;
    }

}

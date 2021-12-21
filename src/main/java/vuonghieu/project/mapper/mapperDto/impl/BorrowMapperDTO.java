package vuonghieu.project.mapper.mapperDto.impl;

import org.springframework.stereotype.Component;
import vuonghieu.project.dto.BorrowDTO;
import vuonghieu.project.entity.Students;

import java.util.*;
@Component
public class BorrowMapperDTO {

    public List<BorrowDTO> covertBorrowToBorrowDTO(List<Integer> borrows, List<Students>students){
        List<BorrowDTO> borrowDTOS = new ArrayList<>();
        for(int i=0;i<borrows.size();i++){
            BorrowDTO borrowDTO = new BorrowDTO();
            borrowDTO.setMssv(borrows.get(i));
            borrowDTO.setStatus("NOT");
            for(int j =0;j<students.size();j++){
                if(students.get(j).getMssv().equals(borrows.get(i))){
                    borrowDTO.setNameStudent(students.get(j).getNameStudent());
                    break;
                }
            }
            borrowDTOS.add(borrowDTO);
        }
        return borrowDTOS;
    }

}

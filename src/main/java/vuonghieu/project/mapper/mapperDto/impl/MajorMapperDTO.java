package vuonghieu.project.mapper.mapperDto.impl;

import org.springframework.stereotype.Component;
import vuonghieu.project.dto.MajorDTO;
import vuonghieu.project.entity.Major;
import java.util.*;

@Component
public class MajorMapperDTO {


    public List<MajorDTO> convertMajorToMajorDTO(List<Major> majors){
        List<MajorDTO>majorDTOS = new ArrayList<>();
        for(Major major: majors){
            MajorDTO majorDTO = new MajorDTO(major.getCodeMajor(),major.getNameMajor());
            majorDTOS.add(majorDTO);
        }
        return majorDTOS;
    }
}

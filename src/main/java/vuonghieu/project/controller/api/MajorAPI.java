package vuonghieu.project.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vuonghieu.project.dto.MajorDTO;
import vuonghieu.project.mapper.mapperDto.impl.MajorMapperDTO;
import vuonghieu.project.service.impl.MajorServiceImpl;

import java.util.*;

@RestController
@RequestMapping("/api/majors")
public class MajorAPI {

    @Autowired
    MajorMapperDTO majorMapperDTO;


    @Autowired
    MajorServiceImpl majorService;


    @GetMapping
    public List<MajorDTO> findAllMajorDTO(){
        return majorMapperDTO.convertMajorToMajorDTO(majorService.findAll());
    }
}

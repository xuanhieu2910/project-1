package vuonghieu.project.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vuonghieu.project.dto.ChartDTO;

@RestController
@RequestMapping("/api/chart")
public class ChartAPI {

    @GetMapping
    public ResponseEntity filterTime(@RequestParam("time")String time){
        switch (time){
            case "oneDayAgo":
            case "sevenDaysAgo":
            case "oneMonthAgo":
            case "oneYearsAgo":
                break;
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

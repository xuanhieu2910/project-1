package vuonghieu.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vuonghieu.project.service.impl.BorrowDetailsServiceImpl;
import vuonghieu.project.service.impl.ExportFileHistoryBorrowService;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ExportFileController {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    BorrowDetailsServiceImpl borrowDetailsService;

    @RequestMapping(value = "/export-file",method = RequestMethod.GET)
    public void exportFileHistoryBorrowDetail(@RequestParam("mssv")int mssv, HttpServletResponse httpServletResponse) throws IOException {
            Date dateNow = new Date();
            String dateCurrent = SIMPLE_DATE_FORMAT.format(dateNow);
            httpServletResponse.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Lich-sua-muon-tra-"+mssv+"-"+dateCurrent+".xlsx";
            httpServletResponse.setHeader(headerKey,headerValue);
        ExportFileHistoryBorrowService exportFileHistoryBorrowService = new ExportFileHistoryBorrowService(borrowDetailsService.historyBorrowByMssv(mssv));
        exportFileHistoryBorrowService.export(httpServletResponse);
    }
}

package vuonghieu.project.service.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vuonghieu.project.dto.HistoryBorrowDetailsDTO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class ExportFileHistoryBorrowService {
    public String createdOn;
    public String modifiedOn;
    public String dateExpiry;
    public XSSFWorkbook xssfWorkbook;
    public XSSFSheet xssfSheet;
    public List<HistoryBorrowDetailsDTO>historyBorrowDetailsDTOS;



    public ExportFileHistoryBorrowService(List<HistoryBorrowDetailsDTO>historyBorrowDetailsDTOS){
        this.historyBorrowDetailsDTOS = historyBorrowDetailsDTOS;
            xssfWorkbook = new XSSFWorkbook();
    }


   private void writeHeaderLine(){
       xssfSheet = xssfWorkbook.createSheet("HistoryBorrow");
       Row row =   xssfSheet.createRow(0);
       CellStyle style = xssfWorkbook.createCellStyle();
       XSSFFont font = xssfWorkbook.createFont();
       font.setBold(true);
       font.setFontHeight(16);
       style.setFont(font);

       createCell(row,0,"STT",style);
       createCell(row,1,"Mã sách",style);
       createCell(row,2,"Tên sách",style);
       createCell(row,3,"Thời gian mượn",style);
       createCell(row,4,"Thời gian trả",style);
       createCell(row,5,"Hạn trả",style);
       createCell(row,6,"Tiền phạt",style);
   }

   private void createCell(Row row, int columnCount,Object value,CellStyle cellStyle){
        xssfSheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Integer){
            cell.setCellValue((Integer)value);
        }
        else if(value instanceof  Float){
            cell.setCellValue((Float)value);
        }
        else if(value instanceof  Double){
            cell.setCellValue((Double)value);
        }
        else if(value instanceof Boolean){
            cell.setCellValue((Boolean)value);
        }
        else if(value instanceof String){
            cell.setCellValue((String)value);
        }
        cell.setCellStyle(cellStyle);
   }

   private void writeDataLines(){
        int rowCount = 1;
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontHeight(14);
        cellStyle.setFont(font);
        for(int i=0;i<historyBorrowDetailsDTOS.size();i++){
            Row row = xssfSheet.createRow(rowCount++);
            int columnCount = 0;
            createdOn = String.valueOf(historyBorrowDetailsDTOS.get(i).getCreatedOn());
            modifiedOn = String.valueOf(historyBorrowDetailsDTOS.get(i).getDatePayment());
            dateExpiry = String.valueOf(historyBorrowDetailsDTOS.get(i).getExpiry());
            createCell(row,columnCount++,i,cellStyle);
            createCell(row,columnCount++,historyBorrowDetailsDTOS.get(i).getCodeBook(),cellStyle);
            createCell(row,columnCount++,historyBorrowDetailsDTOS.get(i).getNameBook(),cellStyle);
            createCell(row,columnCount++,createdOn,cellStyle);
            createCell(row,columnCount++,modifiedOn,cellStyle);
            createCell(row,columnCount++,dateExpiry,cellStyle);
            createCell(row,columnCount++,historyBorrowDetailsDTOS.get(i).getPrice(),cellStyle);
        }
   }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        xssfWorkbook.write(outputStream);
        outputStream.close();

    }

}

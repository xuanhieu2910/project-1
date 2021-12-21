package vuonghieu.project.dto;
import java.util.*;
public class StudentsDTO {
    private  int mssv;
    private String nameStudent;
    private int quantityBorrowed;
    private int quantityRefunded;
    private int amountRefunded;
    private int priceFined;

    private List<BorrowDetailsDTO> borrowDetailsDTOList;

    public StudentsDTO(int mssv, String nameStudent, int quantityBorrowed, int quantityRefunded, int amountRefunded, int priceFined) {
        this.mssv = mssv;
        this.nameStudent = nameStudent;
        this.quantityBorrowed = quantityBorrowed;
        this.quantityRefunded = quantityRefunded;
        this.amountRefunded = amountRefunded;
        this.priceFined = priceFined;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public int getQuantityBorrowed() {
        return quantityBorrowed;
    }

    public void setQuantityBorrowed(int quantityBorrowed) {
        this.quantityBorrowed = quantityBorrowed;
    }

    public int getQuantityRefunded() {
        return quantityRefunded;
    }

    public void setQuantityRefunded(int quantityRefunded) {
        this.quantityRefunded = quantityRefunded;
    }

    public int getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(int amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public int getPriceFined() {
        return priceFined;
    }

    public void setPriceFined(int priceFined) {
        this.priceFined = priceFined;
    }

    public List<BorrowDetailsDTO> getBorrowDetailsDTOList() {
        return borrowDetailsDTOList;
    }

    public void setBorrowDetailsDTOList(List<BorrowDetailsDTO> borrowDetailsDTOList) {
        this.borrowDetailsDTOList = borrowDetailsDTOList;
    }
}

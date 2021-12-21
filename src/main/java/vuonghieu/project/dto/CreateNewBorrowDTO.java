package vuonghieu.project.dto;
import java.util.*;
public class CreateNewBorrowDTO {
    private String codeBorrow;
    private int mssv;
    private List<String>codeBooksBorrowed;


    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public List<String> getCodeBooksBorrowed() {
        return codeBooksBorrowed;
    }

    public void setCodeBooksBorrowed(List<String> codeBooksBorrowed) {
        this.codeBooksBorrowed = codeBooksBorrowed;
    }

    public String getCodeBorrow() {
        return codeBorrow;
    }

    public void setCodeBorrow(String codeBorrow) {
        this.codeBorrow = codeBorrow;
    }
}

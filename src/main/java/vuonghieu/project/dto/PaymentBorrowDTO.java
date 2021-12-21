package vuonghieu.project.dto;
import java.util.*;
public class PaymentBorrowDTO {
    private String codeBorrow;

    private List<PaymentCodeBookDTO> codeBookss;

    public String getCodeBorrow() {
        return codeBorrow;
    }

    public void setCodeBorrow(String codeBorrow) {
        this.codeBorrow = codeBorrow;
    }

    public List<PaymentCodeBookDTO> getCodeBookss() {
        return codeBookss;
    }

    public void setCodeBookss(List<PaymentCodeBookDTO> codeBookss) {
        this.codeBookss = codeBookss;
    }
}


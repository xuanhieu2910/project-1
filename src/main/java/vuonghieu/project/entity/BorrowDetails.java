package vuonghieu.project.entity;

import java.util.Date;

public class BorrowDetails {

    private String codeBorrow;
    private String codeBookChild;
    private Date createdOn;
    private Date modifiedOn;
    private Date expiry;
    private Date datePayment;
    private Float price;
    private int borrowed;


    public String getCodeBorrow() {
        return codeBorrow;
    }

    public void setCodeBorrow(String codeBorrow) {
        this.codeBorrow = codeBorrow;
    }

    public String getCodeBookChild() {
        return codeBookChild;
    }

    public void setCodeBookChild(String codeBookChild) {
        this.codeBookChild = codeBookChild;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }

}

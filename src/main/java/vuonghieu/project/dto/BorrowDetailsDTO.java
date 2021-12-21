package vuonghieu.project.dto;

import java.util.Date;

public class BorrowDetailsDTO{

    private String codeBook;
    private String nameBook;
    private Date createdOn;
    private Date expiry;
    private Date datePayment;
    private int price;
    private int borrowed;
    public BorrowDetailsDTO(){}

    public BorrowDetailsDTO(String codeBook, String nameBook, Date createdOn, Date expiry, Date datePayment, int fine, int borrowed) {
        this.codeBook = codeBook;
        this.nameBook = nameBook;
        this.createdOn = createdOn;
        this.expiry = expiry;
        this.datePayment = datePayment;
        this.price = fine;
        this.borrowed = borrowed;
    }

    public String getCodeBook() {
        return codeBook;
    }

    public void setCodeBook(String codeBook) {
        this.codeBook = codeBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    public int getFine() {
        return price;
    }

    public void setFine(int fine) {
        this.price = fine;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }
}

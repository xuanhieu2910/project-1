package vuonghieu.project.dto;

import java.util.Date;

public class AdminDTO {
    private String nameCategory;
    private String nameBook;
    private String codeBook;
    private String contentBook;
    private String nameMajor;
    private String author;
    private String company;
    private Date createdOn;
    private Date modifiedOn;
    private int quantity;
    private int inventory;
    private int quantityBorrowed;
    private int quantityCanBorrow;

    public AdminDTO(String nameCategory, String nameBook, String codeBook, String contentBook,
                    String nameMajor, String author, String company, Date createdOn, Date modifiedOn,
                    int quantity, int inventory, int quantityBorrowed, int quantityCanBorrow) {
        this.nameCategory = nameCategory;
        this.nameBook = nameBook;
        this.codeBook = codeBook;
        this.contentBook = contentBook;
        this.nameMajor = nameMajor;
        this.author = author;
        this.company = company;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.quantity = quantity;
        this.inventory = inventory;
        this.quantityBorrowed = quantityBorrowed;
        this.quantityCanBorrow = quantityCanBorrow;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getCodeBook() {
        return codeBook;
    }

    public void setCodeBook(String codeBook) {
        this.codeBook = codeBook;
    }

    public String getContentBook() {
        return contentBook;
    }

    public void setContentBook(String contentBook) {
        this.contentBook = contentBook;
    }

    public String getNameMajor() {
        return nameMajor;
    }

    public void setNameMajor(String nameMajor) {
        this.nameMajor = nameMajor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getQuantityBorrowed() {
        return quantityBorrowed;
    }

    public void setQuantityBorrowed(int quantityBorrowed) {
        this.quantityBorrowed = quantityBorrowed;
    }

    public int getQuantityCanBorrow() {
        return quantityCanBorrow;
    }

    public void setQuantityCanBorrow(int quantityCanBorrow) {
        this.quantityCanBorrow = quantityCanBorrow;
    }
}

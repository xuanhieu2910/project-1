package vuonghieu.project.dto;

import java.util.Date;

public class BookDTO {

    private String codeBook;
    private String nameBook;
    private String codeCategory;
    private String codeMajor;
    private String author;
    private String company;
    private Float price;
    private Integer quantity;
    private String description;
    private Date createdOn;

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

    public String getCodeCategory() {
        return codeCategory;
    }

    public void setCodeCategory(String codeCategory) {
        this.codeCategory = codeCategory;
    }

    public String getCodeMajor() {
        return codeMajor;
    }

    public void setCodeMajor(String codeMajor) {
        this.codeMajor = codeMajor;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "codeBook='" + codeBook + '\'' +
                ", nameBook='" + nameBook + '\'' +
                ", codeCategory='" + codeCategory + '\'' +
                ", codeMajor='" + codeMajor + '\'' +
                ", author='" + author + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}

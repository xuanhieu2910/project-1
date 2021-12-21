package vuonghieu.project.dto;

public class BookBorrowDTO {
    private String codeBookChild;
    private String nameBook;
    private String nameMajor;
    private String nameCategory;
    private String nameAuthor;
    private int enable;
    public String getCodeBookChild() {
        return codeBookChild;
    }

    public void setCodeBookChild(String codeBookChild) {
        this.codeBookChild = codeBookChild;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getNameMajor() {
        return nameMajor;
    }

    public void setNameMajor(String nameMajor) {
        this.nameMajor = nameMajor;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}

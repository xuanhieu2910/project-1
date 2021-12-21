package vuonghieu.project.dto;

public class ChartDTO {
    private int bookImport;
    private int bookReturn;
    private int bookBorrow;
    private int studentBorrow;


    public int getBookImport() {
        return bookImport;
    }

    public void setBookImport(int bookImport) {
        this.bookImport = bookImport;
    }

    public int getBookReturn() {
        return bookReturn;
    }

    public void setBookReturn(int bookReturn) {
        this.bookReturn = bookReturn;
    }

    public int getBookBorrow() {
        return bookBorrow;
    }

    public void setBookBorrow(int bookBorrow) {
        this.bookBorrow = bookBorrow;
    }

    public int getStudentBorrow() {
        return studentBorrow;
    }

    public void setStudentBorrow(int studentBorrow) {
        this.studentBorrow = studentBorrow;
    }
}

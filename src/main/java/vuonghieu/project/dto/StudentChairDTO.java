package vuonghieu.project.dto;

public class StudentChairDTO {
    private int mssv;
    private String nameStudent;
    private int sumBorrowed;// đã mượn
    private int sumPaymentBorrowed;//đã trả
    private int sumDontPaymentBorrowed;//chưa trả
    private float sumPrice;//tổng tiền

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

    public int getSumBorrowed() {
        return sumBorrowed;
    }

    public void setSumBorrowed(int sumBorrowed) {
        this.sumBorrowed = sumBorrowed;
    }

    public int getSumPaymentBorrowed() {
        return sumPaymentBorrowed;
    }

    public void setSumPaymentBorrowed(int sumPaymentBorrowed) {
        this.sumPaymentBorrowed = sumPaymentBorrowed;
    }

    public int getSumDontPaymentBorrowed() {
        return sumDontPaymentBorrowed;
    }

    public void setSumDontPaymentBorrowed(int sumDontPaymentBorrowed) {
        this.sumDontPaymentBorrowed = sumDontPaymentBorrowed;
    }

    public float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }
}

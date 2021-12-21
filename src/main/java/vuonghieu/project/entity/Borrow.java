package vuonghieu.project.entity;

import java.util.Date;

public class Borrow {

    private String codeBorrow;

    private Integer mssv;

    private int totalQuantity;

    private Date createdOn;

    private String status;

    public Borrow(){}

    public void setIdBorrow(String codeBorrow) {
        this.codeBorrow = codeBorrow;
    }

    public String getIdBorrow(){return codeBorrow;}

    public Integer getMssv() {
        return mssv;
    }

    public void setMssv(Integer mssv) {
        this.mssv = mssv;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

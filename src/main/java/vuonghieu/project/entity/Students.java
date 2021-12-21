package vuonghieu.project.entity;

public class Students {


    private Integer mssv;

    private String nameStudent;

    private String major;


    private String email;

    private Integer phone;

    public Students() {
    }

    public Students( Integer mssv,String nameStudent, String major, String email,Integer phone) {
        this.mssv = mssv;
        this.nameStudent = nameStudent;
        this.major = major;
        this.email=email;
        this.phone=phone;
    }



    public Integer getMssv() {
        return mssv;
    }

    public void setMssv(Integer mssv) {
        this.mssv = mssv;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}

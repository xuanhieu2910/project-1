package vuonghieu.project.entity;


public class Major {



    private String nameMajor;

    private String codeMajor;

    public Major() {
    }

    public Major(String nameMajor,String codeMajor) {
        this.nameMajor = nameMajor;
        this.codeMajor = codeMajor;
    }

    public String getNameMajor() {
        return nameMajor;
    }

    public void setNameMajor(String nameMajor) {
        this.nameMajor = nameMajor;
    }

    public String getCodeMajor() {
        return codeMajor;
    }

    public void setCodeMajor(String codeMajor) {
        this.codeMajor = codeMajor;
    }

}

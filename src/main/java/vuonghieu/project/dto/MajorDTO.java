package vuonghieu.project.dto;

public class MajorDTO{

    private String codeMajor;
    private String nameMajor;



    public MajorDTO(String codeMajor, String nameMajor) {
        this.codeMajor = codeMajor;
        this.nameMajor = nameMajor;
    }

    public String getCodeMajor() {
        return codeMajor;
    }

    public void setCodeMajor(String codeMajor) {
        this.codeMajor = codeMajor;
    }

    public String getNameMajor() {
        return nameMajor;
    }

    public void setNameMajor(String nameMajor) {
        this.nameMajor = nameMajor;
    }
}

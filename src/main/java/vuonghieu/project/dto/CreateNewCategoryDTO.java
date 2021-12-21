package vuonghieu.project.dto;

public class CreateNewCategoryDTO{
    private String codeCategory;
    private String nameCategory;

    public CreateNewCategoryDTO(String codeCategory, String nameCategory) {
        this.codeCategory = codeCategory;
        this.nameCategory = nameCategory;
    }

    public String getCodeCategory() {
        return codeCategory;
    }

    public void setCodeCategory(String codeCategory) {
        this.codeCategory = codeCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}

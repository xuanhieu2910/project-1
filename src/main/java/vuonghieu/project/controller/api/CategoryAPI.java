package vuonghieu.project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vuonghieu.project.dto.CategoryDTO;
import vuonghieu.project.dto.CreateNewCategoryDTO;
import vuonghieu.project.entity.Category;
import java.util.*;
import vuonghieu.project.service.impl.CategoryServiceImpl;


@RestController
@RequestMapping("/api/category")
public class CategoryAPI {
    @Autowired
    private CategoryServiceImpl categoryService;



    @PostMapping("/save")
    public void saveCategory(@RequestBody CreateNewCategoryDTO createNewCategoryDTO){
        categoryService.save(createNewCategoryDTO.getNameCategory(), createNewCategoryDTO.getCodeCategory());
        return;
    }

    @GetMapping("/findAll")
    public List<CategoryDTO> findAllCategory(){
        List<Category> category = categoryService.findAll();
        List<CategoryDTO>categoryDTOS = new ArrayList<>();
        for (Category category1 : category) {
            CategoryDTO categoryDTO = new CategoryDTO(category1.getCodeCategory(),
                    category1.getNameCategory(),category1.getCreatedOn(),category1.getModifiedOn());
            categoryDTOS.add(categoryDTO);

        }
        return categoryDTOS;
    }

    @DeleteMapping("/delete/{codeCategory}")
    public void deleteCategoryByCodeCategory(@PathVariable("codeCategory") String codeCategory){
        categoryService.deleteByCodeCategory(codeCategory);
    }


    @GetMapping("/codeCategories")
    public List<String> findAllCodeCategories(){
        return categoryService.findAllCodeCategories();
    }

}

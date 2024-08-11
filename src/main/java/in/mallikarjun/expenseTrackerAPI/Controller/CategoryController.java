package in.mallikarjun.expenseTrackerAPI.Controller;

import in.mallikarjun.expenseTrackerAPI.dto.CategoryDTO;
import in.mallikarjun.expenseTrackerAPI.io.CategoryRequest;
import in.mallikarjun.expenseTrackerAPI.io.CategoryResponse;
import in.mallikarjun.expenseTrackerAPI.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/**
 * This controller is for managing the categories
 * @author Bushan SC
 * */
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * API for creating the category
     * @param categoryRequest
     * @return CategoryResponse
     * */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryDTO categoryDTO = mapToDTO(categoryRequest);
        categoryDTO = categoryService.saveCategory(categoryDTO);
        return mapToResponse(categoryDTO);
    }

    /**
     * API for reading the categories
     * @return list
     * */
    @GetMapping
    public List<CategoryResponse> readCategories() {
        List<CategoryDTO> listOfCategories = categoryService.getAllCategories();
        return listOfCategories.stream().map(categoryDTO -> mapToResponse(categoryDTO)).collect(Collectors.toList());
    }

    /**
     * API for deleting the category
     * @param categoryId
     *
     * */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    /**
     * Mapper method for converting DTO object to Response object
     * @param categoryDTO
     * @return CategoryResponse
     * */
    private CategoryResponse mapToResponse(CategoryDTO categoryDTO) {
        return CategoryResponse.builder()
                .categoryId(categoryDTO.getCategoryId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .categoryIcon(categoryDTO.getCategoryIcon())
                .createdAt(categoryDTO.getCreatedAt())
                .updatedAt(categoryDTO.getUpdateAt())
                .build();
    }

    /**
     * Mapper method for converting Request object to DTO object
     * @param categoryRequest
     * @return CategoryDTO
     * */
    private CategoryDTO mapToDTO(CategoryRequest categoryRequest) {
        return CategoryDTO.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .categoryIcon(categoryRequest.getIcon())
                .build();
    }
}
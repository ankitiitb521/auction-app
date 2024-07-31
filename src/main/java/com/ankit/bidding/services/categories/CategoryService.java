package com.ankit.bidding.services.categories;

import com.ankit.bidding.dto.CategoryDto;
import com.ankit.bidding.execption.BusinessValidationException;
import com.ankit.bidding.execption.SystemFailureExeption;
import com.ankit.bidding.models.Category;
import com.ankit.bidding.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> showCategories() throws SystemFailureExeption {
        try {
            return categoryRepository.findAll();
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    public Category addCategory(CategoryDto categoryDto) throws Exception {
        try {
            if(categoryDto.getName().isEmpty()){
                throw new IllegalArgumentException("Category name must not be empty");
            }
            Category category = new Category();
            category.setName(categoryDto.getName());
            return saveCategory(category);
        } catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            throw  new BusinessValidationException(e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new SystemFailureExeption(e.getMessage());
        }
    }

    @Transactional
    private Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
}

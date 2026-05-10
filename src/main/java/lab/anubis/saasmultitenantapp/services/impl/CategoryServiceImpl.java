package lab.anubis.saasmultitenantapp.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lab.anubis.saasmultitenantapp.entities.Category;
import lab.anubis.saasmultitenantapp.mapper.CategoryMapper;
import lab.anubis.saasmultitenantapp.repositories.CategoryRepository;
import lab.anubis.saasmultitenantapp.requests.CategoryRequest;
import lab.anubis.saasmultitenantapp.responses.CategoryResponse;
import lab.anubis.saasmultitenantapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void create(CategoryRequest request) {
        checkIfCategoryExistsByName(request.getName());
        final Category category = categoryMapper.toEntity(request);
        this.categoryRepository.save(category);
    }

    @Override
    public void update(String id, CategoryRequest request) {
        final Optional<Category> existingCategory = this.categoryRepository.findById(id);
        if (existingCategory.isEmpty()){
            log.debug("Category not found");
            throw new EntityNotFoundException("Category not found");
        }
        final Category category = existingCategory.get();
        if (!category.getName().equalsIgnoreCase(request.getName())){
            checkIfCategoryExistsByName(request.getName());
        }
        final Category updateCategory = categoryMapper.toEntity(request);
        updateCategory.setId(id);
        this.categoryRepository.save(updateCategory);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return this.categoryRepository.findAll().stream()
                .map(this.categoryMapper::toResponse).toList();
    }

    @Override
    public CategoryResponse findById(String id) {
        return this.categoryRepository.findById(id)
                .map((this.categoryMapper::toResponse))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    @Override
    public void delete(String id) {
        final Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
//        category.setDeleted(true);
        this.categoryRepository.softDelete(category.getId());
//        this.categoryRepository.save(category);
    }


    private void checkIfCategoryExistsByName(final String name) {
        final Optional<Category> category = this.categoryRepository.findByNameIgnoreCase(name);
        if (category.isPresent()){
            log.debug("Category already exists");
            throw new RuntimeException("Category already exists");
        }

    }

}

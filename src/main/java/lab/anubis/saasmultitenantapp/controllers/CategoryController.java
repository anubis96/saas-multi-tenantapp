package lab.anubis.saasmultitenantapp.controllers;

import jakarta.validation.Valid;
import lab.anubis.saasmultitenantapp.common.PageResponse;
import lab.anubis.saasmultitenantapp.requests.CategoryRequest;
import lab.anubis.saasmultitenantapp.responses.CategoryResponse;
import lab.anubis.saasmultitenantapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> createCategory(
            @Valid @RequestBody final CategoryRequest request
            ){
        this.service.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<Void> updateCategory(
            @Valid @RequestBody final CategoryRequest request,
            @PathVariable("category-id") final String id
    ){
        this.service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<CategoryResponse>> findAllCategories(
            @RequestParam(name = "page", defaultValue = "0")
            final int page,
            @RequestParam(name = "size", defaultValue = "10")
            final int size
    ) {
        return ResponseEntity.ok(this.service.findAll(page, size));
    }


    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable("category-id") final String id
    ){
        return ResponseEntity.ok(this.service.findById(id));
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable("category-id") String id
    ){
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

}

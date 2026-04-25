package com.deepak.productcatalogservice.controller;

import com.deepak.productcatalogservice.dto.ApiResponse;
import com.deepak.productcatalogservice.dto.ApiResponseData;
import com.deepak.productcatalogservice.dto.ListApiResponseData;
import com.deepak.productcatalogservice.dto.request.CreateCategoryRequest;
import com.deepak.productcatalogservice.dto.request.UpdateCategoryRequest;
import com.deepak.productcatalogservice.dto.response.GetAllCategoryResponse;
import com.deepak.productcatalogservice.dto.response.GetCategoryByIdResponse;
import com.deepak.productcatalogservice.service.CategoryService;
import com.deepak.productcatalogservice.util.ResponseUtil;
import io.vavr.Tuple2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService _categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody CreateCategoryRequest req){
        _categoryService.createCategory(req);
        return ResponseUtil.success(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseData<GetCategoryByIdResponse>> getCategoryById(@PathVariable Long id){
        GetCategoryByIdResponse res = _categoryService.getCategoryById(id);
        return ResponseUtil.success(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListApiResponseData<GetAllCategoryResponse>> getAllCategory(@RequestParam int pageNo, @RequestParam int pageSize){
        Tuple2<List<GetAllCategoryResponse>, Long> res = _categoryService.getAllCategory(pageNo, pageSize);
        return ResponseUtil.success(res._1,res._2, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequest req){
        _categoryService.updateCategory(id, req);
        return ResponseUtil.success(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        _categoryService.deleteCategory(id);
        return ResponseUtil.success(HttpStatus.OK);
    }
}

package com.deepak.productcatalogservice.service;

import com.deepak.productcatalogservice.dto.request.CreateCategoryRequest;
import com.deepak.productcatalogservice.dto.request.UpdateCategoryRequest;
import com.deepak.productcatalogservice.dto.response.GetAllCategoryResponse;
import com.deepak.productcatalogservice.dto.response.GetCategoryByIdResponse;
import com.deepak.productcatalogservice.exception.AppException;
import com.deepak.productcatalogservice.mapper.CategoryMapper;
import com.deepak.productcatalogservice.model.Category;
import com.deepak.productcatalogservice.repository.CategoryRepository;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository _categoryRepository;
    private final CategoryMapper _categoryMapper;

    public void createCategory(CreateCategoryRequest req){

        Optional<Category> category = _categoryRepository.findByName(req.getName());

        if(category.isPresent()) throw new AppException("Category already exists");

        _categoryRepository.save(_categoryMapper.createProductRequest(req));
    }

    public GetCategoryByIdResponse getCategoryById(Long id){
        Category data = _categoryRepository.findById(id).orElseThrow(() -> new AppException("Category not found"));
        return _categoryMapper.getCategoryByIdResponse(data);
    }

    public Tuple2<List<GetAllCategoryResponse>, Long> getAllCategory(int pageNo, int pageSize){

        pageNo = (pageNo < 0 ? 0 : pageNo);
        pageSize = (pageSize < 1 ? 10 : pageSize);

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page page = _categoryRepository.findAll(pageable);

        List<GetAllCategoryResponse> res = _categoryMapper.getAllCategoryResponse(page.getContent());
        return Tuple.of(res, page.getTotalElements());
    }

    public void updateCategory(Long id, UpdateCategoryRequest req){

        Optional<Category> duplicate = _categoryRepository.findByName(req.getName());
         if(duplicate.isPresent() && duplicate.get().getId().equals(id) == false) throw new AppException("Duplicate name");

        Category data = _categoryRepository.findById(id).orElseThrow(() -> new AppException("Category not found"));

        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setUpdatedDate(LocalDateTime.now());
        _categoryRepository.save(data);
    }

    public void deleteCategory(Long id){
        if(!_categoryRepository.existsById(id)) throw new AppException("Category not found");
        _categoryRepository.deleteById(id);
    }
}

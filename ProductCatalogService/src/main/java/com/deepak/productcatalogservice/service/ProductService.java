package com.deepak.productcatalogservice.service;

import com.deepak.productcatalogservice.dto.request.CreateProductRequest;
import com.deepak.productcatalogservice.dto.request.UpdateProductRequest;
import com.deepak.productcatalogservice.dto.response.GetAllProductResponse;
import com.deepak.productcatalogservice.dto.response.GetProductByIdResponse;
import com.deepak.productcatalogservice.exception.AppException;
import com.deepak.productcatalogservice.mapper.ProductMapper;
import com.deepak.productcatalogservice.model.Category;
import com.deepak.productcatalogservice.model.Product;
import com.deepak.productcatalogservice.repository.CategoryRepository;
import com.deepak.productcatalogservice.repository.ProductRepository;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository _productRepository;
    private final CategoryRepository _categoryRepository;
    private final ProductMapper _productMapper;

    public void createProduct(CreateProductRequest req){

        Category category = _categoryRepository.findByName(req.getCategory()).orElseThrow(() -> new AppException("Invalid category"));

        Product data = new Product();

        data.setName(req.getName());
        data.setPrice(req.getPrice());
        data.setBrand(req.getBrand());
        data.setDescription(req.getDescription());
        data.setImageUrl(req.getImageUrl());
        data.setActive(req.isActive());
        data.setAttributes(req.getAttributes());
        data.setCategory(category);
        data.setCreatedDate(LocalDateTime.now());

        _productRepository.save(data);
    }

    public GetProductByIdResponse getProductById(Long id){
        Product data = _productRepository.findById(id).orElseThrow(() -> new AppException("Product not found"));
        return _productMapper.getProductById(data);
    }

    public Tuple2<List<GetAllProductResponse>, Long> getAllProduct(int pageNo, int pageSize){

        pageNo = (pageNo < 0 ? 0 : pageNo);
        pageSize = (pageSize < 1 ? 10 : pageSize);

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page page = _productRepository.findAll(pageable);

        List<GetAllProductResponse> res = _productMapper.getAllProduct(page.getContent());
        return Tuple.of(res, page.getTotalElements());
    }

    public void updateProduct(Long id, UpdateProductRequest req){
        Product data = _productRepository.findById(id).orElseThrow(() -> new AppException("Product not found"));

        Category category = _categoryRepository.findByName(req.getCategory()).orElseThrow(() -> new AppException("Invalid category"));

        data.setName(req.getName());
        data.setPrice(req.getPrice());
        data.setBrand(req.getBrand());
        data.setDescription(req.getDescription());
        data.setImageUrl(req.getImageUrl());
        data.setActive(req.isActive());
        data.setAttributes(req.getAttributes());
        data.setCategory(category);
        data.setUpdatedDate(LocalDateTime.now());

        _productRepository.save(data);
    }

    public void deleteProduct(Long id){
        if(!_productRepository.existsById(id)) throw new AppException("Product not found");
        _productRepository.deleteById(id);
    }
}

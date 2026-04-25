package com.deepak.productcatalogservice.controller;

import com.deepak.productcatalogservice.dto.ApiResponse;
import com.deepak.productcatalogservice.dto.ApiResponseData;
import com.deepak.productcatalogservice.dto.ListApiResponseData;
import com.deepak.productcatalogservice.dto.request.CreateProductRequest;
import com.deepak.productcatalogservice.dto.request.UpdateProductRequest;
import com.deepak.productcatalogservice.dto.response.GetAllProductResponse;
import com.deepak.productcatalogservice.dto.response.GetProductByCategoryResponse;
import com.deepak.productcatalogservice.dto.response.GetProductByIdResponse;
import com.deepak.productcatalogservice.dto.response.GetProductByKeywordResponse;
import com.deepak.productcatalogservice.service.ProductService;
import com.deepak.productcatalogservice.util.ResponseUtil;
import io.vavr.Tuple2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService _productService;

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody CreateProductRequest req){
        _productService.createProduct(req);
        return ResponseUtil.success(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseData<GetProductByIdResponse>> getProductById(@PathVariable Long id){
        GetProductByIdResponse res = _productService.getProductById(id);
        return ResponseUtil.success(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListApiResponseData<GetAllProductResponse>> getAllProduct(@RequestParam int pageNo, @RequestParam int pageSize){
        Tuple2<List<GetAllProductResponse>, Long> res = _productService.getAllProduct(pageNo, pageSize);
        return ResponseUtil.success(res._1,res._2, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest req){
        _productService.updateProduct(id, req);
        return ResponseUtil.success(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        _productService.deleteProduct(id);
        return ResponseUtil.success(HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ListApiResponseData<GetProductByCategoryResponse>> getProductByCategory(@PathVariable Long id, @RequestParam int pageNo, @RequestParam int pageSize){
        Tuple2<List<GetProductByCategoryResponse>, Long> res = _productService.getProductByCategory(id, pageNo, pageSize);
        return ResponseUtil.success(res._1, res._2, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ListApiResponseData<GetProductByKeywordResponse>> getProductByKeyword(@RequestParam String keyword){
        List<GetProductByKeywordResponse> res = _productService.getProductByKeyword(keyword);
        return ResponseUtil.success(res, res.stream().count(), HttpStatus.OK);
    }
}

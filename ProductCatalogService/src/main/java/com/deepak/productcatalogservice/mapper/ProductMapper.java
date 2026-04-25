package com.deepak.productcatalogservice.mapper;

import com.deepak.productcatalogservice.dto.response.GetProductByCategoryResponse;
import com.deepak.productcatalogservice.dto.response.GetProductByIdResponse;
import com.deepak.productcatalogservice.dto.response.GetProductByKeywordResponse;
import com.deepak.productcatalogservice.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public GetProductByIdResponse getProductById(Product req){
        if(req == null) return null;

        GetProductByIdResponse res = new GetProductByIdResponse();

        res.setName(req.getName());
        res.setPrice(req.getPrice());
        res.setBrand(req.getBrand());
        res.setDescription(req.getDescription());
        res.setImageUrl(req.getImageUrl());
        res.setActive(req.isActive());
        res.setAttributes(req.getAttributes());
        res.setCategory(req.getCategory().getName());

        return res;
    }

    public List<GetProductByIdResponse> getAllProduct(List<Product> reqList){
        if(reqList == null) return null;

        List<GetProductByIdResponse> result = new ArrayList<>();

        for(Product req : reqList) {
            GetProductByIdResponse res = new GetProductByIdResponse();
            res.setName(req.getName());
            res.setPrice(req.getPrice());
            res.setBrand(req.getBrand());
            res.setDescription(req.getDescription());
            res.setImageUrl(req.getImageUrl());
            res.setActive(req.isActive());
            res.setAttributes(req.getAttributes());
            res.setCategory(req.getCategory().getName());
            result.add(res);
        }
        return result;
    }

    public List<GetProductByCategoryResponse> getProductByCategory(List<Product> reqList){
        if(reqList == null) return null;

        List<GetProductByCategoryResponse> result = new ArrayList<>();

        for(Product req : reqList) {
            GetProductByCategoryResponse res = new GetProductByCategoryResponse();
            res.setName(req.getName());
            res.setPrice(req.getPrice());
            res.setBrand(req.getBrand());
            res.setDescription(req.getDescription());
            res.setImageUrl(req.getImageUrl());
            res.setActive(req.isActive());
            res.setAttributes(req.getAttributes());
            res.setCategory(req.getCategory().getName());
            result.add(res);
        }
        return result;
    }

    public List<GetProductByKeywordResponse> getProductByKeyword(List<Product> reqList){
        if(reqList == null) return null;

        List<GetProductByKeywordResponse> result = new ArrayList<>();

        for(Product req : reqList) {
            GetProductByKeywordResponse res = new GetProductByKeywordResponse();
            res.setName(req.getName());
            res.setPrice(req.getPrice());
            res.setBrand(req.getBrand());
            res.setDescription(req.getDescription());
            res.setImageUrl(req.getImageUrl());
            res.setActive(req.isActive());
            res.setAttributes(req.getAttributes());
            res.setCategory(req.getCategory().getName());
            result.add(res);
        }
        return result;
    }
}

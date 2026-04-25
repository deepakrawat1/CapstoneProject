package com.deepak.productcatalogservice.mapper;

import com.deepak.productcatalogservice.dto.request.CreateCategoryRequest;
import com.deepak.productcatalogservice.dto.response.GetAllCategoryResponse;
import com.deepak.productcatalogservice.dto.response.GetCategoryByIdResponse;
import com.deepak.productcatalogservice.model.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public Category createProductRequest(CreateCategoryRequest req){
        Category data = new Category();

        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setCreatedDate(LocalDateTime.now());

        return data;
    }

    public GetCategoryByIdResponse getCategoryByIdResponse(Category req){
        GetCategoryByIdResponse res = new GetCategoryByIdResponse();

        res.setName(req.getName());
        res.setDescription(req.getDescription());

        return res;
    }

    public List<GetAllCategoryResponse> getAllCategoryResponse(List<Category> req){

        List<GetAllCategoryResponse> res = new ArrayList<>();

        for(Category item : req) {
            GetAllCategoryResponse data = new GetAllCategoryResponse();
            data.setName(item.getName());
            data.setDescription(item.getDescription());
            res.add(data);
        }

        return res;
    }
}

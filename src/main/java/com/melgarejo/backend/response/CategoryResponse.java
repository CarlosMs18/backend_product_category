package com.melgarejo.backend.response;

import com.melgarejo.backend.models.Category;
import lombok.Data;

import java.util.List;


@Data
public class CategoryResponse {
    private List<Category> category;
}

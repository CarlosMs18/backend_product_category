package com.melgarejo.backend.response;

import com.melgarejo.backend.models.Product;
import lombok.Data;

import java.util.List;
@Data
public class ProductResponse {
    private List<Product> product;
}

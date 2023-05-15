package com.melgarejo.backend.dao;

import com.melgarejo.backend.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductDao extends CrudRepository<Product, Long> {
}

package com.melgarejo.backend.dao;

import com.melgarejo.backend.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category, Long> {
}

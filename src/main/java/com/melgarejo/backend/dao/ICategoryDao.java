package com.melgarejo.backend.dao;

import com.melgarejo.backend.models.Category;
import com.melgarejo.backend.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICategoryDao extends CrudRepository<Category, Long> {

    @Query("select p from Category p where p.nombre like %?1%")
    List<Category> findByNameLike(String name);

    List<Category> findByNombreContainingIgnoreCase(String name);
}

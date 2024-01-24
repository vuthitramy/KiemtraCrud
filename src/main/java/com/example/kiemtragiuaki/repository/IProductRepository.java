package com.example.kiemtragiuaki.repository;

import com.example.kiemtragiuaki.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(int id);
}
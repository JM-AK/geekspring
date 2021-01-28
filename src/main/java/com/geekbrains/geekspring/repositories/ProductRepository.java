package com.geekbrains.geekspring.repositories;

import com.geekbrains.geekspring.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByPriceBetween(Pageable pageable, double min, double max);
    Product findOneByTitle(String title);
}

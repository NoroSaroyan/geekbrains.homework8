package ru.gb.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.gb.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

    @Query(value = "SELECT *  FROM onlineShop.products WHERE (price) > (SELECT MIN (price) FROM onlineShop.products)", nativeQuery = true)
    Iterable<Product> findMoreMinPrice();

    @Query(value = "SELECT *  FROM onlineShop.products WHERE (price) < (SELECT MAX (price) FROM onlineShop.products)", nativeQuery = true)
    Iterable<Product> findLessMaxPrice();

    @Query(value = "SELECT *  FROM onlineShop.products AS p WHERE p.price > :min AND p.price < :max", nativeQuery = true)
    Iterable<Product> findBetweenMinMax(@Param("min") int min, @Param("max") int max);

    // var2
    Iterable<Product> findByPriceBetween(int min, int max);

}
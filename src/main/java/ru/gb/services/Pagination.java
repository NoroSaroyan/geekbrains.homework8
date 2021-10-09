package ru.gb.services;


import org.springframework.stereotype.Service;
import ru.gb.Product;
import ru.gb.repositories.ProductRepository;
;

import java.util.ArrayList;
import java.util.List;

@Service
public class Pagination {

    public List<Integer> countPages(ProductRepository repository) {
        List<Product> products = new ArrayList<>();
        List<Integer> pages = new ArrayList<>();
        repository.findAll().forEach(products::add);

        if (products.size() % 10 > 0) {
            pages.add(0, 0);
            for (int i = 0; i < products.size() / 10; i++) {
                pages.add(i + 1);
            }
        } else {
            for (int i = 0; i < products.size() / 10; i++) {
                pages.add(i);
            }
        }
        return pages;
    }
}
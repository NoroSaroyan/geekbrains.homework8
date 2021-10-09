package ru.gb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gb.Product;
import ru.gb.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app/products")
public class RestController {

    private final ProductRepository repository;

    public RestController(ProductRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(products::add);
        return products;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product findById(@PathVariable int id) {
        return repository.findById(id).orElseThrow();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void deleteById(@PathVariable int id) {
        repository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Product> save(@RequestBody Product product) {
        repository.save(product);
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(products::add);
        return products;
    }

    @RequestMapping(value = "/find/min", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> findMoreThenMin() {
        List<Product> products = new ArrayList<>();
        repository.findMoreMinPrice().forEach(products::add);
        return products;
    }

    @RequestMapping(value = "/find/max", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> findLessThenMax() {
        List<Product> products = new ArrayList<>();
        repository.findLessMaxPrice().forEach(products::add);
        return products;
    }

    @RequestMapping(value = "/find/between/{min}&{max}", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> findBetween(@PathVariable int min, @PathVariable int max) {
        int temp;
        if (min > max) {
            temp = min;
            min = max;
            max = temp;
        }
        List<Product> products = new ArrayList<>();
        repository.findBetweenMinMax(min, max).forEach(products::add);
        return products;
    }

    //Второй вариант

    @RequestMapping(value = "/find/between2/{min}&{max}", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> findBetween2(@PathVariable int min, @PathVariable int max) {
        int temp;
        if (min > max) {
            temp = min;
            min = max;
            max = temp;
        }
        List<Product> products = new ArrayList<>();
        repository.findByPriceBetween(min, max).forEach(products::add);
        return products;
    }
}
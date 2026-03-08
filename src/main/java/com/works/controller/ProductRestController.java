package com.works.controller;

import com.works.entity.Product;
import com.works.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService productService;

    @PostMapping("save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @PostMapping("saveAll")
    public List<Product> saveAll(@RequestBody List<Product> products){
        return productService.saveAll(products);
    }

    @GetMapping("list")
    public List<Product> list(){
        return productService.list();
    }

    @GetMapping("get/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p){
        return productService.update(id, p);
    }

}

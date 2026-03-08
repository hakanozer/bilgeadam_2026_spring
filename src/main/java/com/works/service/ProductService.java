package com.works.service;

import com.works.entity.Product;
import com.works.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<Product> saveAll(List<Product> products){
        return productRepository.saveAll(products);
    }

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public void deleteById(Long id){
        boolean exists = productRepository.existsById(id);
        if(!exists){
            throw new RuntimeException("Product not found :" + id);
        }
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product p){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found :" + id));

        product.setTitle(p.getTitle());
        product.setDescription(p.getDescription());
        product.setPrice(p.getPrice());

        return productRepository.save(product);
    }

    public Page<Product> listPage(Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    public Page<Product> search(String q, Integer page, String sortBy, String direction){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, 10, sort);

        return productRepository
                .findByTitleContainsOrDescriptionContainsAllIgnoreCase(q, q, pageable);
    }



}

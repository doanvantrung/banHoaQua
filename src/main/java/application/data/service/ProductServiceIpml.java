package application.data.service;

import application.data.model.Product;
import application.data.reponsitory.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceIpml implements IProductService {
    @Autowired
    private ProductReponsitory productRepository;
    @Override
    public Iterable<Product> fillAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> search(String q) {
        return productRepository.findByNameContaining(q);
    }

    @Override
    public Product findOne(int id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }



}
package application.data.service;

import application.data.model.Product;

import java.util.List;

public interface IProductService {
    Iterable<Product> fillAll();
    List<Product> search (String q);
    Product findOne(int id);
    void save(Product product);
    void delete(int id);
}

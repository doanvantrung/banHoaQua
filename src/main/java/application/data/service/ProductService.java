package application.data.service;

import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.reponsitory.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductReponsitory productRepository;


    public void addNewProduct(Product product){
        productRepository.save(product);
    }
    @Transactional
    public  void addNewListProducts(List<Product> productlist){
        productRepository.save(productlist);
    }
    public long getTotalProducts(){
        return productRepository.getTotalProducts();
    }
    public PaginableItemList<Product> getListProducts(int pageSize , int pageNumber){
        PaginableItemList<Product> productPaginableItemList = new PaginableItemList<>();
        productPaginableItemList.setPageSize(pageSize);
        productPaginableItemList.setPageNumber(pageNumber);

        Page<Product> productPage = productRepository.findAll(new PageRequest(pageNumber , pageSize));
        productPaginableItemList.setTotalProducts(productPage.getTotalElements());
        productPaginableItemList.setListData(productPage.getContent());
        return productPaginableItemList;
    }

}


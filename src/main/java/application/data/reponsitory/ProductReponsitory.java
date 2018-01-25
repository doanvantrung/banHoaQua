package application.data.reponsitory;

import application.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductReponsitory extends JpaRepository<Product , Integer> {

    //chon cot id trong bang tbl_product gan cho bien p
    @Query("select count (p.id) from tbl_product p" )
    long getTotalProducts(); // lay tong so Product trong id. Nghia la minh dem tong so id se ra san pham
    List<Product> findByNameContaining(String q);
}

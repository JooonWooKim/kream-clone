package kream.clone.product.repository;

import kream.clone.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    boolean existsByModelNumber(String modelNumber);

}

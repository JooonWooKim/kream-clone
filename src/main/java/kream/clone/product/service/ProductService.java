package kream.clone.product.service;

import kream.clone.product.dto.request.ProductRegisterRequest;
import kream.clone.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public void registerProduct(ProductRegisterRequest request) {
    }
}

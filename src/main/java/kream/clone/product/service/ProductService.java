package kream.clone.product.service;

import kream.clone.brand.entity.Brand;
import kream.clone.brand.repository.BrandRepository;
import kream.clone.common.exception.ErrorCode;
import kream.clone.common.exception.KreamException;
import kream.clone.product.dto.request.ProductCreateRequest;
import kream.clone.product.dto.request.ProductUpdateRequest;
import kream.clone.product.entity.Product;
import kream.clone.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public void createProduct(ProductCreateRequest request) {
        Brand savedBrand = validatedBrandExists(request);
        isExistsProduct(request.getName(), request.getModelNumber());
        productRepository.save(Product.createProduct(request, savedBrand));
    }

    private void isExistsProduct(String name, String modelNumber) {
        if(productRepository.existsByNameAndModelNumber(name, modelNumber)){
            throw new KreamException(ErrorCode.DUPLICATED_PRODUCT);
        }
    }

    private Brand validatedBrandExists(ProductCreateRequest request) {
        return brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new KreamException(ErrorCode.NOT_FOUND_PRODUCT));
    }

    @Transactional
    public void updateProductInfo(Long productId, ProductUpdateRequest request) {
        Product saveProduct = productRepository.findById(productId).orElseThrow(()-> new
                KreamException(ErrorCode.NOT_FOUND_PRODUCT));

        checkDuplicatedUpdateProduct(request, saveProduct);

        saveProduct.update(request);
    }

    private void checkDuplicatedUpdateProduct(ProductUpdateRequest request, Product saveProduct) {
    }
}

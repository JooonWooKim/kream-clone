package kream.clone.product.controller;

import jakarta.validation.Valid;
import kream.clone.common.response.SuccessMessage;
import kream.clone.product.dto.request.ProductCreateRequest;
import kream.clone.product.dto.request.ProductUpdateRequest;
import kream.clone.product.dto.response.ProductInfo;
import kream.clone.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    /**
     * 제품 생성
     * @param request
     * @return
     */
    @PostMapping("")
    public ResponseEntity<SuccessMessage<Void>> createProduct(@Valid @RequestBody ProductCreateRequest request){
        productService.createProduct(request);
        return new ResponseEntity<>(new SuccessMessage<>("제품 생성 성공", null), HttpStatus.CREATED);
    }

    /**
     * 제품 정보 수정
     * @param productId
     * @param request
     * @return
     */
    @PutMapping("/{productId}")
    public ResponseEntity<SuccessMessage<Void>> updateProductInfo(@PathVariable Long productId, @RequestBody ProductUpdateRequest request){
        productService.updateProductInfo(productId, request);
        return new ResponseEntity<>(new SuccessMessage<>("제품 정보 수정 성공", null), HttpStatus.OK);
    }

    /**
     * 제품 삭제
     * @param productId
     * @return
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<SuccessMessage<Void>> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new SuccessMessage<>("제품 삭제 성공", null), HttpStatus.OK);
    }

    /**
     * 단일 제품 정보 조회
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    public ResponseEntity<SuccessMessage<ProductInfo>> getProductInfo(@PathVariable Long productId){
        ProductInfo productInfo = productService.getProductInfo(productId);
        return new ResponseEntity<>(new SuccessMessage<>("단일제품정보조회성공", productInfo), HttpStatus.OK);
    }

    /**
     * 제품 정보 리스트 정보 조회
     * @return
     */
    @GetMapping("")
    public ResponseEntity<SuccessMessage<List<ProductInfo>>> getProductsInfo(){
        List<ProductInfo> productsInfo = productService.getProductsInfo();
        return new ResponseEntity<>(new SuccessMessage<>("제품정보리스트조회성공", productsInfo), HttpStatus.OK);
    }
}

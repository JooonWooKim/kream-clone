package kream.clone.product.controller;

import jakarta.validation.Valid;
import kream.clone.common.response.SuccessMessage;
import kream.clone.product.dto.request.ProductCreateRequest;
import kream.clone.product.dto.request.ProductUpdateRequest;
import kream.clone.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

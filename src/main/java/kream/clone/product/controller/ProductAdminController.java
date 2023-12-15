package kream.clone.product.controller;

import jakarta.validation.Valid;
import kream.clone.common.response.SuccessMessage;
import kream.clone.product.dto.request.ProductCreateRequest;
import kream.clone.product.dto.request.ProductRegisterRequest;
import kream.clone.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductAdminController {

    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<SuccessMessage<Void>> createProduct(@Valid @RequestBody ProductCreateRequest request){
        return new ResponseEntity<>(new SuccessMessage<>("제품 생성 성공", null), HttpStatus.CREATED);
    }
}

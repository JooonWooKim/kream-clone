package kream.clone.brand.controller;

import jakarta.validation.Valid;
import kream.clone.brand.dto.request.BrandCreateRequest;
import kream.clone.brand.service.BrandService;
import kream.clone.common.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    /**
     * 브랜드 생성
     */
    @PostMapping("")
    public ResponseEntity<SuccessMessage<Void>> createBrand(@Valid @RequestBody BrandCreateRequest request){
        brandService.createBrand(request);
        return new ResponseEntity<>(new SuccessMessage<>("브랜드생성성공", null), HttpStatus.CREATED);
    }
}

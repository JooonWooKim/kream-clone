package kream.clone.brand.controller;

import jakarta.validation.Valid;
import kream.clone.brand.dto.request.BrandCreateRequest;
import kream.clone.brand.dto.request.BrandUpdateRequest;
import kream.clone.brand.dto.response.BrandInfo;
import kream.clone.brand.service.BrandService;
import kream.clone.common.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 브랜드 정보 수정
     * @param brandId
     * @param request
     * @return null
     */
    @PutMapping("/{brandId}")
    public ResponseEntity<SuccessMessage<Void>> updateBrandInfo(@PathVariable Long brandId, @RequestBody BrandUpdateRequest request){
        brandService.updateBrandInfo(brandId, request);
        return new ResponseEntity<>(new SuccessMessage<>("브랜드정보수정성공", null), HttpStatus.OK);
    }

    /**
     * 브랜드 삭제
     * @param brandId
     * @return
     */
    @DeleteMapping("/{brandId}")
    public ResponseEntity<SuccessMessage<Void>> deleteBrand(@PathVariable Long brandId){
        brandService.deleteBrand(brandId);
        return new ResponseEntity<>(new SuccessMessage<>("브랜드삭제성공", null), HttpStatus.OK);
    }

    /**
     * 단일 브랜드 정보 조회
     * @param brandId
     * @return BrandInfo
     */
    @GetMapping("/{brandId}")
    public ResponseEntity<SuccessMessage<BrandInfo>> getBrandInfo(@PathVariable Long brandId){
        BrandInfo brandInfo = brandService.getBrandInfo(brandId);
        return new ResponseEntity<>(new SuccessMessage<>("브랜드단일정보조회성공", brandInfo), HttpStatus.OK);
    }

    /**
     * 브랜드 정보 리스트 조회
     * @param
     * @return BrandInfo
     */
    @GetMapping("")
    public ResponseEntity<SuccessMessage<List<BrandInfo>>> getBrandsInfo(){
        List<BrandInfo> brandInfo = brandService.getBrandsInfo();
        return new ResponseEntity<>(new SuccessMessage<>("브랜드정보리스트조회성공", brandInfo), HttpStatus.OK);
    }
}

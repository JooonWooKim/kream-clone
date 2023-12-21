package kream.clone.brand.service;

import kream.clone.brand.dto.request.BrandCreateRequest;
import kream.clone.brand.dto.request.BrandUpdateRequest;
import kream.clone.brand.dto.response.BrandInfo;
import kream.clone.brand.entity.Brand;
import kream.clone.brand.repository.BrandRepository;
import kream.clone.common.exception.ErrorCode;
import kream.clone.common.exception.KreamException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    private void checkDuplicatedBrandName(BrandCreateRequest request){
        if(brandRepository.existsByBrandName(request.getName())){
            throw new KreamException(ErrorCode.ALREADY_EXIST_BRANDNAME);
        }
    }
    @Transactional
    public void createBrand(BrandCreateRequest request) {
        checkDuplicatedBrandName(request);
        brandRepository.save(request.toBrand());
    }

    @Transactional
    public void updateBrandInfo(Long brandId, BrandUpdateRequest request) {
        Brand saveBrand = brandRepository.findById(brandId).orElseThrow(() -> new
                KreamException(ErrorCode.NOT_FOUND_BRAND));

        saveBrand.update(request);
    }

    @Transactional
    public void deleteBrand(Long brandId) {
        Brand deleteBrand = brandRepository.findById(brandId).orElseThrow(() -> new
                KreamException(ErrorCode.NOT_FOUND_BRAND));
        brandRepository.delete(deleteBrand);
    }

    public BrandInfo getBrandInfo(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(()-> new KreamException(ErrorCode.NOT_FOUND_BRAND))
                .toBrandInfo();
    }

    public List<BrandInfo> getBrandsInfo() {
        return brandRepository.findAll().stream()
                .map(Brand::toBrandInfo)
                .toList();
    }
}

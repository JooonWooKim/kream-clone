package kream.clone.brand.service;

import kream.clone.brand.dto.request.BrandCreateRequest;
import kream.clone.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    public void createBrand(BrandCreateRequest request) {
        brandRepository.save(request.toBrand());
    }
}

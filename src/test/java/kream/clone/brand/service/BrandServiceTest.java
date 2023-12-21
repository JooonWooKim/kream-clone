package kream.clone.brand.service;

import kream.clone.brand.dto.request.BrandCreateRequest;
import kream.clone.brand.dto.request.BrandUpdateRequest;
import kream.clone.brand.dto.response.BrandInfo;
import kream.clone.brand.entity.Brand;
import kream.clone.brand.repository.BrandRepository;
import kream.clone.common.exception.KreamException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static kream.clone.brand.fixture.BrandFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @Test
    public void 정상_브랜드_생성(){
        //given
        BrandCreateRequest request = new BrandCreateRequest(BRANDNAME, ORIGINIMAGEPATH, THUMBNAILIMAGEPATH);
        when(brandRepository.existsByBrandName(request.getName())).thenReturn(false);

        //when
        brandService.createBrand(request);

        //then
        verify(brandRepository, times(1)).existsByBrandName(request.getName());

        ArgumentCaptor<Brand> brandArgumentCaptor = ArgumentCaptor.forClass(Brand.class);
        verify(brandRepository, times(1)).save(brandArgumentCaptor.capture());

        Brand savedBrand = brandArgumentCaptor.getValue();
        assertThat(savedBrand.getBrandName()).isEqualTo(request.getName());
    }

    @Test
    public void 오류_중복된_브랜드_아이디(){
        //given
        BrandCreateRequest request = new BrandCreateRequest(BRANDNAME, ORIGINIMAGEPATH, THUMBNAILIMAGEPATH);

        //when
        when(brandRepository.existsByBrandName(request.getName())).thenReturn(true);

        //then
        assertThatThrownBy(()-> brandService.createBrand(request))
                .isInstanceOf(KreamException.class);
    }

    @Test
    public void 정상_브랜드_정보_수정(){
        //given
        Brand brandInfo = new Brand(BRANDID, BRANDNAME, ORIGINIMAGEPATH, THUMBNAILIMAGEPATH);

        BrandUpdateRequest updatedBrand = new BrandUpdateRequest("Test2", "Test2", "Test2");

        //when
        given(brandRepository.findById(BRANDID)).willReturn(Optional.of(brandInfo));

        //then
        brandService.updateBrandInfo(BRANDID,updatedBrand);
        BrandInfo findBrand = brandService.getBrandInfo(BRANDID);

        assertThat(findBrand.getBrandName()).isEqualTo(updatedBrand.getName());
        assertThat(findBrand.getOriginImagePath()).isEqualTo(updatedBrand.getOriginImagePath());
    }

    @Test
    public void 정상_브랜드_삭제(){
        //given
        Brand deleteBrand = new Brand(BRANDID, BRANDNAME, ORIGINIMAGEPATH, THUMBNAILIMAGEPATH);

        when(brandRepository.findById(BRANDID)).thenReturn(Optional.ofNullable(deleteBrand));
        //when
        brandService.deleteBrand(BRANDID);

        //then
        verify(brandRepository, times(1)).deleteById(BRANDID);
    }

    @Test
    public void 정상_단일_브랜드_조회(){
        //given
        Brand savedBrand = new Brand(BRANDID, BRANDNAME, ORIGINIMAGEPATH, THUMBNAILIMAGEPATH);

        when(brandRepository.findById(BRANDID)).thenReturn(Optional.ofNullable(savedBrand));

        //when
        BrandInfo result = brandService.getBrandInfo(BRANDID);

        //then
        assertThat(result);
        assertEquals(BRANDID, result.getId());
        assertEquals(BRANDNAME, result.getBrandName());

        verify(brandRepository, times(1)).findById(BRANDID);
    }



    @Test
    public void 정상_브랜드_전체_조회(){
        //given
        List<Brand> brandList = List.of(
                new Brand(1L, "Brand1", "Brand1", "Brand1")
                ,new Brand(2L, "Brand2", "Brand2", "Brand2")
                ,new Brand(3L, "Brand3", "Brand3", "Brand3")
        );

        //when
        when(brandRepository.findAll()).thenReturn(brandList);

        List<BrandInfo> result = brandService.getBrandsInfo();
        //then
        assertThat(result.size()).isEqualTo(3);
    }
    
}

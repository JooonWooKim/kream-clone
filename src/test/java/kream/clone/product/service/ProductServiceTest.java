package kream.clone.product.service;

import kream.clone.brand.dto.request.BrandCreateRequest;
import kream.clone.brand.entity.Brand;
import kream.clone.brand.repository.BrandRepository;
import kream.clone.brand.service.BrandService;
import kream.clone.common.exception.KreamException;
import kream.clone.product.dto.request.ProductCreateRequest;
import kream.clone.product.dto.request.ProductUpdateRequest;
import kream.clone.product.dto.response.ProductInfo;
import kream.clone.product.entity.Product;
import kream.clone.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static kream.clone.brand.fixture.BrandFixture.BRANDNAME;
import static kream.clone.brand.fixture.BrandFixture.ORIGINIMAGEPATH;
import static kream.clone.brand.fixture.BrandFixture.THUMBNAILIMAGEPATH;
import static kream.clone.product.fixture.ProductFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private BrandRepository brandRepository;
    @InjectMocks
    private ProductService productService;
    private Brand brand;
    private ProductCreateRequest request;
    private ProductUpdateRequest updateRequest;
    private Product product;
    @BeforeEach
    void setUp(){
        request = ProductCreateRequest.builder()
                .brandId(BRANDID)
                .name(BRANDNAME)
                .modelNumber(MODELNUMBER)
                .build();

        updateRequest = ProductUpdateRequest.builder()
                .name("updateBrandName")
                .modelNumber("updateModelNumber")
                .build();

        product = Product.builder()
                .id(PRODUCTID)
                .name(PRODUCTNAME)
                .modelNumber(MODELNUMBER)
                .build();

        brand = Brand
                .builder()
                .id(BRANDID)
                .brandName(BRANDNAME)
                .originImagePath(ORIGINIMAGEPATH)
                .thumbnailImagePath(THUMBNAILIMAGEPATH)
                .build();
    }
    @Test
    public void 정상_제품_생성(){
        //given
        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));
        when(productRepository.existsByName(request.getName())).thenReturn(false);
        when(productRepository.existsByModelNumber(request.getModelNumber())).thenReturn(false);

        //when
        productService.createProduct(request);

        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());

        Product savedProduct = productArgumentCaptor.getValue();

        assertThat(savedProduct.getName()).isEqualTo(request.getName());
    }
    @Test
    public void 오류_없는_브랜드_아이디(){
        //when
        when(brandRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        //then
        assertThatThrownBy(()-> productService.createProduct(request))
                .isInstanceOf(KreamException.class);
    }
    @Test
    public void 오류_중복된_상품_이름(){
        //when
        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));

        when(productRepository.existsByName(request.getName())).thenReturn(true);

        //then
        assertThatThrownBy(()-> productService.createProduct(request))
                .isInstanceOf(KreamException.class);
    }
    @Test
    public void 오류_중복된_상품_번호(){
        //when
        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));
        when(productRepository.existsByName(request.getName())).thenReturn(false);
        when(productRepository.existsByModelNumber(request.getModelNumber())).thenReturn(true);

        //then
        assertThatThrownBy(()-> productService.createProduct(request))
                .isInstanceOf(KreamException.class);
    }

    @Test
    public void 정상_제품_수정(){
        //when
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productRepository.existsByName(updateRequest.getName())).thenReturn(false);
        when(productRepository.existsByModelNumber(updateRequest.getModelNumber())).thenReturn(false);

        //then
        productService.updateProductInfo(PRODUCTID, updateRequest);
        ProductInfo findProduct = productService.getProductInfo(PRODUCTID);

        assertThat(findProduct.getName()).isEqualTo(updateRequest.getName());
    }

    @Test
    public void 정상_제품_삭제(){
        //given
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

        //when
        productService.deleteProduct(PRODUCTID);
    }

    @Test
    public void 오류_제품_삭제_없음(){
        //given
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        assertThatThrownBy(()-> productService.deleteProduct(PRODUCTID))
                .isInstanceOf(KreamException.class);
    }
}

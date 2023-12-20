package kream.clone.brand.repository;

import kream.clone.brand.entity.Brand;
import kream.clone.brand.fixture.BrandFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static kream.clone.brand.fixture.BrandFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    void 정상_브랜드_생성(){
        //given
        Brand brand = BrandFixture.toBrand(BRANDNAME, ORIGINIMAGEPATH, THUMBNAILIMAGEPATH);

        //when
        Brand expectedBrand = brandRepository.save(brand);

        //then
        assertThat(expectedBrand.getId()).isNotNull();
        assertThat(expectedBrand.getBrandName()).isEqualTo(BRANDNAME);
        assertThat(expectedBrand.getOriginImagePath()).isEqualTo(ORIGINIMAGEPATH);
        assertThat(expectedBrand.getThumbnailImagePath()).isEqualTo(THUMBNAILIMAGEPATH);
    }
    @Test
    void 정상_브랜드_전체_조회(){
        //given
        List<Brand> brandList = List.of(
                new Brand(1L, "Brand1", "Brand1", "Brand1")
                ,new Brand(2L, "Brand2", "Brand2", "Brand2")
                ,new Brand(3L, "Brand3", "Brand3", "Brand3")
                ,new Brand(4L, "Brand4", "Brand4", "Brand4")
        );

        //when
        List<Brand> otherBrandList = brandRepository.findAll();

        //then
        assertThat(brandList.size()).isEqualTo(otherBrandList.size());
    }
}

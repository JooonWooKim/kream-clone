package kream.clone.brand.fixture;

import kream.clone.brand.entity.Brand;

public class BrandFixture {

    public static final Long BRANDID = 1L;
    public static final String BRANDNAME = "test1";
    public static final String ORIGINIMAGEPATH = "originImagePathTest";

    public static final String THUMBNAILIMAGEPATH = "thumbnailImagePathTest";

    public static Brand toBrand(String brandName, String originImagePath, String thumbnailImagePath){
        return Brand.builder()
                .brandName(brandName)
                .originImagePath(originImagePath)
                .thumbnailImagePath(thumbnailImagePath)
                .build();
    }
}

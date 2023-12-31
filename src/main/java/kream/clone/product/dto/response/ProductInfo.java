package kream.clone.product.dto.response;

import kream.clone.product.entity.enums.Currency;
import kream.clone.product.entity.enums.SizeClassification;
import kream.clone.product.entity.enums.SizeUnit;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductInfo {
    private Long id;
    private String name;
    private String modelNumber;
    private String color;
    private LocalDate releaseDate;
    private Long releasePrice;
    private Currency currency;
    private SizeClassification sizeClassification;
    private SizeUnit sizeUnit;
    private Double minSize;
    private Double maxSize;
    private Double sizeGap;
    private String originImagePath;
    private String thumbnailImagePath;
    private String resizedImagePath;
}

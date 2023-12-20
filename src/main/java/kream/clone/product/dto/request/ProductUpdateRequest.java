package kream.clone.product.dto.request;

import kream.clone.product.entity.Product;
import kream.clone.product.entity.enums.Currency;
import kream.clone.product.entity.enums.SizeClassification;
import kream.clone.product.entity.enums.SizeUnit;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class ProductUpdateRequest {
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

    public Product toEntity(){
        return Product.builder()
                .name(this.name)
                .modelNumber(this.modelNumber)
                .color(this.color)
                .releaseDate(this.releaseDate)
                .releasePrice(this.releasePrice)
                .currency(this.currency)
                .sizeClassification(this.sizeClassification)
                .sizeUnit(this.sizeUnit)
                .minSize(this.minSize)
                .maxSize(this.maxSize)
                .sizeGap(this.sizeGap)
                .originImagePath(this.originImagePath)
                .thumbnailImagePath(this.thumbnailImagePath)
                .resizedImagePath(this.resizedImagePath)
                .build();
    }
}

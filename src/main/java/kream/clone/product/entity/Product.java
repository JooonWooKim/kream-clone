package kream.clone.product.entity;

import jakarta.persistence.*;
import kream.clone.brand.entity.Brand;
import kream.clone.common.time.Timestamped;
import kream.clone.product.dto.request.ProductCreateRequest;
import kream.clone.product.entity.enums.Currency;
import kream.clone.product.entity.enums.SizeClassification;
import kream.clone.product.entity.enums.SizeUnit;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String modelNumber;
    private String color;
    private LocalDate releaseDate;
    private Long releasePrice;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private SizeClassification sizeClassification;
    @Enumerated(EnumType.STRING)
    private SizeUnit sizeUnit;
    private Double minSize;
    private Double maxSize;
    private Double sizeGap;
    private String originImagePath;
    private String thumbnailImagePath;
    private String resizedImagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    public static Product createProduct(ProductCreateRequest request, Brand savedBrand){
        return Product.builder()
                .name(request.getName())
                .modelNumber(request.getModelNumber())
                .color(request.getColor())
                .releaseDate(request.getReleaseDate())
                .releasePrice(request.getReleasePrice())
                .currency(request.getCurrency())
                .sizeClassification(request.getSizeClassification())
                .sizeUnit(request.getSizeUnit())
                .minSize(request.getMinSize())
                .maxSize(request.getMaxSize())
                .sizeGap(request.getSizeGap())
                .originImagePath(request.getOriginImagePath())
                .thumbnailImagePath(request.getThumbnailImagePath())
                .resizedImagePath(request.getResizedImagePath())
                .brand(savedBrand)
                .build();
    }
}

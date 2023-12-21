package kream.clone.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kream.clone.product.entity.enums.Currency;
import kream.clone.product.entity.enums.SizeClassification;
import kream.clone.product.entity.enums.SizeUnit;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCreateRequest {

    @NotBlank(message = "상품 이름을 입력하세요.")
    private String name;

    @NotBlank(message = "모델 번호를 입력하세요.")
    private String modelNumber;

    @NotBlank(message = "색상을 입력하세요.")
    private String color;

    @NotNull(message = "출시일을 입력하세요")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate releaseDate;

    @NotNull(message = "출시가를 입력하세요")
    @Positive(message = "올바른 출시가를 입력하세요")
    private Long releasePrice;

    @NotNull(message = "출시가 통화를 선택하세요.")
    private Currency currency;

    @NotNull(message = "나라별 사이즈를 선택하세요.")
    private SizeClassification sizeClassification;

    @NotNull(message = "사이즈 단위를 선택하세요.")
    private SizeUnit sizeUnit;

    @Positive(message = "올바른 최소 사이즈를 입력하세요.")
    @NotNull(message = "최소 사이즈를 입력하세요.")
    private Double minSize;

    @Positive(message = "올바른 최대 사이즈를 입력하세요.")
    @NotNull(message = "최대 사이즈를 입력하세요.")
    private Double maxSize;

    @NotNull(message = "사이즈 간격을 입력하세요.")
    @Positive(message = "올바른 사이즈 간격을 입력하세요")
    private Double sizeGap;

    @NotNull(message = "상품의 브랜드를 선택하세요")
    private Long brandId;

    private String originImagePath;

    private String thumbnailImagePath;

    private String resizedImagePath;
}

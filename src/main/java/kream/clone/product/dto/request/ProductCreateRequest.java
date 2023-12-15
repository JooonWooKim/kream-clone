package kream.clone.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kream.clone.product.entity.enums.SizeClassification;
import kream.clone.product.entity.enums.SizeUnit;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Currency;
import java.util.List;

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
    private Long releasePrice;

    @NotBlank(message = "출시가 통화를 선택하세요.")
    private Currency currency;

    @NotBlank(message = "나라별 사이즈를 선택하세요.")
    private SizeClassification sizeClassification;

    @NotBlank(message = "사이즈 단위를 선택하세요.")
    private SizeUnit sizeUnit;

    @Positive(message = "올바른 최소 사이즈를 입력하세요.")
    @NotBlank(message = "최소 사이즈를 입력하세요.")
    private Double minSize;

    @Positive(message = "올바른 최대 사이즈를 입력하세요.")
    @NotBlank(message = "최대 사이즈를 입력하세요.")
    private Double minSize;

    @NotBlank(message = "상품 설명을 입력해주세요.")
    private String description;

    private int size;

    private List<MultipartFile> images;
}

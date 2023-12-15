package kream.clone.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ProductRegisterRequest {

    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "상품 이름을 입력해주세요.")
    private int releasePrice;

    @NotBlank(message = "상품 설명을 입력해주세요.")
    private String description;

    private int size;

    private List<MultipartFile> images;

}

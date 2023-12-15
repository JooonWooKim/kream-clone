package kream.clone.brand.dto.request;

import kream.clone.brand.entity.Brand;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandCreateRequest {
    private String name;
    private String originImagePath;
    private String thumbnailImagePath;

    public Brand toBrand(){
        return Brand.builder()
                .brandName(this.name)
                .originImagePath(this.originImagePath)
                .thumbnailImagePath(this.thumbnailImagePath)
                .build();
    }
}

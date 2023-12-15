package kream.clone.brand.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandUpdateRequest {
    private String name;
    private String originImagePath;
    private String thumbnailImagePath;
}

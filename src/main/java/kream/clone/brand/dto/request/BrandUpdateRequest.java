package kream.clone.brand.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BrandUpdateRequest {
    private String name;
    private String originImagePath;
    private String thumbnailImagePath;


}

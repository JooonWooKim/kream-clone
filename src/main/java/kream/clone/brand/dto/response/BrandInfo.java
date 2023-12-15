package kream.clone.brand.dto.response;

import io.lettuce.core.CompositeArgument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandInfo {
    private Long id;
    private String brandName;
    private String originImagePath;
    private String thumbnailImagePath;
}

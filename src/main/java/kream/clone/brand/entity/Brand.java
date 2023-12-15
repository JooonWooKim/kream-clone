package kream.clone.brand.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kream.clone.common.time.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brandName;

    private String originImagePath;

    private String thumbnailImagePath;

    @Builder
    public Brand(Long id, String brandName, String originImagePath, String thumbnailImagePath){
        this.id = id;
        this.brandName = brandName;
        this.originImagePath = originImagePath;
        this.thumbnailImagePath = thumbnailImagePath;
    }
}

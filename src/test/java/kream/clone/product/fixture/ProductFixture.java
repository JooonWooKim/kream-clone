package kream.clone.product.fixture;

import kream.clone.product.entity.enums.Currency;
import kream.clone.product.entity.enums.SizeClassification;
import kream.clone.product.entity.enums.SizeUnit;

import java.time.LocalDate;

public class ProductFixture {
    public static final Long PRODUCTID = 1L;
    public static final String PRODUCTNAME = "productname";
    public static final String MODELNUMBER = "modelnumber";
    public static final String COLOR = "color";
    public static final LocalDate LOCALDATE = LocalDate.parse("2023-12-18");
    public static final Long RELEASEPRICE = 10000L;
    public static final Currency CURRENCY = Currency.valueOf("KRW");
    public static final SizeClassification SIZECLASSIFICATION = SizeClassification.valueOf("MEN");
    public static final SizeUnit SIZEUNIT = SizeUnit.valueOf("MM");
    public static final Double MINSIZE = 11.0;
    public static final Double MAXSIZE = 2.0;
    public static final Double SIZEGAP = 1.0;
    public static final String ORIGINIMAGEPATH = "originImagePath";
    public static final String THUMBNAILIMAGEPATH = "thumbnailImagePath";
    public static final String RESIZEDIMAGEPATH = "resizedImagePath";
    public static final Long BRANDID = 1L;

}

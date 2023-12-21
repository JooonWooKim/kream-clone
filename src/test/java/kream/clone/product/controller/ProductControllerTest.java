package kream.clone.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kream.clone.product.dto.request.ProductCreateRequest;
import kream.clone.product.dto.request.ProductUpdateRequest;
import kream.clone.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static kream.clone.product.fixture.ProductFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
    @Test
    public void 정상_제품_생성() throws Exception{
        //given
        ProductCreateRequest request = new ProductCreateRequest(PRODUCTNAME, MODELNUMBER, COLOR, LOCALDATE, RELEASEPRICE,
                CURRENCY, SIZECLASSIFICATION, SIZEUNIT, MINSIZE, MAXSIZE, SIZEGAP, BRANDID ,ORIGINIMAGEPATH, THUMBNAILIMAGEPATH, RESIZEDIMAGEPATH);

        //when
        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("제품 생성 성공"));
    }

    @Test
    public void 정상_제품_정보_수정() throws Exception{
        //given
        ProductUpdateRequest updateRequest = new ProductUpdateRequest("test2", MODELNUMBER, COLOR, LOCALDATE, RELEASEPRICE,
                CURRENCY, SIZECLASSIFICATION, SIZEUNIT, MINSIZE, MAXSIZE, SIZEGAP ,ORIGINIMAGEPATH, THUMBNAILIMAGEPATH, RESIZEDIMAGEPATH);

        doNothing().when(productService).updateProductInfo(eq(PRODUCTID), any(updateRequest.getClass()));
        //when
        mockMvc.perform(put("/api/product/{productId}", PRODUCTID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("제품 정보 수정 성공"));

    }

    @Test
    public void 정상_제품_삭제() throws Exception{
        //given
        doNothing().when(productService).deleteProduct(PRODUCTID);
        //when
        mockMvc.perform(delete("/api/product/{productId}", PRODUCTID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("제품 삭제 성공"));

    }
}

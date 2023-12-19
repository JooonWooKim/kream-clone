package kream.clone.brand.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kream.clone.brand.dto.request.BrandCreateRequest;
import kream.clone.brand.dto.request.BrandUpdateRequest;
import kream.clone.brand.dto.response.BrandInfo;
import kream.clone.brand.service.BrandService;
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

import java.util.List;

import static kream.clone.brand.fixture.BrandFixture.*;
import static kream.clone.member.fixture.MemberFixture.USERNAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BrandControllerTest {
    @Mock
    private BrandService brandService;
    @InjectMocks
    private BrandController brandController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();
    }
    @Test
    public void 정상_브랜드_생성() throws Exception{
        //given
        BrandCreateRequest request = new BrandCreateRequest(USERNAME, ORIGINIMAGEPATH, THUMBNAILIMAGEPATH);

        //when
        mockMvc.perform(post("/api/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("브랜드생성성공"));
    }
    @Test
    public void 정상_브랜드_정보_수정() throws Exception{
        //given
        BrandUpdateRequest updateRequest = new BrandUpdateRequest("Test2", "Test2", "Test2");

        doNothing().when(brandService).updateBrandInfo(eq(BRANDID), any(updateRequest.getClass()));

        //when
        mockMvc.perform(put("/api/brand/{brandId}", BRANDID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("브랜드정보수정성공"));
    }
    @Test
    public void 정상_브랜드_삭제() throws Exception{
        //when
        doNothing().when(brandService).deleteBrand(BRANDID);

        mockMvc.perform(delete("/api/brand/{brandId}", BRANDID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("브랜드삭제성공"));
    }
    @Test
    public void 정상_단일_브랜드_정보_조회() throws Exception{
        //given
        BrandInfo request = new BrandInfo(BRANDID, USERNAME, ORIGINIMAGEPATH, THUMBNAILIMAGEPATH);

        //when
        when(brandService.getBrandInfo(BRANDID)).thenReturn(request);

        //then
        mockMvc.perform(get("/api/brand/{brandId}", BRANDID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("브랜드단일정보조회성공"));

    }

    @Test
    public void 정상_브랜드_리스트_조회() throws Exception{
        //given
        List<BrandInfo> brandList = List.of(
                new BrandInfo(1L, "Brand1", "Brand1", "Brand1")
                ,new BrandInfo(2L, "Brand2", "Brand2", "Brand2")
        );

        //when
        when(brandService.getBrandsInfo()).thenReturn(brandList);

        //then
        mockMvc.perform(get("/api/brand/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("브랜드정보리스트조회성공"));

    }
}

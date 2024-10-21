package com.amalie.thymeleaf.touristguide.controller;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.model.TouristAttractionTagDTO;
import com.amalie.thymeleaf.touristguide.repository.CurrencyService;
import com.amalie.thymeleaf.touristguide.service.TouristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class) //vi vil kun teste en controller
class TouristControllerTest {

    private TouristAttractionTagDTO touristAttractionTagDTO = new TouristAttractionTagDTO();

    @Autowired //spring instantierer selv objekt
    private MockMvc mockMvc;

    @MockBean //gør os i stand til at ui unittest controller
    private TouristService touristService;

    @MockBean //mock af interne objekter
    private CurrencyService currencyConverterService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }

    @Test
    void showTags() throws Exception {
        int attractionId = 1;
        TouristAttraction t = new TouristAttraction();
        t.setTourist_id(attractionId);

        List<Tag> tags = Arrays.asList(new Tag("ballon", 1), new Tag("gynge", 2));

        when(touristService.getAttractionById(attractionId)).thenReturn(t);
        when(touristService.getTags(t)).thenReturn(tags);

        mockMvc.perform(get("/attractions/{id}/tags", attractionId))
                .andExpect(status().isOk())
                .andExpect(view().name("showTags"))
                .andExpect(content().string(containsString("ballon")));

    }
    @Test
    void createTouristAttractionForm() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("dto"))
                .andExpect(view().name("addAttraction"));
    }

    @Test
    void saveTouristAttraction() throws Exception {
        mockMvc.perform(post("/save").sessionAttr("touristAttraction", this.touristAttractionTagDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attractions"));
    }


}
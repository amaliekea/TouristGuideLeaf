package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class TouristRepositoryTest {

    @Autowired //en måde at inistanisere et objekt
    TouristRepository touristRepository;

    @Test
    void getTivoliAttraction() {
        TouristAttraction found = touristRepository.getAttractionById(1);
        assertEquals("Tivoli", found.getName());
    }
    @Test
    void getAllAttractions() {
        int found = touristRepository.getAllAttractions().size();
        assertEquals(1, found);
    }

    @Test
    void getCitiesLength() {
        int foundLength = touristRepository.getCities().size();
        assertEquals(3, foundLength);
    }

    @Test
    void getTagsLength() {
        int foundLength = touristRepository.getAvaliableTags().size();
        assertEquals(3, foundLength);
    }
}

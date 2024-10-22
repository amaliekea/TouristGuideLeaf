package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.model.TouristAttractionTagDTO;

import java.util.List;

public interface ITouristRepository {
    //CREATE
    void saveDTOAttraction(TouristAttractionTagDTO t);

    //READ
    List<TouristAttraction> getAllAttractions();

    List<TouristAttractionTagDTO> getAllDTOAttractions();

    List<City> getCities();

    List<Tag> getAvaliableTags();

    TouristAttraction getAttractionById(int id);

    TouristAttractionTagDTO getDTOAttractionById(int id);

    List<Tag> getTags(TouristAttraction t);

    //UPDATE
    void updateAttraction(TouristAttractionTagDTO dto);

    //DELETE
    void deleteDTOAttraction(int id);
}


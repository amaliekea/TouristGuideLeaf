package com.amalie.thymeleaf.touristguide.controller;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.model.TouristAttractionTagDTO;
import com.amalie.thymeleaf.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService; //vi inistaniserer
    }


    @GetMapping("/attractions")
    public String getAttractions(@RequestParam(defaultValue = "EUR") String valuta, Model model) { // model bruger spring til at kommunikere med th
        List<TouristAttractionTagDTO> touristAttractions = touristService.getAllDTOAttractions();
        model.addAttribute("attractions", touristAttractions); //sender med som argument
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("valuta", valuta);
        return "attractionList";
    }

    @PostMapping("/attractions")
    public String getValuta(@RequestParam String valuta, RedirectAttributes redirectAttributes) { //request fordi vi skal sende en request med som typisk ligger i html
        if (!valuta.equals("EUR")) {
            valuta = "DKK";
        }
        redirectAttributes.addAttribute("valuta", valuta);
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/{id}/tags")
    public String getAttractionTags(@PathVariable int id, Model model) { //pathvariable fordi den kommer fra url path
        TouristAttraction t = touristService.getAttractionById(id);
        model.addAttribute("attraction", t);
        model.addAttribute("tags", touristService.getTags(t));
        return "showTags";
    }
    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttractionTagDTO tagDTO = new TouristAttractionTagDTO();
        model.addAttribute("dto", tagDTO);
        model.addAttribute("availableTags", touristService.getAvaliableTags());
        model.addAttribute("cities", touristService.getCities());
        return "addAttraction";
    }

    @PostMapping("/save")
    public String addAttraction(@ModelAttribute TouristAttractionTagDTO dto, Model model) throws Exception {
        touristService.saveDTOAttraction(dto);
        return "redirect:/attractions";
    }

    @GetMapping("/{id}/edit")
    public String editAttraction(@PathVariable int id, Model model) {
        TouristAttractionTagDTO t = touristService.getDTOAttractionById(id);
        model.addAttribute("attraction", t);
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("description", t.getDescription());
        model.addAttribute("availableTags", touristService.getAvaliableTags());
        return "editAttraction";
    }

    @PostMapping("/update")
    public String editAttraction(@ModelAttribute TouristAttractionTagDTO dto) {
        touristService.updateAttraction(dto);
        return "redirect:/attractions"; //redirect: sig til klienten bed om denne side
    }


    @PostMapping("/delete")
    public String deleteAttraction(@RequestParam int id) {
        touristService.deleteDTOAttraction(id);
        return "redirect:/attractions";
    }

}

package com.portafolio.my_portafolio_backend.controller;

import com.portafolio.my_portafolio_backend.service.IEducationService;
import com.portafolio.my_portafolio_backend.service.IExperienceService;
import com.portafolio.my_portafolio_backend.service.IPersonalInfoService;
import com.portafolio.my_portafolio_backend.service.ISkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IPersonalInfoService personalInfoService;
    private final IEducationService educationService;
    private final ISkillService skillService;
    private final IExperienceService experienceService;

    @GetMapping("/")
    public String showIndex(Model model){
        System.out.println("Mostrando la pàgina de inicio");

        model.addAttribute("personalInfo", personalInfoService.findAll().getFirst());
        model.addAttribute("experienceList", experienceService.findAll());
        model.addAttribute("educationList", educationService.findAll());
        model.addAttribute("skills", skillService.findAll());


        return "index";
    }


}

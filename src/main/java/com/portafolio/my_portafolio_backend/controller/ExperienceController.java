package com.portafolio.my_portafolio_backend.controller;

import com.portafolio.my_portafolio_backend.dto.ExperienceDTO;
import com.portafolio.my_portafolio_backend.dto.SkillDTO;
import com.portafolio.my_portafolio_backend.mapper.ExperienceMapper;
import com.portafolio.my_portafolio_backend.mapper.SkillMapper;
import com.portafolio.my_portafolio_backend.model.Experience;
import com.portafolio.my_portafolio_backend.service.IExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;

    @GetMapping
    public String listExperience(Model model){
        List<Experience> experienceList = experienceService.findAll();

        List<ExperienceDTO> experienceDTOS = experienceList.stream()
                                          .map(ExperienceMapper::toDTO)
                                          .toList();

        model.addAttribute("experienceList", experienceDTOS);
        return "experience/list-experience";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        ExperienceDTO newExperienceDTO = new ExperienceDTO();
        newExperienceDTO.setStartDate(LocalDate.now());
        model.addAttribute("experienceDTO", new ExperienceDTO());
        return "experience/form-experience";
    }

    @PostMapping("/save")
    public String saveExperience(@Valid @ModelAttribute("experienceDTO") ExperienceDTO experienceDTO,
                                 BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "experience/form-experience";
        }
        try{
            Experience experience = ExperienceMapper.toEntity(experienceDTO);
            experienceService.save(experience);
            return "redirect:/experience";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "error al guardar la experiencia laboral: " + e.getMessage());
            return "redirect:/experience";
        }
    }

    @GetMapping("/edit/id")
    public String showEditForm(@PathVariable Long id, Model model){
        Optional<Experience> experienceOptional = experienceService.findById(id);

        if(experienceOptional.isPresent()){
            ExperienceDTO experienceDTO = ExperienceMapper.toDTO(experienceOptional.get());
            model.addAttribute("experienceDTO", experienceDTO);
            return "experience/form-experience";
        }else {
            model.addAttribute("errorMessage", "Experiencia no encontrada con el ID: " + id);
            return "redirect:/experience";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listSkillByPersonalInfoId(@PathVariable Long personalInfoId, Model model){
        List <Experience> experienceList = experienceService.findByPersonalInfoId(personalInfoId);

        List<ExperienceDTO> experienceDTOS = experienceList.stream()
                .map(ExperienceMapper::toDTO).toList();

        model.addAttribute("experienceList", experienceDTOS);
        return "experience/list-experience";
    }

    @PostMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try{
            experienceService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Habilidad eliminada con éxito!");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la habildiad: " + e.getMessage());
        }

        return "redirect:/experience";
    }


}

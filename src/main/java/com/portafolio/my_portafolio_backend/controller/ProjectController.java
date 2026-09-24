package com.portafolio.my_portafolio_backend.controller;
import com.portafolio.my_portafolio_backend.dto.ProjectDTO;
import com.portafolio.my_portafolio_backend.mapper.ProjectMapper;
import com.portafolio.my_portafolio_backend.model.Project;
import com.portafolio.my_portafolio_backend.service.FileStorageService;
import com.portafolio.my_portafolio_backend.service.IProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final IProjectService projectService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public String getAll(Model model){
        List<ProjectDTO> projects = projectService.findAll().stream()
                .map(ProjectMapper::toDTO)
                .toList();
        model.addAttribute("projects", projects);
        return "projects/list";
    }

    @GetMapping("/new-project")
    public String showForm(Model model){
        model.addAttribute("projectDTO", new ProjectDTO());
        return "projects/form-project"; // o el nombre eligido
    }

    @PostMapping("/save")
    public String saveProject(
            @Valid @ModelAttribute("projectDTO") ProjectDTO projectDTO,
            BindingResult result,
            @RequestParam("file")MultipartFile file){

        if (file.isEmpty()){
            result.rejectValue("imageUrl", "file.required", "La imagen del proyecto es obligatoria");
        }

        if (result.hasErrors()){
            return "projects/form-project";
        }

        try {
            String imageUrl = fileStorageService.storeFile(file);

            projectDTO.setImageUrl(imageUrl);

            Project project = ProjectMapper.toEntity(projectDTO);

            projectService.save(project);
            return "redirect:/projects";


        } catch (IOException e) {
            return "error-page";
        }
    }

}

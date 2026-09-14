package com.portafolio.my_portafolio_backend.rest;

import com.portafolio.my_portafolio_backend.model.Experience;
import com.portafolio.my_portafolio_backend.service.IExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {

    private final IExperienceService experienceService;

    public ExperienceController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/all")
    public List<Experience> getAllExperience(){
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public Experience getExperiencieById(@PathVariable Long id){
        Optional<Experience> experience = experienceService.findById(id);
        if (experience.isPresent()){
            return experience.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Información de experiencia no disponible en el ID: " + id);
        }
    }

    @PostMapping()
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience){
        Experience newExperience = experienceService.save(experience);
        return new ResponseEntity<>(newExperience, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Experience update(@PathVariable Long id, @RequestBody Experience experience){
        experience.setId(id);
        return experienceService.save(experience);
    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id){
        experienceService.deleteById(id);
    }


}

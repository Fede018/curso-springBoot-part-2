package com.portafolio.my_portafolio_backend.rest;

import com.portafolio.my_portafolio_backend.model.Education;
import com.portafolio.my_portafolio_backend.service.IEducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education")
public class EducationController {
    private final IEducationService educationService;

    public EducationController(IEducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/all")
    public List<Education> getAllEducation() {
        return educationService.findAll();
    }

    @GetMapping("/{id}")
    public Education getEducationById(@PathVariable Long id){
        Optional<Education> education = educationService.findById(id);
        if(education.isPresent()){
            return education.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Información educativa no disponible en el ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education){
        Education newEducation = educationService.save(education);
        return new ResponseEntity<>(newEducation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Education update(@PathVariable Long id, @RequestBody Education education){
        education.setId(id);
        return educationService.save(education);
    }

    @DeleteMapping("/{id}")
    public void deteleBy(@PathVariable Long id){
        educationService.deleteById(id);
    }


}

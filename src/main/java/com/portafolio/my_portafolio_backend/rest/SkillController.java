package com.portafolio.my_portafolio_backend.rest;


import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.service.ISkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

    private final ISkillService skillService;

    public SkillController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/all")
    public List<Skill> getAllSkill(){
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id){
        Optional <Skill> skill = skillService.findById(id);
        if (skill.isPresent()){
            return skill.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Informacion de Skill no disponible con el ID: " + id);
        }
    }

    @GetMapping("/personal-info/{id}")
    public List<Skill> findBySkillsByPersonalInfoId(@PathVariable("id") Long personalInfoId){
        return skillService.findSkillByPersonalInfoId(personalInfoId);
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill){
        Skill newSkill = skillService.save(skill);
        return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill skill){
        skill.setId(id);
        return skillService.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteBy(@PathVariable Long id){
        skillService.deleteById(id);
    }
}

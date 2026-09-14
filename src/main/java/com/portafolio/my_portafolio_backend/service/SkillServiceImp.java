package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.repository.ISkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImp implements ISkillService{

    private final ISkillRepository skillRepository;


    @Override
    public Skill save(Skill skill) {
        if(skill.getLevelPercentaje() <0 || skill.getLevelPercentaje() > 100){
            throw new IllegalArgumentException("El porcentaje es incorrecto debe estar entre 0 y 100");
        }

        return skillRepository.save(skill);
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return skillRepository.findbyId(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> findSkillByPersonalInfoId(Long personalInfoid) {
        return skillRepository.findSkillByPersonalInfoId(personalInfoid);
    }
}

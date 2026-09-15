package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.repository.ISkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImp implements ISkillService{

    private final ISkillRepository skillRepository;
    private final Validator validator;


    @Override
    @Transactional
    public Skill save(Skill skill) {

        BindingResult result = new BeanPropertyBindingResult(skill, "skill");
        validator.validate(skill, result);
        if (result.hasErrors()){
            System.out.println("Erorres de validación encontrados: " + result.getAllErrors());
            throw new ValidationException(result);
        }

        return skillRepository.save(skill);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Skill> findById(Long id) {
        return skillRepository.findbyId(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findSkillByPersonalInfoId(Long personalInfoid) {
        return skillRepository.findSkillByPersonalInfoId(personalInfoid);
    }
}

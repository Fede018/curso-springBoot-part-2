package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Experience;
import com.portafolio.my_portafolio_backend.repository.IExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements IExperienceService{

    private final IExperienceRepository experienceRepository;

    @Override
    @Transactional
    public Experience save(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        System.out.println("Eliminando experience por ID: " + id + " en el servicio...");
        experienceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findByPersonalInfoId(Long personalInfoId) {
        return experienceRepository.findByPersonalInfoId(personalInfoId);
    }
}

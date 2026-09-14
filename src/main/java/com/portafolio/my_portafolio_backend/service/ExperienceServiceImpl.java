package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Experience;
import com.portafolio.my_portafolio_backend.repository.IExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements IExperienceService{

    private final IExperienceRepository experienceRepository;

    @Override
    public Experience save(Experience experience) {
        // Validación 1: Asegurar que la fecha de inicio no sea nula
        if(experience.getStartDate() == null) {
            throw new IllegalArgumentException("La fecha de inicio de la experiencia no puede estar vacía.");
        }

        // Validación 2: La feche de inicio no puede ser posterior a la de fin (solo si end_date no es nula)
        if(experience.getEndDate()!= null && experience.getStartDate().isAfter(experience.getEndDate())) {
            throw new IllegalArgumentException("La fecha de inicio de la experiencia no puede ser posterior a la fecha de fin.");
        }

        // Validaciones 3 y 4 (ya estaban bien)
        if(experience.getJobTitle()==null || experience.getJobTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo del trabajo no puede estar vacío.");
        }

        if (experience.getCompanyName()==null || experience.getCompanyName().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre de la compañia no puede estar vacío");
        }


        return experienceRepository.save(experience);
    }

    @Override
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public List<Experience> findByPersonalInfoId(Long personalInfoId) {
        return experienceRepository.findByPersonalInfoId(personalInfoId);
    }
}

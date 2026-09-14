package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Education;
import com.portafolio.my_portafolio_backend.repository.IEducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducationService{

    private final IEducationRepository educationRepository;

    @Override
    public Education save(Education education) {
        // Validación 1: Asegurar que la fecha de incio no sea nula, como exige la BD
        if (education.getStartDate() == null) {
            throw new IllegalArgumentException("Le fecha de inicio de la educación no puede estar vacía.");
        }

        // Validación 2: La fecha de incio no puede ser posterior a la de fin
        if(education.getEndDate() != null && education.getStartDate().isAfter(education.getEndDate())) {
            throw new IllegalArgumentException("La fecha de inicio de la educación no puede ser posterior a la fecha de fin");
        }

        return educationRepository.save(education);
    }

    @Override
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }

    @Override
    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findByPersonalInfoId(personalInfoId);
    }
}

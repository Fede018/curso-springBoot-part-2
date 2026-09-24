package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Education;
import com.portafolio.my_portafolio_backend.repository.IEducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducationService{

    private final IEducationRepository educationRepository;

    @Override
    @Transactional
    public Education save(Education education) {
        return educationRepository.save(education);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        System.out.println("Eliminando education por ID: " + id + " en el servicio...");
        educationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findByPersonalInfoId(personalInfoId);
    }
}

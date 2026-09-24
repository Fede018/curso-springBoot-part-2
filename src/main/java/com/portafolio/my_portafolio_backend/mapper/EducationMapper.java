package com.portafolio.my_portafolio_backend.mapper;

import com.portafolio.my_portafolio_backend.dto.EducationDTO;
import com.portafolio.my_portafolio_backend.model.Education;

public class EducationMapper {

    public static EducationDTO toDTO(Education education){
        if(education == null){
            return null;
        }

        EducationDTO educationDTO = new EducationDTO();

        educationDTO.setId(education.getId());
        educationDTO.setDegree(education.getDegree());
        educationDTO.setStartDate(education.getStartDate());
        educationDTO.setEndDate(education.getEndDate());
        educationDTO.setDescription(education.getDescription());
        educationDTO.setPersonalInfoId(education.getPersonalInfoId());

        return educationDTO;
    }


    public static Education toEntity(EducationDTO educationDTO){

        if(educationDTO == null){
            return null;
        }

        Education  education = new Education();

        education.setId(educationDTO.getId());
        education.setDegree(educationDTO.getDegree());
        education.setStartDate(educationDTO.getStartDate());
        education.setEndDate(educationDTO.getEndDate());
        education.setDescription(educationDTO.getDescription());
        education.setPersonalInfoId(educationDTO.getPersonalInfoId());

        return education;
    }

}

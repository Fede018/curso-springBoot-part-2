package com.portafolio.my_portafolio_backend.mapper;

import com.portafolio.my_portafolio_backend.dto.ExperienceDTO;
import com.portafolio.my_portafolio_backend.model.Experience;

public class ExperienceMapper {

    public static ExperienceDTO toDTO(Experience experience){
        if(experience == null){
            return null;
        }

        ExperienceDTO experienceDTO = new ExperienceDTO();

        experienceDTO.setId(experience.getId());
        experienceDTO.setCompanyName(experience.getCompanyName());
        experienceDTO.setStartDate(experience.getStartDate());
        experienceDTO.setEndDate(experience.getEndDate());
        experienceDTO.setDescription(experience.getDescription());
        experienceDTO.setPersonalInfoId(experience.getPersonalInfoId());

        return experienceDTO;
    }


    public static Experience toEntity(ExperienceDTO experienceDTO){
        if (experienceDTO == null){
            return null;
        }

        Experience experience = new Experience();

        experience.setId(experienceDTO.getId());
        experience.setCompanyName(experienceDTO.getCompanyName());
        experience.setStartDate(experienceDTO.getStartDate());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setDescription(experienceDTO.getDescription());
        experience.setPersonalInfoId(experienceDTO.getPersonalInfoId());

        return experience;
    }
}

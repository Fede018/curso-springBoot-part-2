package com.portafolio.my_portafolio_backend.mapper;

import com.portafolio.my_portafolio_backend.dto.PersonalInfoDTO;
import com.portafolio.my_portafolio_backend.model.PersonalInfo;

public class PersonalInfoMapper {

    public static PersonalInfoDTO toDTO(PersonalInfo personalInfo){
        if(personalInfo == null){
            return null;
        }

        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();

        personalInfoDTO.setId(personalInfo.getId());
        personalInfoDTO.setFirstName(personalInfo.getFirstName());
        personalInfoDTO.setLastName(personalInfo.getLastName());
        personalInfoDTO.setTitle(personalInfo.getTitle());
        personalInfoDTO.setProfileDescription(personalInfo.getProfileDescription());
        personalInfoDTO.setProfileImageUrl(personalInfo.getProfileImageUrl());
        personalInfoDTO.setYearsOfExperience(personalInfo.getYearsOfExperience());
        personalInfoDTO.setEmail(personalInfo.getEmail());
        personalInfoDTO.setPhone(personalInfo.getPhone());
        personalInfoDTO.setLinkedinUrl(personalInfo.getLinkedinUrl());
        personalInfoDTO.setGithubUrl(personalInfo.getGithubUrl());


        return personalInfoDTO;
    }


    public static PersonalInfo toEntity(PersonalInfoDTO personalInfoDTO){

        if(personalInfoDTO == null){
            return null;
        }

        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setId(personalInfoDTO.getId());
        personalInfo.setFirstName(personalInfoDTO.getFirstName());
        personalInfo.setLastName(personalInfoDTO.getLastName());
        personalInfo.setTitle(personalInfoDTO.getTitle());
        personalInfo.setProfileDescription(personalInfoDTO.getProfileDescription());
        personalInfo.setProfileImageUrl(personalInfoDTO.getProfileImageUrl());
        personalInfo.setYearsOfExperience(personalInfoDTO.getYearsOfExperience());
        personalInfo.setEmail(personalInfoDTO.getEmail());
        personalInfo.setPhone(personalInfoDTO.getPhone());
        personalInfo.setLinkedinUrl(personalInfoDTO.getLinkedinUrl());
        personalInfo.setGithubUrl(personalInfoDTO.getGithubUrl());

        return personalInfo;
    }
}

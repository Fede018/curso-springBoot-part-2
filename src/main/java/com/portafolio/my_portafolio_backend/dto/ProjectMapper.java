package com.portafolio.my_portafolio_backend.dto;

import com.portafolio.my_portafolio_backend.model.Project;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project){
        if(project == null){
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(project.getId());
        projectDTO.setTitle(project.getTitle());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setImageUrl(project.getImageUrl());
        projectDTO.setProjectUrl(project.getProjectUrl());
        projectDTO.setPersonalInfoId(project.getPersonalInfoId());

        return projectDTO;
    }



    public static Project toEntity(ProjectDTO dto){

        if (dto == null){
            return null;
        }

        Project project = new Project();

        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setImageUrl(dto.getImageUrl());
        project.setProjectUrl(dto.getProjectUrl());
        project.setPersonalInfoId(dto.getPersonalInfoId());

        return project;
    }


}

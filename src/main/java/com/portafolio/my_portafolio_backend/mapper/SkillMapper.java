package com.portafolio.my_portafolio_backend.mapper;

import com.portafolio.my_portafolio_backend.dto.SkillDTO;
import com.portafolio.my_portafolio_backend.model.Skill;

public class SkillMapper {

    public static SkillDTO toDTO(Skill skill){
        if(skill ==null){
            return null;
        }

        SkillDTO skillDTO = new SkillDTO();

        skillDTO.setId(skill.getId());
        skillDTO.setName(skill.getName());
        skillDTO.setLevelPercentaje(skill.getLevelPercentaje());
        skillDTO.setIconClass(skill.getIconClass());
        skillDTO.setPersonalInfoId(skill.getPersonalInfoId());
        return skillDTO;
    }

    public static Skill toEntity(SkillDTO skillDTO){
        if(skillDTO == null){
            return null;
        }

        Skill skill = new Skill();

        skill.setId(skillDTO.getId());
        skill.setName(skillDTO.getName());
        skill.setLevelPercentaje(skillDTO.getLevelPercentaje());
        skill.setIconClass(skillDTO.getIconClass());
        skill.setPersonalInfoId(skillDTO.getPersonalInfoId());

        return skill;
    }




}

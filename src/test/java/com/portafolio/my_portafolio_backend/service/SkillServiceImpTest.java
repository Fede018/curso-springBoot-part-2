package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.repository.ISkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImpTest {

    @Mock
    private ISkillRepository skillRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private SkillServiceImp skillService;

    @Test
    void testFindAllReturnListOfSkills(){

        // Arange (preracion)
        List<Skill> mockSkill = Arrays.asList(new Skill(), new Skill());
        when(skillRepository.findAll()).thenReturn(mockSkill);

        // Act (action)
        List<Skill> skills = skillService.findAll();

        // Assert (verificacion)
        assertNotNull(skills);
        assertEquals(2, skills.size());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsSkillWhenFound(){
        // Arrange (preparacion)
        Long id = 1L;
        Skill skillMock = new Skill();
        when(skillRepository.findbyId(id)).thenReturn(Optional.of(skillMock));

        // Act (action)
        Optional<Skill> skillOptional = skillService.findById(id);

        // Assert (verificacion)
        assertTrue(skillOptional.isPresent());
        assertEquals(skillMock, skillOptional.get());
        verify(skillRepository, times(1)).findbyId(id);
    }

    @Test
    void testSaveSkillThrowsExceptionWhenInvalid(){
        // Arrange (preparacion)
        Skill invalidSkill = new Skill();
        doAnswer(invocationOnMock -> {
            BindingResult result =invocationOnMock.getArgument(1);
            result.rejectValue("name", "NotBlanck", "El nombre no puede estar vacìo");
            return null;
        }).when(validator).validate(any(Skill.class), any(BindingResult.class));

        // Act (action)
        assertThrows(ValidationException.class, () -> skillService.save(invalidSkill),
                "Debe lanzarse una ValidationException si el objeto no es válido.");

        // Assert (verificacion)
        verify(skillRepository, never()).save(any(Skill.class));

    }

    @Test
    void testSaveSkillSavesValidSkill(){
        // Arrange (preparacion)
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill);
        doNothing().when(validator).validate(any(Skill.class), any(BindingResult.class));

        // Act (action)
        Skill savedSkill = skillService.save(validSkill);

        // Assert (verificacion)
        assertNotNull(savedSkill);
        verify(skillRepository, times(1)).save(validSkill);

    }

}

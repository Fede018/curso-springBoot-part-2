package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.model.Experience;
import com.portafolio.my_portafolio_backend.repository.IExperienceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ExperienceServiceImplTest {
    
        @Mock
        private IExperienceRepository experienceRepository;

        @Mock
        private Validator validator;

        @InjectMocks
        private ExperienceServiceImpl experienceService;

        @Test
        void testFindAllReturnsListOfExperience() {
            // Arrange
            List<Experience> mockEducation = Arrays.asList(new Experience(), new Experience());
            when(experienceRepository.findAll()).thenReturn(mockEducation);

            // Act
            List<Experience> result = experienceService.findAll();

            // Assert
            assertNotNull(result);
            assertEquals(2, result.size());
            verify(experienceRepository, times(1)).findAll();
        }

        @Test
        void testFindByIdReturnsEducationWhenFound() {
            // Arrange
            Long educationId = 1L;
            Experience mockEducation = new Experience();
            when(experienceService.findById(educationId)).thenReturn(Optional.of(mockEducation));

            // Act
            Optional<Experience> result = experienceService.findById(educationId);

            // Assert
            assertTrue(result.isPresent());
            assertEquals(mockEducation, result.get());
            verify(experienceRepository, times(1)).findById(educationId);
        }

        @Test
        void testSaveEducationThrowsExceptionWhenInvalid() {
            // Arrange
            Experience invalidExperience = new Experience();

            doAnswer(invocationOnMock -> {
                BindingResult result = invocationOnMock.getArgument(1);
                result.rejectValue("jobTitle", "NotBlank", "El título no puede estar vacío");
                return null;
            }).when(validator).validate(any(Experience.class), any(BindingResult.class));

            // Act & Assert
            assertThrows(ValidationException.class, () -> experienceService.save(invalidExperience),
                    "Debe lanzarse una ValidationException si el objeto no es válido.");
            verify(experienceRepository, never()).save(any(Experience.class));
        }

        @Test
        void testSaveEducationSavesValidEducation() {
            // Arrange
            Experience validExperience = new Experience(null, "Full Stack Developer", "Company ABC", LocalDate.of(2015, 3, 1),
                                                    LocalDate.of(2020, 12, 1), "Descripción de logros y responsabilidad", 1L);
            when(experienceRepository.save(any(Experience.class))).thenReturn(validExperience);
            doNothing().when(validator).validate(any(Experience.class), any(BindingResult.class));

            // Act
            Experience savedEducation = experienceService.save(validExperience);

            // Assert
            assertNotNull(savedEducation);
            verify(experienceRepository, times(1)).save(validExperience);
        }
    }
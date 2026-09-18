package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.model.Education;
import com.portafolio.my_portafolio_backend.repository.IEducationRepository;
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
class EducationServiceImplTest {

    @Mock
    private IEducationRepository educationRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private EducationServiceImpl educationService;

    @Test
    void testFindAllReturnListOfEducations() {
        // Arange (preparacion)
        List<Education> mockEducation = Arrays.asList(new Education(), new Education());
        when(educationRepository.findAll()).thenReturn(mockEducation);

        // Act (action)
        List<Education> educations = educationService.findAll();

        // Assert (verificacion)
        assertNotNull(educations);
        assertEquals(2, educations.size());
        verify(educationRepository, times(1)).findAll();

    }

    @Test
    void testFindByIdReturnsEducationWhenFound(){
        // Arrange (preparacion)
        Long id = 1L;
        Education educationMock = new Education();
        when(educationRepository.findById(id)).thenReturn(Optional.of(educationMock));

        // Act (action)
       Optional<Education> educationOptional = educationService.findById(id);

        // Assert (verificacion)
        assertTrue(educationOptional.isPresent());
        assertEquals(educationMock, educationOptional.get());
        verify(educationRepository, times(1)).findById(id);
    }

    @Test
    void testsaveEducationThrowsExceptionWhenInvalid(){
        //Arrange
        Education invalidEducation = new Education();

        doAnswer(invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("degree", "NotBlank", "El titulo no puede estar vacío");
            return null;
        }).when(validator).validate(any(Education.class), any(BindingResult.class));

        // Act & Assert
        assertThrows(ValidationException.class, () -> educationService.save(invalidEducation),
                "Debe lanzarse una ValidationException si el objeto no es válido");
        verify(educationRepository, never()).save(any(Education.class));
    }

    @Test
    void testSaveEducationSavesValidEducation(){
        // Arrange
        Education validEducation = new Education(null, "Ingeniería en Sistemas",
                "UTN", LocalDate.of(2015, 3, 1), LocalDate.of(2020, 12, 1),
                "Descripcion de la carrera" ,1L);
        when(educationRepository.save(any(Education.class))).thenReturn(validEducation);
        doNothing().when(validator).validate(any(Education.class), any(BindingResult.class));

        // Act
        Education savedEducation = educationService.save(validEducation);

        //Assert
        assertNotNull(savedEducation);
        verify(educationRepository, times(1)).save(validEducation);

    }


}
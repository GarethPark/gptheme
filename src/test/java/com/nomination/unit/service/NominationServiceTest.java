package com.nomination.unit.service;

import com.nomination.dao.entity.Nomination;
import com.nomination.dao.repository.NominationRepository;
import com.nomination.dao.repository.NominationSpecificationBuilder;
import com.nomination.http.model.NominationDTO;
import com.nomination.service.NominationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static com.nomination.unit.model.NominationTestBuilder.aNomination;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NominationServiceTest {

    @Captor ArgumentCaptor<List<Long>> idsCaptor;
    @Captor ArgumentCaptor<List<Long>> nomHrIdsCaptor;
    @Captor ArgumentCaptor<List<String>> nomEmailsCaptor;

    private final NominationRepository nominationRepository = mock(NominationRepository.class);
    private final NominationService service = new NominationService(nominationRepository);
    private NominationSpecificationBuilder specifcationBuilder;

    private Nomination nomination;

    @BeforeEach
    void init(){

        nomination = aNomination().withId(1L).withNomineeHrId(123L).withNomineeEmail("test@test.com").withRequestorId(234L).withRequestorEmail("testrequest@email.com").build();
        when(nominationRepository.findAll()).thenReturn(new ArrayList<>());
        //The ArgumentMatchers.<Specification<Nomination>>any() method is used to indicate that any Specification<Nomination> object can be passed as the first argument.
        //The (Sort) any() method is used to indicate that any Sort object can be passed as the second argument.
        when(nominationRepository.findAll(ArgumentMatchers.<Specification<Nomination>>any(), (Sort) any())).thenReturn(List.of(nomination));

    }

    private void stubSpecification(){
        //mock the SpecBuilder class
        specifcationBuilder = mock(NominationSpecificationBuilder.class);
        //lambda expression creates a supplier that returns the specifcationBuilder object whenever it is invoked
        service.setNominationSpecificationBuilderFactory(() -> specifcationBuilder);
        //the when method from Mockito to specify the behavior of the withIds method of the specifcationBuilder mock object.
        // In this case, the withIds method is expected to be called with any argument (as specified by the any() method),
        // and it will return the same specifcationBuilder mock object.
        // This allows the behavior of the NominationSpecificationBuilder to be controlled during testing,
        // without actually invoking the real withIds method.
        when(specifcationBuilder.withIds(any())).thenReturn(specifcationBuilder);
        when(specifcationBuilder.withNomineeHrIds(any())).thenReturn(specifcationBuilder);
        when(specifcationBuilder.withNomineeEmails(any())).thenReturn(specifcationBuilder);

        //sets the behavior of the build method of the specifcationBuilder mock object to
        //return a Specification instance with null as the criteria predicate.
        //Specification is a type in the Spring Data JPA framework used to specify the criteria for querying data from a database.
        //The where method of Specification takes a Predicate argument, which is used to construct the query criteria.

        when(specifcationBuilder.build()).thenReturn(Specification.where(null));

    }

    @Test
    void NominationsWithNoFilters_shouldReturnAllNominations(){
        stubSpecification();
        NominationDTO.GetRequest request = NominationDTO.GetRequest.builder().build();

        try{
            //we are testing that NO FILTERS are applied!

            //Run method
            List<NominationDTO.GetResponse> result = service.getNominations(request);

            //This code is performing a Mockito verification on a specificationBuilder object to ensure that
            //its withNomineeHrIds method was called exactly once during the test.

            //The verify method checks that the withNomineeHrIds method was called exactly once on the specificationBuilder object,
            //and that the captured value of the nomHrIdsCaptor is null.

            //TODO - have a verify for all filters!

            verify(specifcationBuilder, times(1)).withNomineeHrIds(nomHrIdsCaptor.capture());
            assertNull(nomHrIdsCaptor.getValue());

            verify(specifcationBuilder, times(1)).withNomineeEmails(nomEmailsCaptor.capture());
            assertNull(nomEmailsCaptor.getValue());

            assertNotNull(result);
            assertEquals(1, result.size());


        } catch (Exception e){
            e.printStackTrace();
            fail("Should not throw exception " + e);
        }
    }

    @Test
    void getNominationsById_shouldReturnFilteredNominations(){
        stubSpecification();
        NominationDTO.GetRequest request = NominationDTO.GetRequest.builder().ids(List.of(nomination.getId())).build();

        try{
            List<NominationDTO.GetResponse> result = service.getNominations(request);

            verify(specifcationBuilder, times(1)).withIds(idsCaptor.capture());
            assertNotNull(result);
            assertEquals(1, result.size());

        } catch (Exception e){
            e.printStackTrace();
            fail("Should not throw exception: " + e);
        }
    }
    //TODO - complete all tests!
}

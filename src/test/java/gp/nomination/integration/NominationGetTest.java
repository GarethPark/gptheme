package gp.nomination.integration;

import gp.nomination.dao.entity.Nomination;
import gp.nomination.dao.repository.NominationRepository;
import gp.nomination.http.model.NominationDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static gp.nomination.unit.model.NominationTestBuilder.aNomination;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NominationGetTest  {
    @Autowired
    private NominationRepository nominationRepository;

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "api/aconominations";  //TODO check this  +"?nomineeEmail=test@db.com"

    private List<Nomination> nominations;

    @BeforeAll
    void initData(){
        //TODO Business Groups and Units
        nominations = nominationRepository.saveAll(List.of(
                aNomination().withNomineeHrId(1L).withNomineeEmail("test@db.com").withRequestorId(1L).withRequestorEmail("requestor-10@db.com").withStatus("APPROVED").build(),
                aNomination().withNomineeHrId(1L).withNomineeEmail("test@db.com").withRequestorId(1L).withRequestorEmail("requestor-10@db.com").withStatus("REJECTED").build(),
                aNomination().withNomineeHrId(2L).withNomineeEmail("test@db.com").withRequestorId(1L).withRequestorEmail("requestor-10@db.com").withStatus("APPROVED").build(),
                aNomination().withNomineeHrId(2L).withNomineeEmail("test@db.com").withRequestorId(1L).withRequestorEmail("requestor-10@db.com").withStatus("APPROVED").build()
                ));
    }
    @BeforeEach
    void stub(){
        //Stub EntitlementsClient
    }

    @Test  //?? what are you testing here?
    void getNominationsWithOnlyNomineeParams_ShouldReturnAllNomineeNominations(){
        ParameterizedTypeReference<List<NominationDTO.GetResponse>> responseType = new ParameterizedTypeReference<List<NominationDTO.GetResponse>>() {};

        ResponseEntity<List<NominationDTO.GetResponse>> response = restTemplate.exchange("http://localhost:" + port + "/" + url, HttpMethod.GET, null, responseType);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() >=3);
    }
    @Test
    void getNominationsWithNomineeHrId_ShouldReturnNominations(){
        ParameterizedTypeReference<List<NominationDTO.GetResponse>> responseType = new ParameterizedTypeReference<List<NominationDTO.GetResponse>>() {} ;
        ResponseEntity<List<NominationDTO.GetResponse>> response = restTemplate.exchange("http://localhost:" + port + "/" + url + "&nomineeHRId=1", HttpMethod.GET, null, responseType);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() >=2);
        assertTrue(response.getBody().stream().map(NominationDTO.GetResponse::getNomineeHrId).allMatch(Set.of(1L)::contains));
        assertTrue(response.getBody().stream().map(NominationDTO.GetResponse::getNomineeHrId).noneMatch(Set.of(2L)::contains));
    }
}

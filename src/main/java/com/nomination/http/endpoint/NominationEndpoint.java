package com.nomination.http.endpoint;

import com.nomination.http.model.NominationDTO;
import com.nomination.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class NominationEndpoint {

    private final NominationService service;
    @Autowired
    public NominationEndpoint(NominationService service) {this.service = service;}

    public List<NominationDTO.GetResponse> getNominations (
            @RequestParam(name = "nomineeHrId", required = false) List<Long> nomineeHrIds,
            @RequestParam(name = "id", required = false) List<Long> ids,
            @RequestParam(name = "nomineeEmail", required = false) List<String> nomineeEmails
    ){
        //TODO Logic around status

        NominationDTO.GetRequest request = NominationDTO.GetRequest
                .builder()
                .ids(ids)
                .nomineeEmails(nomineeEmails)
                .nomineeHrIds(nomineeHrIds)
                .build();

        return service.getNominations(request);
    }
}

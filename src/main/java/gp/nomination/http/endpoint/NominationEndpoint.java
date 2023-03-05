package gp.nomination.http.endpoint;

import gp.nomination.http.model.NominationDTO;
import gp.nomination.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aconominations")
public class NominationEndpoint {

    private final NominationService service;
    @Autowired
    public NominationEndpoint(NominationService service) {this.service = service;}

    @GetMapping   //When applied to a method in a Spring Controller class, @GetMapping maps the HTTP GET requests to that method
    @ResponseBody    //annotation that is used to indicate that the return value of a method should be serialized directly into the response body of an HTTP response
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

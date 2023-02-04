package com.nomination.http.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NominationDTO {

    public NominationDTO(){}

    private interface Id {@NotNull Long getId();}
    private interface NomineeHrId {@NotNull Long getNomineeHrId();}
    private interface NomineeEmail {@NotNull String getNomineeEmail();}
    private interface RequestorEmail {@NotNull String getRequestorEmail();}
    private interface  RequestorHrId {@NotNull Long getRequestorHrId();}

    private interface Status {@NotNull Long getStatus();}

    @Data
    @Builder
    public static class GetRequest{
        private List<Long> ids;
        private List<Long> nomineeHrIds;
        private List<Long> requestorHrIds;
        private List<String> nomineeEmails;
        private List<String> requestorEmails;
        private List<String> statuses;

    }
    @Data
    @Builder
    public static class GetResponse implements Id, NomineeHrId, NomineeEmail, RequestorEmail, RequestorHrId, Status {
        private Long id;
        private Long nomineeHrId;
        private String nomineeEmail;
        private String requestorEmail;
        private Long requestorHrId;
        private String status;
    }
}

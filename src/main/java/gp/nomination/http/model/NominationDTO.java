package gp.nomination.http.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

//By making a class final, you are essentially saying that you do not want any other classes to modify or extend the behavior of this class.
//This can be useful for classes that have a well-defined responsibility and should not be changed by other classes or modules in the system.
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class NominationDTO {

    private interface Id {@NotNull Long getId();}
    private interface NomineeHrId {@NotNull Long getNomineeHrId();}
    private interface NomineeEmail {@NotNull String getNomineeEmail();}
    private interface RequestorHrId {@NotNull Long getRequestorHrId();}
    private interface RequestorEmail {@NotNull String getRequestorEmail();}
    private interface Status {@NotNull String getStatus();}

    @Data
    @Builder
    public static class GetRequest {
        private List<Long> ids;
        private List<Long> nomineeHrIds;
        private List<Long> requestorHrIds;
        private List<String> nomineeEmails;
        private List<String> requestorEmails;
        private List<String> statuses;

    }
    @Data
    @Builder
    public static class GetResponse implements Id, NomineeHrId, NomineeEmail, RequestorHrId, RequestorEmail, Status {
        private Long id;
        private Long nomineeHrId;
        private String nomineeEmail;
        private Long requestorHrId;
        private String requestorEmail;
        private String status;
    }
    @Data
    @Builder
    public static class PostRequest implements NomineeHrId, NomineeEmail, RequestorHrId, RequestorEmail, Status {
        private Long nomineeHrId;
        private String nomineeEmail;
        private Long requestorHrId;
        private String requestorEmail;

        private String status;
    }

    @Data
    @Builder
    public static class PostResponse implements Id, NomineeHrId, NomineeEmail, RequestorHrId, RequestorEmail, Status {
        private Long id;
        private Long nomineeHrId;
        private String nomineeEmail;
        private Long requestorHrId;
        private String requestorEmail;

        private String status;
    }
}

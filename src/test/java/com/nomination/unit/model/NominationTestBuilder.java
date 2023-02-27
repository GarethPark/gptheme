package com.nomination.unit.model;

import com.nomination.dao.entity.Nomination;
import com.utils.Builder;

public class NominationTestBuilder implements Builder<Nomination> {
    private Long id;
    private Long nomineeHrId = 123L;
    private String nomineeEmail = "test.nominee@db.com";
    private String status = "OPEN";
    private Long requestorId = 234L;
    private String requestorEmail = "test.requestor@db.com";

    //The aNomination() method is a static factory method that returns a new instance of the builder.
    //The NominationTestBuilder() constructor is private,
    //which means that the builder can only be instantiated through the aNomination() factory method.
    public static NominationTestBuilder aNomination(){
        return new NominationTestBuilder();
    }
    private NominationTestBuilder(){};
    public NominationTestBuilder withId(Long id){
        this.id = id;
        return this;
    }
    public NominationTestBuilder withNomineeHrId(Long nomineeHrId){
        this.nomineeHrId = nomineeHrId;
        return this;
    }
    public NominationTestBuilder withNomineeEmail (String nomineeEmail){
        this.nomineeEmail = nomineeEmail;
        return this;
    }
    public NominationTestBuilder withStatus (String status){
        this.status = status;
        return this;
    }
    public NominationTestBuilder withRequestorId(Long requestorId){
        this.requestorId = requestorId;
        return this;
    }
    public NominationTestBuilder withRequestorEmail (String requestorEmail){
        this.requestorEmail = requestorEmail;
        return this;
    }
    @Override
    public Nomination build() {
        final var nomination = new Nomination();
        nomination.setId(id);
        nomination.setNomineeHRId(nomineeHrId);
        nomination.setNomineeEmail(nomineeEmail);
        nomination.setRequestorHRId(requestorId);
        nomination.setRequestorEmail(requestorEmail);
        return nomination;
    }
}

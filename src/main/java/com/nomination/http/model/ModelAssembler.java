package com.nomination.http.model;

import com.nomination.dao.entity.Nomination;

public final class ModelAssembler {
    private ModelAssembler(){}
    //PASS In a Nomination ENTITY and return a Nomination DTO
    public static NominationDTO.GetResponse toNominationGetResponse(Nomination nomination){
        return NominationDTO.GetResponse.builder()
                .id(nomination.getId())
                .nomineeHrId(nomination.getNomineeHRId())
                .nomineeEmail(nomination.getNomineeEmail())
                .requestorHrId(nomination.getRequestorHRId())
                .requestorEmail(nomination.getRequestorEmail())
                .build();
    }
}

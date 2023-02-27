package com.nomination.dao.assembler;

/*
Need to understand why we have this and where it is done
 */

import com.nomination.dao.entity.Nomination;
import com.nomination.http.model.NominationDTO;

public final class JPAAssembler {

    private JPAAssembler() {
    }

    public static Nomination toEntity(NominationDTO.PostRequest request) {
        return Nomination.builder()
                .nomineeEmail(request.getNomineeEmail())
                .nomineeHRId(request.getNomineeHrId())
                .requestorEmail(request.getRequestorEmail())
                .requestorHRId(request.getNomineeHrId())
                .status(request.getStatus())
                .build();
    }
}



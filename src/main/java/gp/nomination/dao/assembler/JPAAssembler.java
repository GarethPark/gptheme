package gp.nomination.dao.assembler;

/*
Need to understand why we have this and where it is done
 */

import gp.nomination.dao.entity.Nomination;
import gp.nomination.http.model.NominationDTO;

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



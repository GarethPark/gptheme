package com.nomination.dao.entity;

import javax.persistence.*;

import lombok.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "KYCELIG_OWNER", name = "ACO_NOMINATION")
//TODO - must extend auditable
public class Nomination {
    @Id
    @SequenceGenerator(sequenceName = "ACO_NOMINATION_SEQ", allocationSize = 1, name = "ACO_NOMINATION_SEQ", schema = "KYCELIG_OWNER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ACO_NOMINATION_SEQ")
    @Column(name = "ID")
    private Long id;
    @Column(name="NOMINEE_HR_ID")
    private Long nomineeHRId;
    @Column(name="NOMINEE_EMAIL")
    private String nomineeEmail;
    @Column(name="REQUESTOR_HR_ID")
    private Long requestorHRId;
    @Column(name="REQUESTOR_EMAIL")
    private String requestorEmail;
    @Column(name="STATUS")
    private String status;
    @Column(name="APPROVAL_DATE")
    private ZonedDateTime approvalDate;
}

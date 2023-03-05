package gp.nomination.dao.repository;

import gp.nomination.dao.entity.Nomination;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.List;

/*
Given an object sometimes is necessary to apply different conditions to a query,
requiring to create a lot of different methods for each possible combination.
The Specification Pattern derives from concepts introduced in Eric Evans’ Domain Driven Design book.
It provides a design pattern that allows us to separate the search criteria from the object that performs the search.
 */
public class NominationSpecificationBuilder {
    private List<Long> ids;
    private List<Long> nomineeHrIds;
    private List<String> nomineeEmails;
    private List<Long> requestorHRIds;
    private List<String> requestorEmails;
    private List<String> statuses;
    private List<ZonedDateTime> approvalDates;

    public NominationSpecificationBuilder withIds(List<Long> ids){
        this.ids = ids;
        return this;
    }
    public NominationSpecificationBuilder withNomineeHrIds(List<Long> nomineeHrIds){
        this.nomineeHrIds = nomineeHrIds;
        return this;
    }
    public NominationSpecificationBuilder withNomineeEmails(List<String> nomineeEmails){
        this.nomineeEmails = nomineeEmails;
        return this;
    }

    public Specification<Nomination> build(){
        return Specification
                .where(((Specification<Nomination>) (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, "nomineeHrId", nomineeHrIds))
                        .and((Specification<Nomination>) (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, "nomineeEmail", nomineeEmails))
                                .and((Specification<Nomination>) (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, "requestorHrId", requestorHRIds))
                                .and((Specification<Nomination>) (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, "requestorEmail", requestorEmails))
                                .and((Specification<Nomination>) (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, "requestorEmail", requestorEmails))
                                .and((Specification<Nomination>) (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, "status", statuses))
                                .and((Specification<Nomination>) (root, criteriaQuery, criteriaBuilder) -> getPredicate(root, criteriaBuilder, "approvalDate", approvalDates))
                        );
    }
    private Predicate getPredicate(Root<Nomination> root, CriteriaBuilder criteriaBuilder, String filterName, List<?> filters){
        if (filters == null || filters.isEmpty()){
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return root.get(filterName).in(filters);
    }
}

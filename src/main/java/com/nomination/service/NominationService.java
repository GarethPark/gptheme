package com.nomination.service;


import com.nomination.dao.entity.Nomination;
import com.nomination.dao.repository.NominationRepository;
import com.nomination.dao.repository.NominationSpecificationBuilder;
import com.nomination.http.model.ModelAssembler;
import com.nomination.http.model.NominationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Validated
public class NominationService{

    private final NominationRepository nominationRepository;
    //NominationSpecificationBuilder::new is a method reference that is used to create a new instance of the NominationSpecificationBuilder class. The :: operator is used to refer to a method without invoking it.
    private Supplier<NominationSpecificationBuilder> nominationSpecificationBuilderFactory = NominationSpecificationBuilder::new;
    @Autowired
    public NominationService(NominationRepository nominationRepository){
        this.nominationRepository = nominationRepository;
    }
    //The "Supplier" interface is a functional interface in Java that represents a supplier of objects.
    // In this case, the supplier is expected to provide an instance of "NominationSpecificationBuilder".
    public void setNominationSpecificationBuilderFactory(Supplier<NominationSpecificationBuilder> nominationSpecificationBuilderFactory){
        this.nominationSpecificationBuilderFactory = nominationSpecificationBuilderFactory;
    }

    public List<NominationDTO.GetResponse> getNominations (NominationDTO.GetRequest request){
        //TODO add business logic
        //Build the specification
        Specification<Nomination> specification = nominationSpecificationBuilderFactory.get()
                .withIds(request.getIds())
                .withNomineeHrIds(request.getNomineeHrIds())
                .withNomineeEmails(request.getNomineeEmails())
                .build();

        //Pass the specification to the repository
        //Return the NOMINATIONS ENTITY
        List<Nomination> nominations = nominationRepository.findAll(specification, Sort.by(Sort.Direction.DESC, "createdDate"));
        if (nominations.isEmpty()) return new ArrayList<>();
        //1.Stream the entity nominations
        //2.The Stream map() method is used to transform elements of a stream by applying a mapping function to each element
        //3.The double colon (::) operator, also known as method reference operator in Java,
        // is used to call a method by referring to it with the help of its class directly.
        // They behave exactly as the lambda expressions.

        return nominations.stream().map(ModelAssembler::toNominationGetResponse).collect(Collectors.toList());

        //The mutable reduction operation is called collect(),
        // as it collects together the desired results into a result container such as a Collection.
        // A collect operation requires three functions: a supplier function to construct new instances of
        // the result container, an accumulator function to incorporate an input element into a result container,
        // and a combining function to merge the contents of one result container into another.
    }
}

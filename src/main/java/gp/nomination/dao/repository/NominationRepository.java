package gp.nomination.dao.repository;

import gp.nomination.dao.entity.Nomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface NominationRepository extends JpaRepository<Nomination, Long>, JpaSpecificationExecutor<Nomination> {

    List<Nomination> findByIdIn(Set<Long> Id);
    List<Nomination> findAllByStatus(String status);

    //TODO do an explicit query
    //@Query("SELECT")
}

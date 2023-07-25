package com.recruitment.job.domain;

import com.recruitment.job.domain.repositories.ports.ResourceRequestCountRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ResourceRequestCountJakartaRepository extends ResourceRequestCountRepository, CrudRepository<String, ResourceRequestCount> {

}

package com.recruitment.job.infrastructure.counter;

import com.recruitment.job.domain.request.ResourceRequestCount;
import com.recruitment.job.domain.request.port.ResourceRequestCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceRequestCountPersistenceServiceAdapter implements ResourceRequestCountRepository {
    private final ResourceRequestCountJakartaRepository resourceRequestCountJakartaRepository;

    @Override
    public Optional<ResourceRequestCount> findByLogin(String login) {
        return resourceRequestCountJakartaRepository.findByLogin(login);
    }

    @Override
    public void save(ResourceRequestCount resourceRequestCount) {
        resourceRequestCountJakartaRepository.save(resourceRequestCount);
    }
}

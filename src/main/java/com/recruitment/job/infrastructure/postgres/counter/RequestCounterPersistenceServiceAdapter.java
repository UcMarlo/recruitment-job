package com.recruitment.job.infrastructure.postgres.counter;

import com.recruitment.job.domain.request.RequestCounter;
import com.recruitment.job.domain.request.port.RequestCounterRepository;
import com.recruitment.job.infrastructure.postgres.counter.exception.RequestCounterNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestCounterPersistenceServiceAdapter implements RequestCounterRepository {
    private final ResourceRequestCountJakartaRepository resourceRequestCountJakartaRepository;

    @Override
    public Optional<RequestCounter> findByLogin(String login) {
        Optional<RequestCounterEntity> requestCounterEntity = resourceRequestCountJakartaRepository.findByLogin(login);
        if (requestCounterEntity.isPresent()) {
            return requestCounterEntity.map(RequestCounterEntity::toDomainObject);
        }

        return Optional.empty();
    }

    @Override
    public void save(RequestCounter requestCounter) {
        resourceRequestCountJakartaRepository.save(
                new RequestCounterEntity(
                        requestCounter.getLogin(),
                        requestCounter.getRequestCount()
                ));
    }

    @Override
    @Transactional
    public void update(RequestCounter requestCounter) {
        RequestCounterEntity entityToUpdate = resourceRequestCountJakartaRepository.findByLogin(requestCounter.getLogin())
                .orElseThrow(() -> new RequestCounterNotFoundException("Cannot update an entity" + requestCounter.toString() + "- is not present in database"));
        entityToUpdate.updateEntity(requestCounter);
    }

}

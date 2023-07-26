package com.recruitment.job.domain.request;

import com.recruitment.job.domain.request.port.ResourceRequestCountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceRequestCountService {
    private final ResourceRequestCountRepository resourceRequestCountRepository;

    @Transactional
    public void registerNewRequest(String login){
        resourceRequestCountRepository.findByLogin(login)
                .ifPresentOrElse(ResourceRequestCount::increaseCounter, ()-> createNewResourceRequestCount(login));
    }

    private ResourceRequestCount createNewResourceRequestCount(String login){
        ResourceRequestCount resourceRequestCount = new ResourceRequestCount(login);
        resourceRequestCountRepository.save(resourceRequestCount);
        resourceRequestCount.increaseCounter();
        return resourceRequestCount;
    }
}

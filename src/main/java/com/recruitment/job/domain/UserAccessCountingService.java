package com.recruitment.job.domain;

import com.recruitment.job.domain.request.ResourceRequestCountService;
import com.recruitment.job.domain.user.UserService;
import com.recruitment.job.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccessCountingService {
    private final UserService userService;
    private final ResourceRequestCountService resourceRequestCountService;

    public UserDto accessUserResource(String login){
        UserDto user = userService.findUser(login);
        resourceRequestCountService.registerNewRequest(login);

        return user;
    }
}

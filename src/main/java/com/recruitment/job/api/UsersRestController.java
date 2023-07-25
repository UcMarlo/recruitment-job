package com.recruitment.job.api;

import com.recruitment.job.api.dto.UserResponseDto;
import com.recruitment.job.domain.UserAccessCountingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersRestController {
    private final UserAccessCountingService userAccessCountingService;

    @GetMapping("/{login}")
    public UserResponseDto getUserByLogin(@PathVariable String login){
        return UserResponseDto.createResponseDto(userAccessCountingService.accessUserResource(login));
    }
}

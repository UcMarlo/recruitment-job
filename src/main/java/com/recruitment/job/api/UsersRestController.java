package com.recruitment.job.api;

import com.recruitment.job.api.dto.UserMetadataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersRestController {

    @GetMapping("/{login}")
    public UserMetadataDto getUserByLogin(@PathVariable String login){
        throw new RuntimeException("Not implemented yet");
    }
}

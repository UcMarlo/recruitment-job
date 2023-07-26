package com.recruitment.job.domain.user;

import com.recruitment.job.domain.user.dto.UserDto;
import com.recruitment.job.domain.exception.ResourceNotFoundException;
import com.recruitment.job.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
     private final UserRepository userRepository;
     private final UserCalculator userCalculator;

    public UserDto findUser(String login){
        User user = userRepository.findUserByLogin(login)
                .orElseThrow(ResourceNotFoundException::new);

        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getName(),
                user.getType(),
                user.getAvatarUri(),
                user.getCreatedAt(),
                userCalculator.performCalculations(user.followers_count, user.publicRepositoriesCount).orElse(null)
        );
    }


}

package com.taskmates.backend.mapper;


import com.taskmates.backend.model.dto.MemberDTO;
import com.taskmates.backend.model.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MemberDTOMapper implements Function<UserEntity, MemberDTO> {
    @Override
    public MemberDTO apply(UserEntity userEntity) {
        return new MemberDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getUsername(),
                userEntity.getProfilePictureUrl()
        );
    }
}

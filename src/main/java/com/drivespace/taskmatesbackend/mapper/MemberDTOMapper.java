package com.drivespace.taskmatesbackend.mapper;


import com.drivespace.taskmatesbackend.model.dto.MemberDTO;
import com.drivespace.taskmatesbackend.model.entity.UserEntity;
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

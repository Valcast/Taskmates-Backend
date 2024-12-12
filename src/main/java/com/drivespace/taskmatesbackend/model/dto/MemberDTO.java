package com.drivespace.taskmatesbackend.model.dto;

import java.util.UUID;

public record MemberDTO(
        UUID id,
        String name,
        String surname,
        String username,
        String profilePictureUrl
) {
}

package com.taskmates.backend.model.dto;

import java.util.UUID;

public record MemberDTO(
        UUID id,
        String name,
        String surname,
        String username,
        String profilePictureUrl
) {
}

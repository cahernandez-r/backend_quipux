package com.quipux.playlistmanager.domains.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtAuthenticationResponse {

    private String token;
}

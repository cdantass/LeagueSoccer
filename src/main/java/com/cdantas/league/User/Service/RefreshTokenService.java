package com.cdantas.league.User.Service;

import com.cdantas.league.User.Entity.RefreshToken;
import com.cdantas.league.User.Entity.User;
import com.cdantas.league.User.Repository.RefreshTokenRepository;
import com.cdantas.league.infra.exception.InvalidRefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-expiration-ms}")
    private long refreshExpirationMs;

    @Transactional
    public RefreshToken create(User user) {
        var refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiresAt(Instant.now().plusMillis(refreshExpirationMs))
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public void revokeAllForUser(Long userId) {
        refreshTokenRepository.revokeAllByUserId(userId);
    }

    @Transactional
    public User validateAndRotate(String token) {
        var refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidRefreshToken("Refresh token inválido"));

        if (refreshToken.isRevoked()) {
            throw new InvalidRefreshToken("Refresh token revogado");
        }

        if (refreshToken.isExpired()) {
            throw new InvalidRefreshToken("Refresh token expirado, faça login novamente");
        }

        // Rotação: revoga o antigo assim que usado, evita reuso do mesmo refresh token
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);

        return refreshToken.getUser();
    }
}
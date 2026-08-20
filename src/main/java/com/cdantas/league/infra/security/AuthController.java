package com.cdantas.league.infra.security;

import com.cdantas.league.User.Entity.User;
import com.cdantas.league.User.Repository.UserRepository;
import com.cdantas.league.User.Service.RefreshTokenService;
import com.cdantas.league.infra.exception.EmailAlreadyInUse;
import com.cdantas.league.infra.exception.TooManyLoginAttempts;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final LoginRateLimiter loginRateLimiter;

    @PostMapping("/login")
    public AuthDtos.TokenResponse login(@Valid @RequestBody AuthDtos.LoginRequest request) {
        String key = request.email().toLowerCase();

        if (loginRateLimiter.isBlocked(key)) {
            throw new TooManyLoginAttempts("Muitas tentativas de login. Tente novamente em alguns minutos.");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (BadCredentialsException ex) {
            loginRateLimiter.registerFailure(key);
            throw ex;
        }

        loginRateLimiter.reset(key);

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        User user = userRepository.findByEmail(request.email()).orElseThrow();

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = refreshTokenService.create(user).getToken();

        return new AuthDtos.TokenResponse(accessToken, refreshToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthDtos.TokenResponse register(@Valid @RequestBody AuthDtos.RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyInUse("Email já cadastrado: " + request.email());
        }

        User user = User.builder()
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .role("USER")
                .enabled(true)
                .build();

        userRepository.save(user);

        String accessToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.create(user).getToken();

        return new AuthDtos.TokenResponse(accessToken, refreshToken);
    }

    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal User user) {
        refreshTokenService.revokeAllForUser(user.getId());
    }

    @PostMapping("/refresh")
    public AuthDtos.TokenResponse refresh(@Valid @RequestBody AuthDtos.RefreshRequest request) {
        User user = refreshTokenService.validateAndRotate(request.refreshToken());

        String newAccessToken = jwtService.generateToken(user);
        String newRefreshToken = refreshTokenService.create(user).getToken();

        return new AuthDtos.TokenResponse(newAccessToken, newRefreshToken);
    }
}
package KiwiDevSoft.clinia.infrastructure.services;

import KiwiDevSoft.clinia.api.dto.request.AuthRequest;
import KiwiDevSoft.clinia.api.dto.request.RegisterRequest;
import KiwiDevSoft.clinia.api.dto.response.AuthResponse;
import KiwiDevSoft.clinia.domain.entities.Account;
import KiwiDevSoft.clinia.domain.repositories.AccountRepository;
import KiwiDevSoft.clinia.infrastructure.helpers.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /*----------------------------
     * REGISTER USER
     * ----------------------------
     */
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String email = request.getEmail().toLowerCase().trim();

        if (accountRepository.existsByEmail(email)) {
            throw new IllegalStateException("The email '" + email + "' is already registered.");
        }

        Account account = Account.builder()
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .build();

        accountRepository.save(account);

        // ✅ Usar generateToken en lugar de getToken
        String token = jwtService.generateToken(account.getEmail());

        return new AuthResponse(token, token);
    }

    /*----------------------------
     * LOGIN USER
     * ----------------------------
     */
    public AuthResponse login(AuthRequest request) {
        String email = request.getEmail().toLowerCase().trim();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, request.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password.");
        }

        Account user = accountRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password."));

        // ✅ Usar generateToken en lugar de getToken
        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, token);
    }
}

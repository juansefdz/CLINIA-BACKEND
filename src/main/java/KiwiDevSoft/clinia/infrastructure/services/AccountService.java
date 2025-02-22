package KiwiDevSoft.clinia.infrastructure.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import KiwiDevSoft.clinia.api.dto.request.AccountRequest;
import KiwiDevSoft.clinia.api.dto.request.update.AccountUpdateRequest;
import KiwiDevSoft.clinia.api.dto.response.AccountResponse;
import KiwiDevSoft.clinia.domain.entities.Account;
import KiwiDevSoft.clinia.domain.repositories.AccountRepository;
import KiwiDevSoft.clinia.infrastructure.abstract_services.IAccountService;
import KiwiDevSoft.clinia.infrastructure.mappers.AccountMapper;
import KiwiDevSoft.clinia.util.enums.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    /*----------------------------
     * CREATE ACCOUNT
     * ----------------------------
     */
    @Transactional
    public AccountResponse create(AccountRequest request) {
        validateAccountRequest(request);

        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("The email '" + request.getEmail() + "' is already registered.");
        }

        Account account = Account.builder()
                .email(request.getEmail().toLowerCase().trim())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(getValidRole(request.getRole()))
                .createdAt(LocalDateTime.now())
                .build();

        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

    /*----------------------------
     * SOFT DELETE ACCOUNT
     * ----------------------------
     */
    @Transactional
    @Override
    public void delete(String id) {
        Account account = find(id);
        account.setActive(false);
        accountRepository.save(account);
    }

    /*----------------------------
     * GET ALL ACCOUNTS
     * ----------------------------
     */
    @Override
    public Page<AccountResponse> getAll(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(accountMapper::toAccountResponse);
    }

    /*----------------------------
     * GET ACCOUNT BY ID
     * ----------------------------
     */
    @Override
    public Optional<AccountResponse> getById(String id) {
        return accountRepository.findById(id)
                .map(accountMapper::toAccountResponse);
    }

    /*----------------------------
     * UPDATE ACCOUNT
     * ----------------------------
     */
    @Transactional
    @Override
    public AccountResponse update(String id, AccountUpdateRequest request) {
        Account account = find(id);

        if (request.getEmail() != null && !request.getEmail().trim().isEmpty()) {
            account.setEmail(request.getEmail().toLowerCase().trim());
        }
        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            account.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getRole() != null && !request.getRole().trim().isEmpty()) {
            account.setRole(getValidRole(request.getRole()));
        }

        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

    /*----------------------------
     * FIND ACCOUNT BY ID
     * ----------------------------
     */
    private Account find(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Account with id %s not found", id)));
    }

    /*----------------------------
     * VALIDATION HELPERS
     * ----------------------------
     */
    private void validateAccountRequest(AccountRequest request) {
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }
    }

    private Role getValidRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            return Role.PATIENT;
        }
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role +
                    ". Available roles: " + java.util.Arrays.toString(Role.values()));
        }
    }
}

package KiwiDevSoft.clinia.infrastructure.mappers;

import org.mapstruct.*;

import java.util.Arrays;
import java.util.List;

import KiwiDevSoft.clinia.util.enums.Role;
import KiwiDevSoft.clinia.api.dto.request.AccountRequest;
import KiwiDevSoft.clinia.api.dto.response.AccountResponse;
import KiwiDevSoft.clinia.domain.entities.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    AccountResponse toAccountResponse(Account account);

    List<AccountResponse> toAccountResponseList(List<Account> accounts);

    @Mapping(target = "idAccount", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "role", source = "role", qualifiedByName = "stringToRole")
    Account toAccount(AccountRequest request);

    @Named("stringToRole")
    static Role stringToRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            return Role.PATIENT;
        }
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "Invalid role: " + role + ". Available roles: " + Arrays.toString(Role.values()));
        }
    }
}

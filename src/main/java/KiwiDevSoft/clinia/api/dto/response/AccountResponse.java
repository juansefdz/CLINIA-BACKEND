package KiwiDevSoft.clinia.api.dto.response;

import java.time.LocalDateTime;

import javax.management.relation.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private String idAccount;
    private String email;
    private Role Role;
    private LocalDateTime createdAt;

}

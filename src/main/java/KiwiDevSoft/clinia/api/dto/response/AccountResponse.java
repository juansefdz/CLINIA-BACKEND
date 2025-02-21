package KiwiDevSoft.clinia.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private String accountId;
    private String accountEmail;
    private String accountRole;

}

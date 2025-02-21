package KiwiDevSoft.clinia.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private String accountId;
    private String accountEmail;
    private String accountPassword;
    private String accountRole;


}

package KiwiDevSoft.clinia.api.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequest {

    private String Email;
    private String Password;
    private String Role;

}

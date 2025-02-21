package KiwiDevSoft.clinia.infrastructure.abstract_services;

import KiwiDevSoft.clinia.api.dto.request.AccountRequest;
import KiwiDevSoft.clinia.api.dto.request.update.AccountUpdateRequest;
import KiwiDevSoft.clinia.api.dto.response.AccountResponse;
import KiwiDevSoft.clinia.infrastructure.abstract_services.generic.CreateService;
import KiwiDevSoft.clinia.infrastructure.abstract_services.generic.DeleteService;
import KiwiDevSoft.clinia.infrastructure.abstract_services.generic.GetAllService;
import KiwiDevSoft.clinia.infrastructure.abstract_services.generic.GetbyIdService;
import KiwiDevSoft.clinia.infrastructure.abstract_services.generic.UpdateService;

public interface IAccountService extends
        CreateService<AccountRequest, AccountResponse>,
        DeleteService<String>,
        GetAllService<AccountResponse>,
        GetbyIdService<AccountResponse, String>,
        UpdateService<AccountUpdateRequest, AccountResponse, String> {

}

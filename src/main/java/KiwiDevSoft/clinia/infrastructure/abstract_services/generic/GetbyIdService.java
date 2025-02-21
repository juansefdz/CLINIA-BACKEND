package KiwiDevSoft.clinia.infrastructure.abstract_services.generic;

import java.util.Optional;

public interface GetbyIdService<RESPONSE, ID> {

    Optional<RESPONSE> getById(ID id);
}

package KiwiDevSoft.clinia.infrastructure.abstract_services.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllService<RESPONSE> {
    Page<RESPONSE> getAll(Pageable pageable);
}
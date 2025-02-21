package KiwiDevSoft.clinia.infrastructure.abstract_services.generic;

public interface UpdateService<REQUEST, RESPONSE, ID> {
    RESPONSE update(ID id, REQUEST request);
}

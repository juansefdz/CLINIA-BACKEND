package KiwiDevSoft.clinia.infrastructure.abstract_services.generic;

public interface CreateService<REQUEST, RESPONSE> {

    RESPONSE create(REQUEST request);
}
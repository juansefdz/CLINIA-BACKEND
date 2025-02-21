package KiwiDevSoft.clinia.infrastructure.abstract_services.generic;

public interface DeleteService<ID> {
    void delete(ID id);
}
package KiwiDevSoft.clinia.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import KiwiDevSoft.clinia.domain.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}

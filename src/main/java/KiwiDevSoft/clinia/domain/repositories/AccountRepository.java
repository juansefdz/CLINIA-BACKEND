package KiwiDevSoft.clinia.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import KiwiDevSoft.clinia.domain.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @SuppressWarnings("null")
    @Query("SELECT a FROM Account a WHERE a.active = true")
    Page<Account> findAll(Pageable pageable);

    boolean existsByEmail(String email);

}

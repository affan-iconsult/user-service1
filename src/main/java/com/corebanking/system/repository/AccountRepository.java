package com.corebanking.system.repository;

import com.corebanking.system.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumberAndCnic(String accountNumber, String cnicNumber);
    Optional<Account> findByAccountNumber(String AccountNumber);
    List<Account> findByCnic(String cninc);

}

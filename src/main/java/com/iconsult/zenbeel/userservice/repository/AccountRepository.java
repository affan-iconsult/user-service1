package com.iconsult.zenbeel.userservice.repository;

import com.iconsult.zenbeel.userservice.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String AccountNumber);
    List<Account> findByCnic(String cninc);

}

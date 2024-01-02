package com.corebanking.system.service;

import com.corebanking.system.model.dto.AccountDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface AccountService {
    public String createAccount(AccountDto accountDto);
    public AccountDto updateAccount(AccountDto accountDto);
    public String deleteAccount(Long accountId);
    public List<AccountDto> listOfAccount (String cnicNumber);
    public AccountDto getAccountDetail(String accountNumber);
    public Boolean accountAvailable(String accountNumber, String cnic);
}

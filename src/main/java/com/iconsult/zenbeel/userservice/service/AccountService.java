package com.iconsult.zenbeel.userservice.service;

import com.iconsult.zenbeel.userservice.model.dto.AccountDto;
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

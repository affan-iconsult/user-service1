package com.iconsult.zenbeel.userservice.service;

import com.iconsult.zenbeel.userservice.model.dto.AccountDto;

import java.util.List;

public interface AccountService {
    public AccountDto createAccount(AccountDto accountDto);
    public AccountDto updateAccount(AccountDto accountDto);
    public String deleteAccount(Long accountId);
    public List<AccountDto> listOfAccount (String cnicNumber);
    public AccountDto getAccountDetail(String accountNumber);
}

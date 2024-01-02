package com.corebanking.system.mapper;

import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto jpeToDto(Account account);
    Account dtoToJpe(AccountDto accountDto);
    List<Account> accountEntityList (List<AccountDto>accountDtoList);
    List<AccountDto> accountDtoList (List<Account> accountEntityList);

}

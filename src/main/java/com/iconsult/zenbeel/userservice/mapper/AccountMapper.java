package com.iconsult.zenbeel.userservice.mapper;

import com.iconsult.zenbeel.userservice.model.dto.AccountDto;
import com.iconsult.zenbeel.userservice.model.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto jpeToDto(Account account);
    Account dtoToJpe(AccountDto accountDto);
    List<Account> accountEntityList (List<AccountDto>accountDtoList);
    List<AccountDto> accountDtoList (List<Account> accountEntityList);

}

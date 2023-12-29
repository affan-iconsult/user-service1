package com.iconsult.zenbeel.userservice.controller;

import com.iconsult.zenbeel.userservice.model.dto.AccountDto;
import com.iconsult.zenbeel.userservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/add")
    public ResponseEntity<String> addAccount(@RequestBody AccountDto accountDto){
        AccountDto newAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>("Account has been registered Account Number : "+newAccount.getAccountNumber(), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateInfo(@RequestBody AccountDto accountDto){
        AccountDto updateAccountDto = accountService.updateAccount(accountDto);
        return new ResponseEntity<>(updateAccountDto.getAccountNumber()+" has been updated",HttpStatus.OK);
    }
    @GetMapping("/get/{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountNumber")String accountNumber)
    {
        AccountDto accountDto =  accountService.getAccountDetail(accountNumber);
        return new ResponseEntity<>(accountDto, HttpStatus.FOUND);
    }
    @GetMapping("/getAll/{cnicNumber}")
    public ResponseEntity<List<AccountDto>> getAllAccounts(@PathVariable("cnicNumber")String cnicNumber)
    {
        List<AccountDto> accountDtoList = accountService.listOfAccount(cnicNumber);
        return new ResponseEntity<>(accountDtoList,HttpStatus.FOUND);
    }


}

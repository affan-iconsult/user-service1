package com.corebanking.system.service.Impl;

import com.corebanking.system.exception.ResourceNotFoundException;
import com.corebanking.system.mapper.AccountMapper;
import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.entity.Account;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.repository.AccountRepository;
import com.corebanking.system.repository.CustomerRepository;
import com.corebanking.system.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger logger  = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Override
    public String createAccount(AccountDto accountDto) {
        logger.info("create account request received");
        String accountNumber = generateAccountNumber("zanbeel-");
        Account account = accountMapper.dtoToJpe(accountDto);
        account.setAccountNumber(accountNumber);
        boolean checkCnic = isExpired(accountDto.getCnicIssuance(), accountDto.getCnicExpiry());
        if(checkCnic)
        {
            Customer customer = customerRepository.findById(accountDto.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer Id Not Found"));
            account.setCustomer(customer);
            Account saveAccount =  accountRepository.save(account);
            logger.info("account Number [{}] has been generated against Cnic :  [{}]  ",saveAccount.getAccountNumber(),saveAccount.getCnic());
            return saveAccount.getAccountNumber()+" "+"save successfully !";

        }

        return "account is not save";
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        Account account =  updateAccountInfo(accountDto);
        return accountMapper.jpeToDto(account);
    }

    @Override
    public String deleteAccount(Long accountId) {
        Account account = getAccountByAccountId(accountId);
        accountRepository.deleteById(accountId);
        return "your Account is deleted!";
    }

    @Override
    public List<AccountDto> listOfAccount(String cnicNumber) {
        List<Account> listOfAccounts  = accountRepository.findByCnic(cnicNumber);
        return accountMapper.accountDtoList(listOfAccounts);
    }

    @Override
    public AccountDto getAccountDetail(String accountNumber) {
        Account account = getAccountByAccountNumber(accountNumber);
        return accountMapper.jpeToDto(account);
    }

    @Override
    public Boolean accountAvailable(String accountNumber, String cnic) {
        Optional<Account> account = accountRepository.findByAccountNumberAndCnic(accountNumber, cnic);
        if(account.isPresent())
        {
            return true;
        }
        return false;
    }

    private Account updateAccountInfo(AccountDto accountDto)
   {

       Account accountDetail = getAccountByAccountNumber(accountDto.getAccountNumber());
       accountDetail.setCity(accountDto.getCity());
       accountDetail.setBusinessAddress(accountDto.getBusinessAddress());
       accountDetail.setLineOfBusiness(accountDto.getLineOfBusiness());
       accountDetail.setCnicIssuance(accountDto.getCnicIssuance());
       accountDetail.setCnicExpiry(accountDto.getCnicExpiry());
       accountDetail.setPurposeOfAccount(accountDto.getPurposeOfAccount());
       accountDetail.setSourceOfIncome(accountDto.getSourceOfIncome());
       accountDetail.setResidentialAddress(accountDto.getResidentialAddress());
       accountDetail.setLineOfBusiness(accountDto.getLineOfBusiness());
       accountDetail.setBusinessAddress(accountDto.getBusinessAddress());
       accountDetail.setCustomer(accountDetail.getCustomer());
       Account currentAccountUpdated = accountRepository.save(accountDetail);
       return currentAccountUpdated;
   }
   private Account getAccountByAccountId(Long accountId)
   {
       Account account = accountRepository.findById(accountId).orElseThrow(()->new ResourceNotFoundException("invalid Id"));
       return account;
   }
   private Account getAccountByAccountNumber(String accountNumber)
   {
       Account accountDetail = accountRepository.findByAccountNumber(accountNumber).orElseThrow(()-> new ResourceNotFoundException("This account is not exist"));
       return accountDetail;
   }
   private static  String generateAccountNumber(String prefix) {
        // Combine prefix with a unique identifier (e.g., UUID)
        String uniqueIdentifier = UUID.randomUUID().toString().replaceAll("-", "");
        return prefix + uniqueIdentifier.substring(0, 7); // Use a portion of the UUID as the account number
    }
    private boolean isExpired(String cnicIssueDate, String cnicExpiryDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Date format example

        // Parse string dates into LocalDate objects
        LocalDate expiryDate = LocalDate.parse(cnicExpiryDate, formatter);
        LocalDate issueDate = LocalDate.parse(cnicIssueDate, formatter);

        // Compare expiry date with the current date
       return issueDate.isBefore(expiryDate);
    }
}

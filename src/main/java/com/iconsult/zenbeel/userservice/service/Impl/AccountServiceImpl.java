package com.iconsult.zenbeel.userservice.service.Impl;

import com.iconsult.zenbeel.userservice.exception.ResourceNotFoundException;
import com.iconsult.zenbeel.userservice.mapper.AccountMapper;
import com.iconsult.zenbeel.userservice.model.dto.AccountDto;
import com.iconsult.zenbeel.userservice.model.entity.Account;
import com.iconsult.zenbeel.userservice.model.entity.Customer;
import com.iconsult.zenbeel.userservice.repository.AccountRepository;
import com.iconsult.zenbeel.userservice.repository.CustomerRepository;
import com.iconsult.zenbeel.userservice.service.AccountService;
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
    @Override
    public String createAccount(AccountDto accountDto) {
        String accountNumber = generateAccountNumber("zanbeel-");
        Account account = accountMapper.dtoToJpe(accountDto);
        account.setAccountNumber(accountNumber);
        boolean checkCnic = isExpired(accountDto.getCnicIssuance(), accountDto.getCnicExpiry());
        if(checkCnic)
        {
            Customer customer = customerRepository.findById(accountDto.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer Id Not Found"));
            account.setCustomer(customer);
           Account saveAccount =  accountRepository.save(account);
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
        boolean a = issueDate.isBefore(expiryDate);
        return a;
    }

//        private String generateAccountNumber()
//   {
//       int length = 7;
//       //public static String generateOTP(int length) {
//       String seq = "0";
//       while (seq.startsWith("0")) {
//           seq = Long.valueOf(UUID.randomUUID().getLeastSignificantBits()).toString().substring(1, length+1);
//       }
//       return seq;
//   }

}

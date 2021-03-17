package com.syrisa.onlinebank.microservice.onlinebankbff.service.impl;

import com.syrisa.onlinebank.microservice.onlinebankbff.client.*;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.DemandDepositAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.Exchange;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.ExtractOfAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.entity.impl.SavingsAccount;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.DemandDepositAccountService;
import com.syrisa.onlinebank.microservice.onlinebankbff.service.DepositAndWithdrawMoneyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DemandDepositAccountServiceImpl implements DemandDepositAccountService
        , DepositAndWithdrawMoneyService<DemandDepositAccount, ExtractOfAccount> {
    private final DemandDepositAccountServiceClient demandDepositAccountServiceClient;
    private final SavingsAccountServiceClient savingsAccountServiceClient;
    private final AccountProcessClient accountProcessClient;
    private final TransferServiceClient transferServiceClient;
    private final TransferClient transferClient;

    public DemandDepositAccountServiceImpl(DemandDepositAccountServiceClient demandDepositAccountServiceClient, SavingsAccountServiceClient savingsAccountServiceClient, AccountProcessClient accountProcessClient, TransferServiceClient transferServiceClient, TransferClient transferClient) {
        this.demandDepositAccountServiceClient = demandDepositAccountServiceClient;
        this.savingsAccountServiceClient = savingsAccountServiceClient;
        this.accountProcessClient = accountProcessClient;
        this.transferServiceClient = transferServiceClient;
        this.transferClient = transferClient;
    }

    @Override
    public DemandDepositAccount getAccountByIban(String accountIban) {
        try {
            return demandDepositAccountServiceClient.getDemandDepositAccountByAccountIban(accountIban).toDemandDepositAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found.");
        }

    }

    @Override
    public DemandDepositAccount depositMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            return accountProcessClient.depositMoneyDemand(extractOfAccount.toExtractOfAccountDto()).toDemandDepositAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public DemandDepositAccount withDrawMoneyAccount(ExtractOfAccount extractOfAccount) {
        try {
            return accountProcessClient.withDrawMoneyDemand(extractOfAccount.toExtractOfAccountDto()).toDemandDepositAccount();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public DemandDepositAccount transferMoneyBetweenAccounts(Exchange exchange) {
      try{
          DemandDepositAccount from = demandDepositAccountServiceClient.getDemandDepositAccountByAccountIban(exchange.getFromAccountIban()).toDemandDepositAccount();
          DemandDepositAccount to = demandDepositAccountServiceClient.getDemandDepositAccountByAccountIban(exchange.getToAccountIban()).toDemandDepositAccount();
          double money = transferServiceClient.getConvertMoney(from.getAccountCurrency(), to.getAccountCurrency(), exchange.getDepositMoney());
          exchange.setReceiveMoney((int) money);
          return transferClient.moneyTransferBetweenAccounts(exchange.toExchangeDto()).toDemandDepositAccount();
      }catch (Exception exception){
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
      }
    }

    @Override
    public DemandDepositAccount transferMoneyDifferentAccounts(Exchange exchange) {
        try{
            DemandDepositAccount from = demandDepositAccountServiceClient.getDemandDepositAccountByAccountIban(exchange.getFromAccountIban()).toDemandDepositAccount() ;
            SavingsAccount to =savingsAccountServiceClient.getSavingsAccountByAccountIban(exchange.getToAccountIban()).toSavingsAccount();
            double money = transferServiceClient.getConvertMoney(from.getAccountCurrency(),to.getAccountCurrency(),exchange.getDepositMoney());
            exchange.setReceiveMoney((int) money);
            return transferClient.moneyTransferBetweenDifferentAccounts(exchange.toExchangeDto()).toDemandDepositAccount();
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }

    }
}

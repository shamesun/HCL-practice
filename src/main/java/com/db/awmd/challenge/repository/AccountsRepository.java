package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.FundTransfer;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface AccountsRepository {


  void createAccount(Account account) throws DuplicateAccountIdException;

  Account getAccount(String accountId);

  void clearAccounts();

  boolean transferFund(FundTransfer fundTransfer);

}

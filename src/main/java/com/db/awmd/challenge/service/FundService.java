package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.FundTransfer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundService {
    @Getter
    private final AccountsService accountService;

    @Getter
    private final NotificationService notificationService;

    @Autowired
    public FundService(AccountsService accountService, NotificationService notificationService) {
        this.accountService = accountService;
        this.notificationService = notificationService;
    }

    public boolean fundTransfer(FundTransfer fundTransfer) {
        boolean transferStatus =  this.accountService.getAccountsRepository().transferFund(fundTransfer);
        if(transferStatus) {
            this.notificationService.notifyAboutTransfer(this.accountService.getAccount(
                    fundTransfer.getSenderAccountId()),
                    " Account debitted with " + fundTransfer.getFund());
            this.notificationService.notifyAboutTransfer(this.accountService.getAccount(
                    fundTransfer.getReceiverAccountId()),
                    " Account Creditted with " + fundTransfer.getFund());
        }
        return transferStatus;
    }
}

package com.crimsonlogic.mutualfundinvestmentspringmvc.model.transaction;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;

public class SIPTransaction extends Transaction {

    @Override
    public void executeTransaction() {

        System.out.println(
                "SIP Transaction Executed Successfully");
    }



}
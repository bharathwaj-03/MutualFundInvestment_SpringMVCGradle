package com.crimsonlogic.mutualfundinvestmentspringmvc.model.transaction;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;

public class BuyTransaction extends Transaction {

    @Override
    public void executeTransaction() {

        System.out.println(
                "Buy Transaction Executed Successfully");
    }


}
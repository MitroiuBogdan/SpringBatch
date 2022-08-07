package com.ing.casyadapterpoc.saltedge.rest;

import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.domain.casy_entity.Merchant;
import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.common.utils.ObjectToExcelWriter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ObjectToExcelWriterTest {

    ObjectToExcelWriter excelFile;
    final String path = "src/test/resources/";

    @BeforeEach
    void before() {
        excelFile = new ObjectToExcelWriter();
    }


    @Test
    @SneakyThrows
    void test_objArrayToExcel_expect_ok() throws IOException {


        Account account = mockAccount();
        Transaction trx = mockTransaction();
//        List<Account> accountList = Collections.nCopies(20, account);
//        excelFile.writeObjectsToExcelFile(accountList), path + "accounts-saltedge.xls");
//        excelFile.writeObjectToExcelFile(accountList.get(0), path + "accounts-saltedge.xls");

        ObjectToExcelWriter.writeObjectToExcelFile(trx, path + "test-trx.xls");
    }

    public static Transaction mockTransaction() {
        return Transaction.builder()
                .providerTransactionId("transactionId")
                .creditorName("Jenny")
                .debtorName("Lisa")
                .creditor(Merchant.builder()
                        .iban("NL1235ILPIZZA")
                        .build())
                .debtor(Merchant.builder()
                        .iban("NL1235ILCOOKIES")
                        .build())
                .endToEndId("end_to_end_id")
                .build();
    }

    public static Account mockAccount() {
        return Account.builder()
                .name("accountName")
//                .currency("$$$->USD")
//                .transaction(Transaction
//                        .builder()
//                        .bookingDate("2001")
//                        .endToEndId("endToEndId")
//                        .status("ACCEPTED")
//                        .amount("10000000000$")
//                        .build())
//                .balances(List.of("CURRENT", "CREDIT"))
//                .aspspAccountId("bankId")
//                .clientId("clientId")
                .build();
    }
}

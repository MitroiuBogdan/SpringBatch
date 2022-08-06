package com.ing.casyadapterpoc.saltedge.rest;

import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.common.file.WriteExcelFile;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WriteExcelFileTest {

    WriteExcelFile excelFile;

    @BeforeEach
    void before() {
        excelFile = new WriteExcelFile();
    }

    @Test
    void test_writingFiles() throws IOException {
//        String path = "src/test/resources/emptyFile.xls";
//        excelFile.processFile(path);
//        excelFile.addHeadersFromObject(Account.builder().build());
//        excelFile.processFileSecondVersion();

//        excelFile.processFileSecondVersion();
    }

    @Test
    @SneakyThrows
    void test_jsonToExcel_expect_ok() throws IOException {
        Account account = mockAccount();

//        excelFile.processFileSecondVersion(account);
        excelFile.processLive(account);

    }


    public static Account mockAccount() {
        return Account.builder()
                .name("accountName")
                .currency("$USDE")
                .transaction(Transaction
                        .builder()
                        .bookingDate("dsadsa")
                        .endToEndId("endToEndId")
                        .status("ACCEPTED")
                        .amount("10000000000$")
                        .build())
                .aspspAccountId("bankId")
                .clientId("clientId")
                .build();
    }
}

package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.common.properties.CommonProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ing.casyadapterpoc.common.utils.ObjectToExcelWriter.writeObjectToExcelFile;

@Component
@AllArgsConstructor
public class ExcelWriterDelegatingService {

    CommonProperties commonProperties;

    public void writeAccountForVendor(Account account, Vendor vendor) {
        if (vendor == Vendor.SALTEDGE) {
            writeObjectToExcelFile(account, commonProperties.getSaltedgeAccountsPath());
        }
        if (vendor == Vendor.TINK) {
            writeObjectToExcelFile(account, commonProperties.getTinkAccountPath());
        }
    }

    public void writeTransactionForVendor(Transaction transaction, Vendor vendor) {
        if (vendor == Vendor.SALTEDGE) {
            writeObjectToExcelFile(transaction, commonProperties.getSaltedgeTrxPath());
        }
        if (vendor == Vendor.TINK) {
            writeObjectToExcelFile(transaction, commonProperties.getTinkTransactionPath());
        }
    }
}

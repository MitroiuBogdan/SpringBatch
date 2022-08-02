package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.ais.SaltedgeTransaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaltedgeTransactionMapper implements Function<SaltedgeTransaction, Transaction> {

    public static final SaltedgeTransactionMapper SALTEDGE_TX_MAPPER = new SaltedgeTransactionMapper();

    @Override
    public Transaction apply(SaltedgeTransaction saltedgeTransaction) {
        return Transaction.builder()
                .bookingDate(saltedgeTransaction.getMade_on())
                .amount(saltedgeTransaction.getAmount().toString())
                .debtorName(saltedgeTransaction.getExtra().getPayee())
                .endToEndId(saltedgeTransaction.getExtra().getEnd_to_end_id())
                .status(saltedgeTransaction.getStatus())
                .providerAccountId(saltedgeTransaction.getId())
                .externalId(saltedgeTransaction.getAccount_id())
                .transactionType(saltedgeTransaction.getCategory())
                .valueDate(saltedgeTransaction.getExtra().getPosting_date())
                .remittanceInformationUnstructured(saltedgeTransaction.getDescription())
                .build();
    }
}

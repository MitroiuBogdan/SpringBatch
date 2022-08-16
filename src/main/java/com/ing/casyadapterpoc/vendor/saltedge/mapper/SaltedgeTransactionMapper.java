package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.common.domain.casy_entity.Amount;
import com.ing.casyadapterpoc.common.domain.casy_entity.ExchangeRate;
import com.ing.casyadapterpoc.common.domain.casy_entity.Merchant;
import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaltedgeTransactionMapper implements Function<SaltedgeTransaction, Transaction> {

    public static final SaltedgeTransactionMapper SALTEDGE_TX_MAPPER = new SaltedgeTransactionMapper();

    @Override
    public Transaction apply(SaltedgeTransaction saltedgeTransaction) {
        return Transaction.builder()
                .providerTransactionId(saltedgeTransaction.getId())
                .transactionType(saltedgeTransaction.getStatus())
                .transactionDate(saltedgeTransaction.getMade_on())
                .amount(Amount.builder()
                        .content(saltedgeTransaction.getAmount())
                        .currency(saltedgeTransaction.getCurrency_code())
                        .build())
                .bankTransactionCode(saltedgeTransaction.getExtra().getConstant_code())
                .valueDate(saltedgeTransaction.getExtra().getTime())
                .bookingDate(saltedgeTransaction.getExtra().getPosting_date())
                .remittanceInformationUnstructured(saltedgeTransaction.getDescription())
                .providerAccountId(saltedgeTransaction.getAccount_id())
                .endToEndId(saltedgeTransaction.getExtra().getEnd_to_end_id())
                .externalId(saltedgeTransaction.getExtra().getId())
                .creditorName(saltedgeTransaction.getExtra().getPayee_information())
                .debtorName(saltedgeTransaction.getExtra().getPayer_information())
                .category(saltedgeTransaction.getCategory())
                .merchantId(saltedgeTransaction.getExtra().getMerchant_id())
                .creditor(Merchant.builder()
                        .iban(saltedgeTransaction.getExtra().getPayee())
                        .build())
                .debtor(Merchant.builder()
                        .iban(saltedgeTransaction.getExtra().getPayer())
                        .build())
                .exchangeRate(ExchangeRate.builder()
                        .rate(saltedgeTransaction.getExtra().getExchange_rate())
                        .build())
                .build();
    }
}

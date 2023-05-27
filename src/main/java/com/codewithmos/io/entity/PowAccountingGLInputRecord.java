package com.codewithmos.io.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PowAccountingGLInputRecord {
    private String recordType;
    private long recordSequence;
    private String bankCode;
    private String operationCode;
    private int operationSequence;
    private String settlementAccountCutoffId;
    private long entrySequence;
    private String glReference;
    private String refOfDocument;
    private String entryLabel;
    private int postingDate;
    private int entryDate;
    private String entryAccountNumber;
    private String entryKeyAccount;
    private long entryAmount;
    private String entrySign;
    private String entryCurrency;
    private String postingFlag;
    private int businessDate;
    private String dataFileSource;
    private String issuerBankCode;
    private String acquirerBankCode;
    private String networkCode;
    private String acquirerReferenceNumber;
    private int acquirerReferenceSequence;
    private String destinationAccountLabel;
    private String operationPowLabel;
    private String basicSourceLabel;
    private String operationGroupingCode;
    private long conversionRate;

    private String compSourceIdTagName;
    private int compSourceIdTagLength;
    private String compSourceIdTagValue;
    private String costCenterSourceIdTagName;
    private int costCenterSourceIdTagLength;
    private String costCenterSourceIdTagValue;
    private String applSourceIdTagName;
    private int applSourceIdTagLength;
    private String applSourceIdTagValue;

    private long sourceAmount;
    private String sourceCurrency;
}

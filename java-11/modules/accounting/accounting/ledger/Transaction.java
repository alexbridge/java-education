package accounting.ledger;

import accounting.account.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Transaction {
    private LocalDateTime dateTime;
    private String code;
    private String note;
    private MinorMoney money;
    private ArrayList<TransactionLine> lines;

    private Transaction() {}

    public static Transaction fromTransactionOffer(String code, TransactionOffer transactionOffer) {
        Transaction transaction = new Transaction();
        transaction.dateTime = transactionOffer.getDateTime();
        transaction.code = code;
        transaction.note = transactionOffer.getNote();
        transaction.money = transactionOffer.getMoney();
        transaction.lines = transactionOffer
                .getLines()
                .stream()
                .map(line -> transaction.new TransactionLine(
                            line.getType(),
                            line.getAccount(),
                            line.getMoney(),
                            line.getNote()
                    ))
                .collect(Collectors.toCollection(ArrayList::new));

        return transaction;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }

    public MinorMoney getMoney() {
        return money;
    }

    public ArrayList<TransactionLine> getLines() {
        return lines;
    }

    public class TransactionLine {
        private LedgerType ledgerType;
        private Account account;
        private MinorMoney money;
        private String note;

        private TransactionLine(LedgerType ledgerType, Account account, MinorMoney money, String note) {
            this.ledgerType = ledgerType;
            this.account = account;
            this.note = note;
        }

        public LedgerType getLedgerType() {
            return ledgerType;
        }

        public String getNote() {
            return note;
        }

        public Account getAccount() {
            return account;
        }
    }
}

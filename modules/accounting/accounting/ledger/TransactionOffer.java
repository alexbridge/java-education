package accounting.ledger;

import accounting.account.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionOffer {
    private LocalDateTime dateTime;
    private String note;
    private MinorMoney money;
    private ArrayList<TransactionOffer.TransactionOfferLine> lines;

    public TransactionOffer(LocalDateTime dateTime, MinorMoney money, String note) {
        this.dateTime = dateTime;
        this.money = money;
        this.note = note;
        this.lines = new ArrayList<>();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getNote() {
        return note;
    }

    public MinorMoney getMoney() {
        return money;
    }

    public void addLine(LedgerType ledgerType, Account account, MinorMoney money, String note) {
        lines.add(new TransactionOfferLine(
                ledgerType,
                account,
                money,
                note
        ));
    }

    public List<TransactionOffer.TransactionOfferLine> getLines() {
        return lines;
    }

    public class TransactionOfferLine {
        private LedgerType ledgerType;
        private Account account;
        private MinorMoney money;
        private String note;

        public TransactionOfferLine(LedgerType ledgerType, Account account, MinorMoney money, String note) {
            this.ledgerType = ledgerType;
            this.account = account;
            this.money = money;
            this.note = note;
        }

        public LedgerType getLedgerType() {
            return ledgerType;
        }

        public LedgerType getType() {
            return ledgerType;
        }

        public MinorMoney getMoney() {
            return money;
        }

        public String getNote() {
            return note;
        }

        public Account getAccount() {
            return account;
        }
    }
}

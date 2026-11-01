package accounting;

import accounting.account.AccountSubType;
import accounting.account.AccountType;
import accounting.ledger.*;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class App {
    private static TransactionService transactionService;

    static {
        transactionService = new TransactionService();
    }

    public static void main(String[] args) {

        MinorMoney money = new MinorMoney(100.00);

        // 1. Create transaction offer
        TransactionOffer transactionOffer = new TransactionOffer(
                LocalDateTime.now(),
                money,
                "Buy office assets"
        );

        transactionOffer.addLine(
                LedgerType.CREDIT,
                LedgerBook.getAccount(AccountType.PAYABLE, AccountSubType.BANK),
                money,
                "Spent bank account money"
        );
        transactionOffer.addLine(
                LedgerType.DEBIT,
                LedgerBook.getAccount(AccountType.RECEIVABLE, AccountSubType.ASSET),
                money,
                "Buy new office furniture"
        );

        transactionService.offerTransaction(transactionOffer);

        /**
         * Print in nice cli format
         * -------------------------------------------------------------------------------------
         * Date         Note                 Account              Debit                Credit
         * -------------------------------------------------------------------------------------
         * 11/11/20     Buy office assets   Bank account                              $100.00
         *                                  Office assets        $100.00
         * .....................................................................................
         */

        System.out.println("-".repeat(85));
        System.out.printf("%-12s %-20s %-20s %-20s %-20s\n", "Date", "Note", "Account", "Debit", "Credit");
        System.out.println("-".repeat(85));

        final NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

        transactionService.getTransactions().forEach(transaction -> {
            System.out.printf(
                    "%-12s %-20s",
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(transaction.getDateTime()),
                    transaction.getNote()
            );

            boolean first = true;
            for (Transaction.TransactionLine line : transaction.getLines()) {
                if (!first) {
                    System.out.printf("%33s", " ");
                } else {
                    first = false;
                }

                System.out.printf(
                        "%-20s %-20s %-20s\n",
                        line.getAccount().getName(),
                        line.getLedgerType() == LedgerType.DEBIT
                                ? currency.format(transaction.getMoney().getAmount())
                                : "",
                        line.getLedgerType() == LedgerType.CREDIT
                                ? currency.format(transaction.getMoney().getAmount())
                                : ""
                );
            }
            System.out.println(".".repeat(85));
        });
    }
}

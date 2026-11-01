package accounting;

import accounting.ledger.Transaction;
import accounting.ledger.TransactionOffer;

import java.util.ArrayList;
import java.util.UUID;

public class TransactionService {
    private ArrayList<Transaction> transactions;
    {
        transactions = new ArrayList<>();
    }

    public Transaction offerTransaction(TransactionOffer transactionOffer) {
        String code = UUID.randomUUID().toString();

        Transaction transaction = Transaction.fromTransactionOffer(code, transactionOffer);

        transactions.add(transaction);

        return transaction;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}

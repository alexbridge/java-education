package accounting.ledger;

import accounting.account.Account;
import accounting.account.AccountSubType;
import accounting.account.AccountType;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class LedgerBook {
    private static ArrayList<Account> accounts;

    static {
        // Get ledger book from config or external source
        accounts = new ArrayList<>() {{
            add(new Account(
                    UUID.randomUUID().toString(),
                    AccountType.PAYABLE,
                    AccountSubType.DEFAULT,
                    "Default payable account"
            ));
            add(new Account(
                    UUID.randomUUID().toString(),
                    AccountType.RECEIVABLE,
                    AccountSubType.DEFAULT,
                    "Default receivable account"
            ));
            add(new Account(
                    UUID.randomUUID().toString(),
                    AccountType.PAYABLE,
                    AccountSubType.BANK,
                    "Bank account"
            ));
            add(new Account(
                    UUID.randomUUID().toString(),
                    AccountType.RECEIVABLE,
                    AccountSubType.ASSET,
                    "Office assets"
            ));
        }};
    }

    private LedgerBook() {}

    public static Account getAccount(AccountType type, AccountSubType subType) {
        Optional<Account> foundAccount = accounts.stream()
                .filter(account -> account.getAccountType() == type)
                .filter(account -> account.getAccountSubType() == subType)
                .findFirst();

        return foundAccount.orElseGet(() -> accounts.stream()
                .filter(account -> account.getAccountType() == type)
                .filter(account -> account.getAccountSubType() == AccountSubType.DEFAULT)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The account with given type id not present in Ledger book")));
    }
}

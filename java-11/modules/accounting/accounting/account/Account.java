package accounting.account;

public class Account {
    private String code;
    private String name;
    private AccountType accountType;
    private AccountSubType accountSubType;

    public Account(String code, AccountType accountType, AccountSubType accountSubType, String name) {
        this.code = code;
        this.accountType = accountType;
        this.accountSubType = accountSubType;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public AccountSubType getAccountSubType() {
        return accountSubType;
    }
}

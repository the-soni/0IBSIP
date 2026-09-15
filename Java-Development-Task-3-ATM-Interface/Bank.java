import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();

        accounts.put("1001",
                new Account("1001", "soni", "1234", 10000));

        accounts.put("1002",
                new Account("1002", "rahul", "5678", 15000));
    }

    public Account authenticate(String userId, String pin) {
        for (Account account : accounts.values()) {
            if (account.getUserId().equals(userId)
                    && account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
}
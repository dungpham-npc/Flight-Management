
package utils;

import java.util.HashMap;


public class AccountDaoImpl extends HashMap<String, Account> implements AccountDao{
    @Override
    public void addAccount(Account account){
        this.put(account.getUsername(), account);
    }
}

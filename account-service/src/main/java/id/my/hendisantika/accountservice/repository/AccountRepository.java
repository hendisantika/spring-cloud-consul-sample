package id.my.hendisantika.accountservice.repository;

import id.my.hendisantika.accountservice.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/01/25
 * Time: 10.46
 * To change this template use File | Settings | File Templates.
 */
public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public Account add(Account account) {
        account.setId((long) (accounts.size() + 1));
        accounts.add(account);
        return account;
    }

    public Account update(Account account) {
        accounts.set(account.getId().intValue() - 1, account);
        return account;
    }

    public Account findById(Long id) {
        return accounts.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public void delete(Long id) {
        accounts.remove(id.intValue());
    }

    public List<Account> find(List<Long> ids) {
        return accounts.stream().filter(a -> ids.contains(a.getId())).toList();
    }
}

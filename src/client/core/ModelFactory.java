package client.core;


import client.model.account.Account;
import client.model.account.AccountManager;
import client.model.login.Login;
import client.model.login.LoginManager;

public class ModelFactory {
    private final ClientFactory cf;
    private Login login;
    private Account account;

    public ModelFactory(ClientFactory cf) {
        this.cf = cf;
    }

    public Login getLoginM() {
        if (login == null)
            login = new LoginManager(cf.getClient());
        return login;
    }

    public Account getAccountM() {
        if (account == null)
            account = new AccountManager(cf.getClient());
        return account;
    }
}

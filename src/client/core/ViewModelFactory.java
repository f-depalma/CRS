package client.core;

import client.views.account.AccountVM;
import client.views.login.LoginVM;

public class ViewModelFactory {
    private final ModelFactory mf;
    private LoginVM loginViewModel;
    private AccountVM accountViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }

    public LoginVM getLoginVM() {
        if (loginViewModel == null)
            loginViewModel = new LoginVM(mf.getLoginM());
        return loginViewModel;
    }

    public AccountVM getAccountVM() {
        return (accountViewModel = accountViewModel == null ?
                new AccountVM(mf.getAccountM()) :
                accountViewModel);
    }
}

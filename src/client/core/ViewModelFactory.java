package client.core;

import client.model.favoritecourses.FavoriteCourses;
import client.views.account.AccountVM;
import client.views.courses.CoursesVM;
import client.views.favoritecourses.FavoriteCoursesVM;
import client.views.login.LoginVM;

public class ViewModelFactory {
    private final ModelFactory mf;
    private LoginVM loginViewModel;
    private AccountVM accountViewModel;
    private FavoriteCoursesVM favoriteCoursesViewModel;
    private CoursesVM coursesViewModel;

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

    public FavoriteCoursesVM getFavoriteCoursesVM() {
        return (favoriteCoursesViewModel = favoriteCoursesViewModel == null ?
                new FavoriteCoursesVM(mf.getFavoriteCoursesM()) :
                favoriteCoursesViewModel);
    }

    public CoursesVM getCoursesVM() {
        return (coursesViewModel = coursesViewModel == null ?
                new CoursesVM(mf.getCoursesM()) :
                coursesViewModel);
    }
}

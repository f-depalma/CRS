package client.core;


import client.model.account.Account;
import client.model.account.AccountManager;
import client.model.courses.Courses;
import client.model.courses.CoursesManager;
import client.model.favoritecourses.FavoriteCourses;
import client.model.favoritecourses.FavoriteCoursesManager;
import client.model.login.Login;
import client.model.login.LoginManager;
import server.database.entity.Course;

public class ModelFactory {
    private final ClientFactory cf;
    private Login login;
    private Account account;
    private FavoriteCourses favoriteCourses;
    private Courses courses;

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

    public FavoriteCourses getFavoriteCoursesM() {
        if (favoriteCourses == null) {
            favoriteCourses = new FavoriteCoursesManager(cf.getClient());
        }
        return favoriteCourses;
    }

    public Courses getCoursesM() {
        if (courses == null) {
            courses = new CoursesManager(cf.getClient());
        }
        return courses;
    }
}

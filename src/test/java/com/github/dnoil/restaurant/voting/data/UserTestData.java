package com.github.dnoil.restaurant.voting.data;

import com.github.dnoil.restaurant.voting.model.Role;
import com.github.dnoil.restaurant.voting.model.User;
import com.github.dnoil.restaurant.voting.MatcherFactory;
import com.github.dnoil.restaurant.voting.to.UserTo;
import com.github.dnoil.restaurant.voting.web.JsonUtil;

import java.util.Collections;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingEqualsComparator(User.class);

    public static final int ADMIN_ID = 1;
    public static final int USER_ID = 2;
    public static final int ANOTHER_USER_ID = 3;

    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String USER1_MAIL = "user@gmail.com";
    public static final String USER2_MAIL = "another_user@gmail.com";

    public static final User admin = new User(ADMIN_ID, "Some Admin", ADMIN_MAIL, "admin321",
            "54321", Role.ADMIN, Role.USER);
    public static final User user1 = new User(USER_ID, "Some User", USER1_MAIL, "user123",
            "12345", Role.USER);
    public static final User user2 = new User(ANOTHER_USER_ID, "Some Another User", USER2_MAIL, "another_user123",
            "123123", Role.USER);
    public static final UserTo userTo = new UserTo(null, "newName", "user@yandex.ru", "newLogin", "newPassword");

    public static User getNew() {
        return new User(null, "New User", "new@gmail.com", "new_user", "123456", Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(user1);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setLogin("updated_login");
        updated.setPassword("newPass123");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String password) {
        return JsonUtil.writeAdditionProps(user, "password", password);
    }
}

package in.mallikarjun.expenseTrackerAPI.service;

import in.mallikarjun.expenseTrackerAPI.entity.User;
import in.mallikarjun.expenseTrackerAPI.entity.UserModel;

public interface UserService {

    User createUser(UserModel userModel);

    User readUser();

    User updateUser(UserModel userModel);

    void deleteUser();

    User getLoggedInUser();

}


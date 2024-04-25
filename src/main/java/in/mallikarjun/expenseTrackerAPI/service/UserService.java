package in.mallikarjun.expenseTrackerAPI.service;

import in.mallikarjun.expenseTrackerAPI.entity.User;
import in.mallikarjun.expenseTrackerAPI.entity.UserModel;
import org.springframework.stereotype.Service;

public interface UserService {
    User createUser(UserModel userModel);

    User readUser(Long id);

    User updateUser(UserModel userModel, Long id);

    void deleteUser(Long id);

}


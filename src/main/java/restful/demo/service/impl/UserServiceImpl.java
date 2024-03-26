package restful.demo.service.impl;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restful.demo.model.request.UserRequest;
import restful.demo.model.response.UserResponse;
import restful.demo.service.UserService;
import restful.demo.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {

    }
    @Autowired
    public UserServiceImpl(Utils util) {
        util = this.util;
    }

    Map<String, UserResponse> users = null;
    Utils util;

    @Override
    public UserResponse createUser(UserRequest user) {

        UserResponse response = new UserResponse(user.getFirstName(), user.getLastname(), user.getEmail());

        if (users == null)
            users = new HashMap<String, UserResponse>();
        String userId = util.generateUserId();
        response.setUserid(userId);
        users.put(userId, response);

        return response;
    }

    @Override
    public void deleteUser(String userId) {
        if (users != null) {
            if (users.get(userId) != null) {
                users.remove(userId);
            }
        }
    }

    @Override
    public UserResponse updateUser(String userId, UserRequest toUpdate) {

        UserResponse existingUser = null, update = null;

        if (users != null) {
            existingUser = users.get(userId);
        }
        if (existingUser != null) {
            update = new UserResponse();

            update.setUserid(userId);

            if (toUpdate.getFirstName() == null)
                update.setFirstName(existingUser.getFirstName());
            else
                update.setFirstName(toUpdate.getFirstName());

            if (toUpdate.getLastname() == null)
                update.setLastname(existingUser.getLastname());
            else
                update.setLastname(toUpdate.getLastname());

            if (toUpdate.getEmail() == null)
                update.setEmail(existingUser.getEmail());
            else
                update.setEmail(toUpdate.getEmail());
        }
        users.put(userId, update);

        return update;
    }

    @Override
    public UserResponse getUser(String userid) {
        UserResponse response = null;
        if (users != null) {
            response = users.get(userid);
        }

        return response;

    }

    @Override
    public Map<String, UserResponse> getUsers(int page, int limit) {
        return users;
    }

}

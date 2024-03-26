package restful.demo.service;

import java.util.Map;

import restful.demo.model.request.UserRequest;
import restful.demo.model.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest user);
    void deleteUser( String userId );
    UserResponse updateUser( String userId, UserRequest toUpdate);
    UserResponse getUser( String userid );
    Map<String,UserResponse> getUsers(int page,int limit);
}

package lab.anubis.saasmultitenantapp.services;

import lab.anubis.saasmultitenantapp.common.PageResponse;
import lab.anubis.saasmultitenantapp.requests.UserRequest;
import lab.anubis.saasmultitenantapp.responses.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(final UserRequest request);

    void updateUser(final String userId, final UserRequest request);

    void deleteUser(final String userId);

    UserResponse getUserById(final String userId);

    PageResponse<UserResponse> getAllUsers(final int page, final int size);

    void enableUser(final String userId);

    void disableUser(final String userId);

}

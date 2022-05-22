package vn.mvpthinh.services;

import vn.mvpthinh.model.User;

import java.util.List;

public interface IUserService {
    User getCurrentUser();

    List<User> findAll();

    User adminLogin(String username, String password);

    void add(User newUser);

    void update(User newUser);

    boolean existById(Long id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByUsername(String userName);


    User findById(Long id);




}

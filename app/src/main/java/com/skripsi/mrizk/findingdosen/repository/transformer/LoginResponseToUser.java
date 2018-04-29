package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.repository.entity.local.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginResponse;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

import javax.inject.Inject;

/**
 * Created by mrizk on 18/01/2018.
 */

public class LoginResponseToUser extends BaseLayerDataTransformer<LoginResponse, User> {

    @Inject
    public LoginResponseToUser() {
    }

    @Override
    public User transform(LoginResponse from) {
        User user = new User();
        user.setId(from.getLoggedInUser().getId());
        user.setEmail(from.getLoggedInUser().getEmail());
        user.setPassword(from.getLoggedInUser().getPassword());
        user.setNama(from.getLoggedInUser().getNama());
        user.setJenisIdentitas(from.getLoggedInUser().getJenisIdentitas());
        user.setNoIdentitas(from.getLoggedInUser().getNoIdentitas());
        user.setNoTelpon(from.getLoggedInUser().getNoTelpon());
        user.setRole(from.getLoggedInUser().getRole());
        user.setToken(from.getToken());
        return user;
    }
}

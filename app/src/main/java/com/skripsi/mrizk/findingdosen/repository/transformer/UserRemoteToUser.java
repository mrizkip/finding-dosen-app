package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.repository.entity.local.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.UserRemote;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

import javax.inject.Inject;

/**
 * Created by mrizk on 16/03/2018.
 */

public class UserRemoteToUser extends BaseLayerDataTransformer<UserRemote, User> {

    @Inject
    public UserRemoteToUser() {
    }

    @Override
    public User transform(UserRemote from) {
        User user = new User();
        user.setId(from.getId());
        user.setEmail(from.getEmail());
        user.setPassword(from.getPassword());
        user.setNama(from.getNama());
        user.setJenisIdentitas(from.getJenisIdentitas());
        user.setNoIdentitas(from.getNoIdentitas());
        user.setNoTelpon(from.getNoTelpon());
        return user;
    }
}

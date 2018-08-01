package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.repository.entity.local.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.ProfileDosenResponse;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

import javax.inject.Inject;

/**
 * Created by mrizk on 30/01/2018.
 */

public class ProfileDosenResponseToUser extends BaseLayerDataTransformer<ProfileDosenResponse, User> {

    @Inject
    public ProfileDosenResponseToUser() {
    }

    @Override
    public User transform(ProfileDosenResponse from) {
        User user = new User();
        user.setId(from.getData().getId());
        user.setEmail(from.getData().getEmail());
        user.setPassword(from.getData().getPassword());
        user.setNama(from.getData().getNama());
        user.setJenisIdentitas(from.getData().getJenisIdentitas());
        user.setNoIdentitas(from.getData().getNoIdentitas());
        user.setRole(from.getData().getRole());
        return user;
    }
}

package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.repository.entity.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.FetchDosenResponse;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mrizk on 16/03/2018.
 */

public class FetchDosenResponseToUser extends BaseLayerDataTransformer<FetchDosenResponse, List<User>> {

    private final UserRemoteToUser userRemoteToUser;

    @Inject
    public FetchDosenResponseToUser(UserRemoteToUser userRemoteToUser) {
        this.userRemoteToUser = userRemoteToUser;
    }

    @Override
    public List<User> transform(FetchDosenResponse from) {
        List<User> userList = new ArrayList<>();
        userList = userRemoteToUser.transform(from.getData());
        return userList;
    }
}

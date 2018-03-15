package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.repository.entity.Register;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RegisterResponse;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

import javax.inject.Inject;

/**
 * Created by mrizk on 15/03/2018.
 */

public class RegisterResponseToRegister extends BaseLayerDataTransformer<RegisterResponse, Register> {

    @Inject
    public RegisterResponseToRegister() {
    }

    @Override
    public Register transform(RegisterResponse from) {
        Register register = new Register();
        register.setMessages(from.getMessage());
        return register;
    }
}

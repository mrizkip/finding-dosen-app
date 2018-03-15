package com.skripsi.mrizk.findingdosen.main.register;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.skripsi.mrizk.findingdosen.repository.UserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.Register;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RegisterRequest;

import javax.inject.Inject;

/**
 * Created by mrizk on 15/03/2018.
 */

public class RegisterViewModel extends ViewModel {
    private static final String TAG = "RegisterViewModel";

    private final UserRepository userRepository;
    private MutableLiveData<Register> register;
    private MutableLiveData<Boolean> registerStatus;

    public RegisterViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        register = new MutableLiveData<>();
        registerStatus = new MutableLiveData<>();
    }

    public LiveData<Boolean> registerUser(String email, String password, String nama, String jenisIdentitas, String noIdentitas, String noTelpon) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);
        registerRequest.setNama(nama);
        registerRequest.setJenis_identitas(jenisIdentitas);
        registerRequest.setNo_identitas(noIdentitas);
        registerRequest.setNo_telpon(noTelpon);

        userRepository.registerUser(registerRequest)
                .subscribe(register1 -> {
                    this.registerStatus.postValue(true);
                    this.register.postValue(register1);
                }, throwable -> this.registerStatus.postValue(false));

        return registerStatus;
    }

    public static class RegisterViewModelFactory implements ViewModelProvider.Factory {
        private final UserRepository userRepository;

        @Inject
        public RegisterViewModelFactory(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new RegisterViewModel(userRepository);
        }
    }
}

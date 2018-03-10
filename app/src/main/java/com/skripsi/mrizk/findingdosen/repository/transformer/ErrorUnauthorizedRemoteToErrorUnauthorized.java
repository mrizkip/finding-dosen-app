package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.repository.entity.ErrorUnauthorized;
import com.skripsi.mrizk.findingdosen.repository.entity.api.ErrorUnauthorizedRemote;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

/**
 * Created by mrizk on 08/03/2018.
 */

public class ErrorUnauthorizedRemoteToErrorUnauthorized extends BaseLayerDataTransformer<ErrorUnauthorizedRemote, ErrorUnauthorized> {
    @Override
    public ErrorUnauthorized transform(ErrorUnauthorizedRemote from) {
        ErrorUnauthorized error = new ErrorUnauthorized();
        error.setMessage(from.getMessage());
        error.setStatus(from.getStatus());
        return error;
    }
}

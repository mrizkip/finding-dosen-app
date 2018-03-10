package com.skripsi.mrizk.findingdosen.repository.transformer;

import com.skripsi.mrizk.findingdosen.repository.entity.ErrorMessage;
import com.skripsi.mrizk.findingdosen.repository.entity.api.ErrorMessageRemote;
import com.skripsi.mrizk.findingdosen.repository.utils.BaseLayerDataTransformer;

/**
 * Created by mrizk on 08/03/2018.
 */

public class ErrorMessageRemoteToErrorMessage extends BaseLayerDataTransformer<ErrorMessageRemote, ErrorMessage> {
    @Override
    public ErrorMessage transform(ErrorMessageRemote from) {
        ErrorMessage error = new ErrorMessage();
        return null;
    }
}

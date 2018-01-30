package com.skripsi.mrizk.findingdosen.repository.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mrizk on 16/01/2018.
 */

public abstract class BaseLayerDataTransformer<F, T> implements ILayerDataTransformer<F, T> {
    @Override
    public List<T> transform(Collection<F> from) {
        List<T> transformed = new ArrayList<>(from.size());

        for (F fromObject : from) {
            transformed.add(transform(fromObject));
        }

        return transformed;
    }
}

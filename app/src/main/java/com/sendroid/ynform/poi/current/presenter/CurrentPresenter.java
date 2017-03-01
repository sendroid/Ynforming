package com.sendroid.ynform.poi.current.presenter;

import com.sendroid.ynform.poi.AbstractInformStatusModel;

/**
 * Created by sendro on 27.02.17.
 */

public interface CurrentPresenter {
    void updateCurrent(AbstractInformStatusModel model);
    void swapCurrent(AbstractInformStatusModel model);
}

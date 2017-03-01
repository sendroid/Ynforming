package com.sendroid.ynform.poi.current.view;

import com.sendroid.ynform.poi.AbstractInformStatusModel;

/**
 * Created by sendro on 27.02.17.
 */

public interface CurrentView {
    void show();
    void hide();
    void update(AbstractInformStatusModel model);
}

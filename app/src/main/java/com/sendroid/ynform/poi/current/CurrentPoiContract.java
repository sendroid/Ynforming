package com.sendroid.ynform.poi.current;

import com.sendroid.ynform.poi.AbstractInformStatusModel;

/**
 * Created by sendrowski on 06.04.17.
 */

public interface CurrentPoiContract {

    interface View {

        void show();

        void hide();

        void update(AbstractInformStatusModel model);
    }

    interface Presenter {

        void updateCurrent(AbstractInformStatusModel model);

        void swapCurrent(AbstractInformStatusModel model);

    }

}

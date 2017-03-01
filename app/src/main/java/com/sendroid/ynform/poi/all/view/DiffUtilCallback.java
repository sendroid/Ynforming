package com.sendroid.ynform.poi.all.view;

import android.support.v7.util.DiffUtil;

import com.sendroid.ynform.poi.AbstractInformStatusModel;

import java.util.List;

/**
 * Created by sendro on 01.03.17.
 */

public class DiffUtilCallback extends DiffUtil.Callback {

    private List<AbstractInformStatusModel> oldInformStatusModels;
    private List<AbstractInformStatusModel> newInformStatusModels;

    public DiffUtilCallback(List<AbstractInformStatusModel> oldInformStatusModels, List<AbstractInformStatusModel> newInformStatusModels) {
        this.oldInformStatusModels = oldInformStatusModels;
        this.newInformStatusModels = newInformStatusModels;
    }

    @Override
    public int getOldListSize() {
        return oldInformStatusModels.size();
    }

    @Override
    public int getNewListSize() {
        return newInformStatusModels.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldInformStatusModels.get(oldItemPosition).getId() == newInformStatusModels.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldInformStatusModels.get(oldItemPosition).getTitle().equals(newInformStatusModels.get(newItemPosition).getTitle())
                && oldInformStatusModels.get(oldItemPosition).getSubtitle().equals(newInformStatusModels.get(newItemPosition).getSubtitle());
    }
}

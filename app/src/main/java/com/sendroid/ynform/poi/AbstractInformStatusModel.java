package com.sendroid.ynform.poi;

/**
 * Created by sendro on 26.02.17.
 */

public class AbstractInformStatusModel {

    private long id;
    private String title;
    private String subtitle;

    public AbstractInformStatusModel(long id, String title, String subtitle) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((AbstractInformStatusModel) obj).getId() == id;
    }
}

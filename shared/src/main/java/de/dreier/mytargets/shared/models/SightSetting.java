/*
 * MyTargets Archery
 *
 * Copyright (C) 2015 Florian Dreier
 * All rights reserved
 */
package de.dreier.mytargets.shared.models;

public class SightSetting implements IIdSettable {
    public static final String ID = "_id";
    public long bowId;
    public Distance distance = new Distance(18, Dimension.METER);
    public String value = "";
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof SightSetting &&
                getClass().equals(another.getClass()) &&
                id == ((SightSetting) another).id;
    }
}

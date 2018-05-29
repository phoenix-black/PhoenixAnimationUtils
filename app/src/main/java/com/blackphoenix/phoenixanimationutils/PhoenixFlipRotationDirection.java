package com.blackphoenix.phoenixanimationutils;

/**
 * Created by Praba on 5/29/2018.
 */

public enum PhoenixFlipRotationDirection {
    HORIZONTAL("rotationY"),
    VERTICAL("rotationX");

    String propertyName;

    PhoenixFlipRotationDirection(String property){
        this.propertyName = property;
    }

    @Override
    public String toString() {
        return propertyName;
    }
}

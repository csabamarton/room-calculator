package com.csmarton.roomcalculator.model;

import lombok.Data;

@Data
public class Room {
    private int length;
    private int width;
    private int height;
    private int totalWallpaper;

    public Room(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.totalWallpaper = calculateTotalWallpaper();
    }

    private int calculateTotalWallpaper() {
        int sideArea = Math.min(length * width, Math.min(width * height, height * length));
        int sum = 2 * (length * width) + 2 * (width * height) + 2 * (height * length);
        int i = sum + sideArea;
        return i;
    }

    public boolean isCubicShape() {
        return length == width && width == height;
    }

    @Override
    public String toString() {
        return length + "x" + width + "x" + height;
    }
}
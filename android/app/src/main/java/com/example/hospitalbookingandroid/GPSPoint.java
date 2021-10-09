package com.example.hospitalbookingandroid;

import android.location.Location;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

/**
 *  * Created by alejandro.tkachuk
 *   */

public class GPSPoint {
    Location location;

    public GPSPoint(Location currentLocation) {
        this.location=currentLocation;

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}



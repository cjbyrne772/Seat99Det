package com.seatstir.andy.eventlistlib;

/**
 * Created by fred on 8/15/2015.
 */

public class EventData {
    private String shortdesc;
    private String venue;

    public EventData() {
    }
    // The constructor is called as the json data is parsed
    public EventData(String shortdesc, String venue) {
        this.shortdesc = shortdesc;
        this.venue = venue;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getVenue() {
        return venue;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}

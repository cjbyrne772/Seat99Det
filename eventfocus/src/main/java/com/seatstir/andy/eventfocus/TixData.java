package com.seatstir.andy.eventfocus;

import java.util.Date;

/**
 * Created by fred on 9/1/2015.
 */
public class TixData {
    private int qtyAllowed;
    private Date perfDate;
    private int perfID;

    public TixData() {}
    public TixData(int q, int p) {
        this.qtyAllowed = q;
        this.perfID = p;
    }
   public int getperfID() { return this.perfID; }

}

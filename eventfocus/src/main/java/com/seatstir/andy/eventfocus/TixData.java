package com.seatstir.andy.eventfocus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fred on 9/1/2015.
 */
public class TixData {
    private int qtyAllowed;
    private String  perfDate;
    private int perfID;
    private int qlimit;  // yes it looks kludgy, since we are storing a ticket limit for each
                         // performance. But this looks like the easiest way to implement.
    private int qavail;

    public TixData() {}
    public TixData(String d, int p, int q) {
        this.perfDate = d;
        this.perfID = p;
        this.qlimit = q;
    }
    public TixData(String d, int p, int q, int a) {
        this.perfDate = d;
        this.perfID = p;
        this.qlimit = q;
        this.qavail = a;
    }

    public int getperfID() { return this.perfID; }
    public int getqlimit() { return this.qlimit; }
    public int getqavail() { return this.qavail; }
   public String getperfString() { return this.perfDate;}

}

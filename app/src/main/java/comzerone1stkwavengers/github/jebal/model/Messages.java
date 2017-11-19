package comzerone1stkwavengers.github.jebal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yicho on 2017-11-07.
 */

public class Messages {
    @SerializedName("mid")
    @Expose
    private String mid;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "Data { " + "mid = " + mid + " }";
    }
}

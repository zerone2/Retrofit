package comzerone1stkwavengers.github.jebal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yicho on 2017-08-27.
 */

public class Feed {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("mid")
    @Expose
    private Messages mid;

    public Messages getMid() {
        return mid;
    }

    public void setMid(Messages mid) {
        this.mid = mid;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "data = " + data +
                "mid = " + mid +
                '}';
    }
}

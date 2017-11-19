package comzerone1stkwavengers.github.jebal.connection;

import comzerone1stkwavengers.github.jebal.model.Feed;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by yicho on 2017-08-27.
 */

public interface RedditAPI {
    //String BASE_URL="https://api.artik.cloud/";

    @Headers("Content-Type: application/json")
    @GET("users/self?Authorization=Bearer+bab1fae1330240dbab02612c4bdb9303")
    //Call<Feed> getData(@Query("Authorization") String auth);
    Call<Feed> getData();

    @FormUrlEncoded
    @POST("messages?Authorization=Bearer+bab1fae1330240dbab02612c4bdb9303")
    Call<Feed> getMid(@Body() String message);
}

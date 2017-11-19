package comzerone1stkwavengers.github.jebal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import comzerone1stkwavengers.github.jebal.connection.RedditAPI;
import comzerone1stkwavengers.github.jebal.model.Feed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL= "https://api.artik.cloud/v1.1/";
    private String AUTH;

    ListView ArtikInfoView;
    EditText postText;
    List<String> userInfoList = new ArrayList<>();

    /*private ArrayList<HashMap<String,String>> userInfo = new ArrayList<>();
    private HashMap<String,String> inputData = new HashMap<>();
    private HashMap<String,String> inputData2 = new HashMap<>();
    private HashMap<String,String> inputData3 = new HashMap<>();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetData = (Button) findViewById(R.id.btnGetData);
        Button sendDataBtn = (Button) findViewById(R.id.sendDataBtn);

        ArtikInfoView = (ListView) findViewById(R.id.ArtikInfo_list);
        postText = (EditText)findViewById(R.id.postText);

        try{AUTH = URLDecoder.decode("Bearer+bab1fae1330240dbab02612c4bdb9303".replace("+","%2B"),"UTF-8");}
        catch (UnsupportedEncodingException e){}

        try {
            Log.e("error", URLDecoder.decode("Bearer+bab1fae1330240dbab02612c4bdb9303".replace("+","%2B"),"UTF-8"));
            Log.e("error2", AUTH);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        final Call<Feed>[] call = new Call[2];
        //call = redditAPI.getData(AUTH);

        //getUserData(call);


        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyBoard(postText);
                call[0] = redditAPI.getData();
                getUserData(call[0]);
                setListView();
                //Log.e("find error point : ", "error in here4");
            }
        });
        sendDataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                hideKeyBoard(postText);
                String sendMsg = "{\n" +
                        "    \"data\": {\n" +
                        "        \"actions\": [\n" +
                        "            {\n" +
                        "                \"name\": \"sendToMe\",\n" +
                        "                \"parameters\": \n" +
                        "                {\n" +
                        "                \t\"value\" : \"여원재 병신\"\n" +
                        "                }\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    },\n" +
                        "    \"ddid\": \"dc59db1e5d584c438fcc0eb230ba37c1\",\n" +
                        "    \"type\": \"action\"\n" +
                        "}";
                call[1] = redditAPI.getMid(sendMsg);
                sendMessage(call[1]);
                setListView();
            }
        });

    }

    private void getUserData(Call<Feed> call){
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse : Server Response : " + response.toString());
                Log.d(TAG, "onResponse : received information : " + response.body().getData().toString());

                /*inputData.put("id", "id : "+response.body().getData().getId());
                inputData.put("email","e-mail : "+response.body().getData().getEmail());
                inputData.put("name", "name : "+response.body().getData().getFullName());
                userInfo.add(inputData);*/
                //userInfo.add(inputData2);
                //userInfo.add(inputData3);

                //userInfoList = Arrays.asList(response.body().getData().getId());
                userInfoList.add("id : "+response.body().getData().getId());
                userInfoList.add("e-mail : "+response.body().getData().getEmail());
                userInfoList.add("name : "+response.body().getData().getFullName());
                Log.e("userInfoList : ", ""+userInfoList.toString());

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure : something went wrong: " + t.getMessage());
                Toast.makeText(MainActivity.this, " Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendMessage(Call<Feed> call){
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse : Server Response : " + response.toString());
                Log.d(TAG, "onResponse : received information : " + response.body().getMid().toString());

                userInfoList.clear();
                userInfoList.add("mid : "+response.body().getMid().getMid());


                Log.e("userInfoList : ", "" + userInfoList.toString());

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure : something went wrong: " + t.getMessage());
                Toast.makeText(MainActivity.this, " Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListView(){
        //Log.e("find error point : ","error in here1");
        ArrayAdapter<String> listAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userInfoList);
        //Log.e("find error point : ", "error in here2");
        ArtikInfoView.setAdapter(listAdapter);
        //Log.e("find error point : ", "error in here3");
    }

    private void hideKeyBoard(EditText et){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(),0);
    }
}

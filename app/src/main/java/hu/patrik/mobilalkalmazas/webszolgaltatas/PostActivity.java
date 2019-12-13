package hu.patrik.mobilalkalmazas.webszolgaltatas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import hu.patrik.mobilalkalmazas.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    private RecyclerView PostRecyclerView;
    private RecyclerView.Adapter PostAdapter;
    private RecyclerView.LayoutManager PostLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        PostLayoutManager = new LinearLayoutManager(this);


        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();


        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call=jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {


                List<Post> posts=response.body();

                PostRecyclerView = findViewById(R.id.recyclerView);
                PostRecyclerView.setHasFixedSize(true);

                PostAdapter = new PostAdapter(posts);

                PostRecyclerView.setLayoutManager(PostLayoutManager);
                PostRecyclerView.setAdapter(PostAdapter);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

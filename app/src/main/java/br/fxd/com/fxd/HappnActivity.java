package br.fxd.com.fxd;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.fxd.com.fxd.adapter.UserCustomAdapter;
import br.fxd.com.fxd.model.User;
import br.fxd.com.fxd.webservice.APIClient;
import br.fxd.com.fxd.webservice.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HappnActivity extends AppCompatActivity {

    private Call<List<User>> callUser;
    private UserCustomAdapter userCustomAdapter;
    private APIInterface apiService;
    private ArrayList<User> userList;
    private RecyclerView recyclerView;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happn);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);

        apiService = APIClient.getService().create(APIInterface.class);
        callUser = apiService.getHappnFXD();
        userList = new ArrayList<>();

        progress = ProgressDialog.show(HappnActivity.this, "Carregando", "Buscando as pessoas que você cruzou...", true);

        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.raw().code() == 200) {

                    List<User> l = new ArrayList<User>();
                    l.addAll(response.body());

                    for (User d : l) {
                        userList.add(new User(d.getId(), d.getName(), d.getAge(), d.getBike(), d.getLat(), d.getLng()));
                    }

                    Collections.reverse(userList);
                    userCustomAdapter = new UserCustomAdapter(getBaseContext(), userList);

                    recyclerView.setAdapter(userCustomAdapter);

                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("DOCTORS", t.toString());

                progress.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}

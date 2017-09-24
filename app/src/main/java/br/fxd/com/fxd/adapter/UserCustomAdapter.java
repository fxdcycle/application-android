package br.fxd.com.fxd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import br.fxd.com.fxd.R;
import br.fxd.com.fxd.helper.DownloadImageTask;
import br.fxd.com.fxd.model.User;

/**
 * Created by matheuscatossi on 23/09/17.
 */

public class UserCustomAdapter extends RecyclerView.Adapter<UserCustomAdapter.MyViewHolder> {

    private Context mContext;
    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age, bike;
        public ImageView person;
        public LinearLayout ll_card_bike;

        public MyViewHolder(View view) {
            super(view);
            name          = (TextView) view.findViewById(R.id.name);
            age           = (TextView) view.findViewById(R.id.age);
            bike         = (TextView) view.findViewById(R.id.bike);
            person        = (ImageView) view.findViewById(R.id.person);
            ll_card_bike = (LinearLayout) view.findViewById(R.id.newsfeeds_card);
        }
    }

    public UserCustomAdapter(Context mContext, List<User> userList) {
        this.mContext = mContext;
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_doctor, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final User user = userList.get(position);
        holder.name.setText("" + user.getName());
        holder.age.setText("" + user.getAge());
        holder.bike.setText("" + user.getBike());

        holder.person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, listSchedulesActivity.class);
//                intent.putExtra("id", "" + evento.getId());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);
            }
        });


        Random r = new Random();

        int result = r.nextInt(6);

        switch (result) {
            case 0:
                holder.person.setImageResource(R.drawable.rafael);
                break;
            case 1:
                holder.person.setImageResource(R.drawable.caique);
                break;
            case 2:
                holder.person.setImageResource(R.drawable.carolina);
                break;
            case 3:
                holder.person.setImageResource(R.drawable.luiz);
                break;
            case 4:
                holder.person.setImageResource(R.drawable.maicon);
                break;
            case 5:
                holder.person.setImageResource(R.drawable.matheus);
                break;
            default:
                holder.person.setImageResource(R.drawable.rafael);
                break;
        }

//        new DownloadImageTask(holder.person).execute("http://f.i.uol.com.br/folha/esporte/images/16225746.jpeg");
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}

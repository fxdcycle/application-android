package br.fxd.com.fxd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.fxd.com.fxd.R;
import br.fxd.com.fxd.model.EWallet;

/**
 * Created by matheuscatossi on 24/09/17.
 */

public class EWalletCustomAdapter extends ArrayAdapter<EWallet> implements View.OnClickListener {

    private ArrayList<EWallet> dataSet;
    private Context mContext;

    @Override
    public void onClick(View v) {

    }

    private static class ViewHolder {
        TextView tv_title;
        TextView tv_description;
        TextView tv_date;
        ImageView iv_maps;
        LinearLayout ll_line;
    }

    public EWalletCustomAdapter(ArrayList<EWallet> data, Context context) {
        super(context, R.layout.row_item_ewallet, data);

        this.dataSet = data;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final EWallet eWallet = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_ewallet, parent, false);

            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.iv_maps = (ImageView) convertView.findViewById(R.id.iv_maps);


            viewHolder.ll_line = (LinearLayout) convertView.findViewById(R.id.ll_line);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tv_title.setText("" + eWallet.getTitle());
        viewHolder.tv_description.setText("" + eWallet.getDescription());
        viewHolder.tv_date.setText("" + eWallet.getDate());
        viewHolder.iv_maps.setImageResource(R.drawable.maps);

        viewHolder.ll_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
            }
        });

        return convertView;
    }
}
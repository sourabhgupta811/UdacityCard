package com.example.android.udacitycard;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    CardView previous_child;
    Context context;
    RecyclerView rv;
    String[] data=new String[]{"Address","Phone","Website"};
    String[] child_data=new String[]{"2465 Latham St Mountain View, CA 94043","650-555-5555","www.udacity.com"};
    int[] icons={R.drawable.ic_map_marker,R.drawable.ic_phone,R.drawable.ic_google_chrome};
    public CustomAdapter(Context context,RecyclerView rv){
        this.rv=rv;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.recyclerview_tem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView text=holder.card_text_view;
        TextView small_text=holder.child_card.findViewById(R.id.button);
        small_text.setText(child_data[position]);
        text.setText(data[position]);
//        text.setPadding(10,10,10,10);
        text.setCompoundDrawablesWithIntrinsicBounds(icons[position], 0, 0, 0);
        text.setCompoundDrawablePadding(80);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView card_text_view;
        public CardView child_card;
        public ViewHolder(final View itemView) {
            super(itemView);

            card_text_view=itemView.findViewById(R.id.card_text);
            child_card=itemView.findViewById(R.id.child);
            child_card.setVisibility(View.GONE);
//            TextView button=child_card.findViewById(R.id.button);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context,"hello"+itemView.getId(),Toast.LENGTH_LONG).show();
//                }
//            });
            card_text_view.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    if(child_card.getVisibility()==View.GONE){

                        child_card.animate().translationX(0).start();
                        child_card.animate().x(0).setDuration(1000).start();
                        child_card.setVisibility(View.VISIBLE);

                        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT) {
                            TransitionManager.beginDelayedTransition(rv);
                            //notifyDataSetChanged();
                        }

                        if(previous_child!=null) {
                            previous_child.animate().translationX(1000).withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    previous_child.animate().translationX(0).start();
                                }
                            }).setDuration(1000).start();
                            previous_child.setVisibility(View.GONE);

                            Log.d("previous if", "in previous if");
                        }
                        previous_child=child_card;
                        Log.d("if","in main if");
                    }
                    else{
                        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT) {
                            TransitionManager.beginDelayedTransition(rv);
                        }
                        Log.d("else","in else");
                        child_card.animate().translationXBy(1000).setDuration(1000).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                child_card.animate().translationX(0).start();
                            }
                        }).start();
                        child_card.setVisibility(View.GONE);
                        previous_child=null;

                    }
                }
            });
        }
    }
}
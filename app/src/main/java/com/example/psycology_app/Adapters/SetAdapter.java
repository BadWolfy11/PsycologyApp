package com.example.psycology_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psycology_app.Activities.QuestionActivity;
import com.example.psycology_app.Activities.SetsActivity;
import com.example.psycology_app.Models.SetModel;
import com.example.psycology_app.R;
import com.example.psycology_app.databinding.ItemSetBinding;

import java.util.ArrayList;

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private Context context;
    private ArrayList<SetModel> list;

    public SetAdapter(Context context, ArrayList<SetModel> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(SetModel setModel);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_set, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SetModel model = list.get(position);
        holder.binding.getName.setText(model.getSetName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        SetModel setModel = list.get(position);
                        listener.onItemClick(setModel);
                    }
                }
                // Открыть новую активность или выполнить другие действия при клике на элемент списка
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("set", model.getSetName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemSetBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSetBinding.bind(itemView);
        }
    }
}

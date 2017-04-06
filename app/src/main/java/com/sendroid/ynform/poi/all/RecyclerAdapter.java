package com.sendroid.ynform.poi.all;

import android.support.annotation.IntDef;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sendroid.ynform.R;
import com.sendroid.ynform.poi.AbstractInformStatusModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.sendroid.ynform.poi.all.RecyclerAdapter.PoiControllerType.TYPE_BACKGROUND;
import static com.sendroid.ynform.poi.all.RecyclerAdapter.PoiControllerType.TYPE_MAIN;
import static com.sendroid.ynform.poi.all.RecyclerAdapter.PoiControllerType.TYPE_MAP;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @IntDef({TYPE_MAP,
            TYPE_MAIN,
            TYPE_BACKGROUND})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PoiControllerType{
        int TYPE_MAP = 0;
        int TYPE_MAIN = 1;
        int TYPE_BACKGROUND = 2;
    }
    private final ArrayList<AbstractInformStatusModel> informStatusModels;

    private RecyclerItemClickListener recyclerItemClickListener;
    private @RecyclerAdapter.PoiControllerType int controllerType = -1;

    public void setRecyclerItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public RecyclerAdapter(ArrayList<AbstractInformStatusModel> informStatusModels, @RecyclerAdapter.PoiControllerType int type) {
        this.informStatusModels = informStatusModels;
        this.controllerType = type;
    }

    public void updateList(List<AbstractInformStatusModel> modelList){
        if(modelList == null){
            informStatusModels.clear();
            notifyDataSetChanged();
            return;
        }

        DiffUtil.DiffResult diff = DiffUtil.calculateDiff(new DiffUtilCallback(informStatusModels, modelList));
        diff.dispatchUpdatesTo(this);
        informStatusModels.clear();
        informStatusModels.addAll(modelList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, @PoiControllerType int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_BACKGROUND:
                viewHolder = new BackgroundViewHolder(inflater.inflate(R.layout.poi_bckg_item, parent, false));
                break;
            case TYPE_MAP:
                viewHolder = new MapViewHolder(inflater.inflate(R.layout.poi_map_item, parent, false));
                break;
            case TYPE_MAIN:
                viewHolder = new MainViewHolder(inflater.inflate(R.layout.poi_main_item, parent, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        AbstractInformStatusModel informStatusModel = informStatusModels.get(position);

        switch (holder.getItemViewType()) {
            case TYPE_BACKGROUND:
                setBackgroundView(((BackgroundViewHolder) holder), informStatusModel);
                break;

            case TYPE_MAP:
                setMapView(((MapViewHolder) holder), informStatusModel);
                break;

            case TYPE_MAIN:
                setMainView(((MainViewHolder) holder), informStatusModel);
                break;

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerItemClickListener != null)
                    recyclerItemClickListener.onItemClickListener(holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return informStatusModels.size();
    }

    @PoiControllerType
    @Override
    public int getItemViewType(int position) {
        return this.controllerType;
    }


    private static class BackgroundViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;
        BackgroundViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }

    private static class MapViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;
        MapViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }

    private static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;
        MainViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }



    private void setBackgroundView(BackgroundViewHolder holder, AbstractInformStatusModel model) {
        holder.title.setText(model.getTitle());
        holder.subtitle.setText(model.getSubtitle());
    }

    private void setMapView(MapViewHolder holder, AbstractInformStatusModel model) {
        holder.title.setText(model.getTitle());
        holder.subtitle.setText(model.getSubtitle());
    }

    private void setMainView(MainViewHolder holder, AbstractInformStatusModel model) {
        holder.title.setText(model.getTitle());
        holder.subtitle.setText(model.getSubtitle());
    }

}

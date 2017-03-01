package com.sendroid.ynform.poi;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sendroid.ynform.R;
import com.sendroid.ynform.poi.all.view.RecyclerAdapter;
import com.sendroid.ynform.poi.all.view.RecyclerItemClickListener;
import com.sendroid.ynform.poi.current.presenter.CurrentPresenter;
import com.sendroid.ynform.poi.current.view.CurrentView;
import com.sendroid.ynform.poi.current.view.CurrentViewBackground;
import com.sendroid.ynform.poi.current.view.CurrentViewMain;
import com.sendroid.ynform.poi.current.view.CurrentViewMap;

import java.util.ArrayList;
import java.util.List;

import static com.sendroid.ynform.poi.all.view.RecyclerAdapter.PoiControllerType.TYPE_BACKGROUND;
import static com.sendroid.ynform.poi.all.view.RecyclerAdapter.PoiControllerType.TYPE_MAIN;
import static com.sendroid.ynform.poi.all.view.RecyclerAdapter.PoiControllerType.TYPE_MAP;


/**
 * Created by sendro on 26.02.17.
 */

public class PoiController implements RecyclerItemClickListener, CurrentPresenter {

    private Context context;
    private CurrentView currentView;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerViewAdapter;
    private AbstractInformStatusModel currentModel;
    private final List<AbstractInformStatusModel> informStatusModels = new ArrayList<>();

    public PoiController(Context context) {
        this.context = context;
        this.recyclerView = (RecyclerView) ((Activity) context).findViewById(R.id.poi_recycler);
    }

    public void initialize(@RecyclerAdapter.PoiControllerType int type){
        uninitialize();
        setupViews(type);
    }

    public void uninitialize() {
        if (currentView != null) {
            currentView.update(null);
        }
        if (recyclerViewAdapter != null) {
            recyclerViewAdapter.updateList(null);
        }
    }

    public void update(List<AbstractInformStatusModel> currentModels){
        if(currentModels == null || currentView == null || recyclerViewAdapter == null)
            return;
        informStatusModels.clear();
        informStatusModels.addAll(currentModels);
        if(informStatusModels.size() > 0) {
            AbstractInformStatusModel item = informStatusModels.get(0);
            if (currentModel != null && currentModel.equals(item)) {
                updateCurrent(item);
                informStatusModels.remove(item);
            } else {
                swapCurrent(item);
            }
        }
        recyclerViewAdapter.updateList(informStatusModels);

    }

    private void setupViews(@RecyclerAdapter.PoiControllerType int controllerType){
        recyclerViewAdapter = new RecyclerAdapter(new ArrayList<AbstractInformStatusModel>(), controllerType);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setRecyclerItemClickListener(this);
        switch (controllerType){
            case TYPE_MAP:
                currentView = new CurrentViewMap();
                break;
            case TYPE_MAIN:
                currentView = new CurrentViewMain();
                break;
            case TYPE_BACKGROUND:
                currentView = new CurrentViewBackground();
                break;
        }
    }

    @Override
    public void onItemClickListener(int position) {
        AbstractInformStatusModel item = informStatusModels.get(position);
        swapCurrent(item);
        recyclerViewAdapter.updateList(informStatusModels);
        Log.d("positionClick", String.valueOf(position));
    }

    @Override
    public void updateCurrent(AbstractInformStatusModel model) {
        currentModel = model;
        currentView.update(currentModel);
    }

    @Override
    public void swapCurrent(AbstractInformStatusModel model) {
        if(currentModel != null) {
            informStatusModels.add(0, currentModel);
        }
        currentModel = model;
        informStatusModels.remove(model);
        //TODO:
        currentView.hide();
        currentView.update(currentModel);
        currentView.show();
    }
}

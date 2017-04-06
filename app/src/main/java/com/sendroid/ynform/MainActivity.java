package com.sendroid.ynform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sendroid.ynform.poi.AbstractInformStatusModel;
import com.sendroid.ynform.poi.PoiController;
import com.sendroid.ynform.poi.all.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PoiController poiController;
    private List<AbstractInformStatusModel> currentModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        poiController = new PoiController(this);

        currentModels = new ArrayList<>();
        currentModels.add(new AbstractInformStatusModel(0, "Zagrozenie", "200m"));
        currentModels.add(new AbstractInformStatusModel(1, "Wypadek", "100m"));
        currentModels.add(new AbstractInformStatusModel(2, "Inspekcja", "300m"));
        currentModels.add(new AbstractInformStatusModel(3, "Fotoradar", "4200m"));
        currentModels.add(new AbstractInformStatusModel(4, "Prace drogowe", "500m"));

        findViewById(R.id.btn_init).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poiController.initialize(RecyclerAdapter.PoiControllerType.TYPE_MAP);
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poiController.update(currentModels);
            }
        });

    }
}

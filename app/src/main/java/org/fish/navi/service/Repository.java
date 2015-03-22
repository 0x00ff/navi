package org.fish.navi.service;

import android.content.Context;

import org.fish.navi.model.Target;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository instance;
    private Context context;
    private ArrayList<Target> targets;


    private Repository(Context context) {
        this.context = context;
        targets = new ArrayList<>();

        //test only

        Target target = new Target();
        target.setName("Test target");
        targets.add(target);
    }


    public static Repository get(Context context) {
        if (instance == null) {
            instance = new Repository(context.getApplicationContext());
        }

        return instance;
    }

    public List<Target> getTargets() {
        return targets;
    }
}

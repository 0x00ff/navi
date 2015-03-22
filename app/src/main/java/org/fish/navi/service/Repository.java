package org.fish.navi.service;

import android.content.Context;

import org.fish.navi.model.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        target.setComment("Hi there!");
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

    public Target getTarget(UUID id) {
        for (Target t : targets) {
            if (t.getId().equals(id))
                return t;
        }
        return null;

    }
}

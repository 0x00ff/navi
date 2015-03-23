package org.fish.navi.service;

import android.content.Context;

import org.fish.navi.model.Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Repository {
    private static Repository instance;
    private Context context;
    private ArrayList<Target> targets;


    private Repository(Context context) {
        this.context = context;
        targets = new ArrayList<>();
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

    public List<String> getCategories() {
        return Arrays.asList("river", "lake", "pond");
    }

    public Target getTarget(UUID id) {
        for (Target t : targets) {
            if (t.getId().equals(id))
                return t;
        }
        return null;
    }

    public boolean removeTarget(UUID id) {
        for (Target t : targets) {
            if (t.getId().equals(id))
                return targets.remove(t);
        }
        return false;
    }

    public boolean setTarget(Target target) {
        if (target == null) throw new NullPointerException();

        //TODO: implement update code

        return targets.add(target);
    }
}

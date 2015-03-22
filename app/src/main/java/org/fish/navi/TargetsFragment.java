package org.fish.navi;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.fish.navi.model.Target;
import org.fish.navi.service.Repository;

import java.util.List;

public class TargetsFragment extends ListFragment {
    private List<Target> targets;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.targets_title);

        targets = Repository.get(getActivity()).getTargets();

        ArrayAdapter<Target> adapter = new ArrayAdapter<Target>(getActivity(), R.layout.list_item, targets);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Target c = (Target)(getListAdapter()).getItem(position);
        Log.d("TargetsFragment", c.getName() + " was clicked");
    }
}

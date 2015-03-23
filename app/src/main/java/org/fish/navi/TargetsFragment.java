package org.fish.navi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        ArrayAdapter<Target> adapter = new TargetAdapter(targets);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Target target = (Target)(getListAdapter()).getItem(position);

        Intent intent = new Intent(getActivity(), TargetActivity.class);
        intent.putExtra(TargetFragment.EXTRA_TARGET_ID, target.getId());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TargetAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class TargetAdapter extends ArrayAdapter<Target>{
        public TargetAdapter(List<Target> targets) {
            super(getActivity(), 0, targets);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_target, null);
            }

            Target target = getItem(position);

            // TODO: set target icon

            TextView titleTextView = (TextView)convertView.findViewById(R.id.target_list_item_name);
            titleTextView.setText(target.getName());

            TextView categoryTextView = (TextView)convertView.findViewById(R.id.target_list_item_category);
            categoryTextView.setText(target.getId().toString()); // TODO: get category name here

            return convertView;
        }
    }
}

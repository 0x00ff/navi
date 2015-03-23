package org.fish.navi;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import org.fish.navi.model.Target;
import org.fish.navi.service.GpsManager;
import org.fish.navi.service.LocationReceiver;
import org.fish.navi.service.Repository;

import java.util.UUID;

public class TargetFragment extends Fragment {
    public static final String EXTRA_TARGET_ID =
            "org.fish.navi.target_id";

    private Target target;
    private Location location;
    private GpsManager gpsManager;
    private LocationReceiver locationReceiver;

    private EditText nameField;
    private AutoCompleteTextView categoryField;
    private EditText latitudeField;
    private EditText longitudeField;
    private EditText altitudeField;
    private EditText accuracyField;
    private EditText commentField;


    public static TargetFragment newInstance(UUID targetId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TARGET_ID, targetId);
        TargetFragment fragment = new TargetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TargetFragment(){
        locationReceiver = new LocationReceiver(){
            @Override
            protected void onLocationReceived(Context context, Location loc) {
                super.onLocationReceived(context, loc);

                location = loc;
                if (isVisible()) updateUI();
            }
            @Override
            protected void onProviderEnabledChanged(boolean enabled) {
                super.onProviderEnabledChanged(enabled);

                //TODO: introduce gpsmanager indicator
                //int toastText = enabled ? R.string.gps_enabled : R.string.gps_disabled;
                //Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        gpsManager = GpsManager.instance(getActivity());

        if (getArguments().containsKey(EXTRA_TARGET_ID)) {
            UUID id = (UUID)getArguments().getSerializable(EXTRA_TARGET_ID);
            Log.d("TargetFragment", "Going to edit " + id + " target");
            target = Repository.get(getActivity()).getTarget(id);
            getActivity().setTitle(R.string.target_update_title);
        } else {
            Log.d("TargetFragment", "Going to create brand new target");
            target = new Target();
            gpsManager.startLocationUpdates();
            getActivity().setTitle(R.string.target_create_title);
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        gpsManager.stopLocationUpdates();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View result = inflater.inflate(R.layout.fragment_target, parent, false);

        nameField = (EditText)result.findViewById(R.id.target_name);
        nameField.setText(target.getName());
        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                target.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        categoryField = (AutoCompleteTextView)result.findViewById(R.id.target_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, Repository.get(getActivity()).getCategories());
        categoryField.setAdapter(adapter);

        latitudeField = (EditText)result.findViewById(R.id.target_latitude);
        latitudeField.setText(Location.convert(target.getLatitude(), Location.FORMAT_DEGREES));
        latitudeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    double latitude = Location.convert(s.toString());
                    target.setLatitude(latitude);
                    latitudeField.setError(null);}
                catch (Exception e) {
                    latitudeField.setError(getResources().getString(R.string.target_invalid_coordinate_message));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        longitudeField = (EditText)result.findViewById(R.id.target_longitude);
        longitudeField.setText(Location.convert(target.getLongitude(), Location.FORMAT_DEGREES));
        longitudeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    double longitude = Location.convert(s.toString());
                    target.setLongitude(longitude);
                    longitudeField.setError(null); }
                catch (Exception e) {
                    longitudeField.setError(getResources().getString(R.string.target_invalid_coordinate_message));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        altitudeField = (EditText)result.findViewById(R.id.target_altitude);
        altitudeField.setText(String.valueOf(target.getAltitude()));
        altitudeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    double altitude = Double.parseDouble(s.toString());
                    target.setAltitude(altitude);
                    altitudeField.setError(null);}
                catch (Exception e) {
                    altitudeField.setError(getResources().getString(R.string.target_invalid_coordinate_message));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        accuracyField = (EditText)result.findViewById(R.id.target_accuracy);
        accuracyField.setText(String.valueOf(target.getAccuracy()));
        accuracyField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    float accuracy = Float.parseFloat(s.toString());
                    target.setAccuracy(accuracy);
                    accuracyField.setError(null);}
                catch (Exception e) {
                    accuracyField.setError(getResources().getString(R.string.target_invalid_coordinate_message));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        commentField = (EditText)result.findViewById(R.id.target_comment);
        commentField.setText(target.getComment());
        commentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                target.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return result;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_target, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void updateUI() {
        latitudeField.setText(Location.convert(location.getLatitude(), Location.FORMAT_DEGREES));
        longitudeField.setText(Location.convert(location.getLongitude(), Location.FORMAT_DEGREES));
        altitudeField.setText(String.valueOf(location.getAltitude()));
        accuracyField.setText(String.valueOf(location.getAccuracy()));
    }
}


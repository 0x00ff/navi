package org.fish.navi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.fish.navi.model.Target;

public class TargetFragment extends Fragment {
    private Target target;
    private EditText nameField;
    private EditText commentField;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        target = new Target();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View result = inflater.inflate(R.layout.fragment_target, parent, false);

        nameField = (EditText)result.findViewById(R.id.target_name);
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

        commentField = (EditText)result.findViewById(R.id.target_comment);
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
}

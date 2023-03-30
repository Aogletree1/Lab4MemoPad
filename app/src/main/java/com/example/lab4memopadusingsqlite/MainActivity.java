package com.example.lab4memopadusingsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lab4memopadusingsqlite.databinding.ActivityMainBinding;

import java.beans.PropertyChangeEvent;


public class MainActivity extends AppCompatActivity implements AbstractView{

    public static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private DefaultController controller;
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = new DatabaseHandler(this, null, null, 1);
        controller = new DefaultController(db);
        DefaultModel model = new DefaultModel(db);

        controller.addView(this);
        controller.addModel(model);

        model.initDefault();

        MemoClickHandler click = new MemoClickHandler();
        ConstraintLayout layout = binding.layout;

        for (int i = 0; i < layout.getChildCount(); ++i) {
            View child = layout.getChildAt(i);
            if(child instanceof Button) {
                child.setOnClickListener(click);
            }
        }

    }

    class MemoClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String tag = ((Button) v).getTag().toString();
            if (tag.equals("btnAddMemo")) {
                String newText = binding.newMemo.getText().toString();
                controller.addMemo(newText);
            }
            else if (tag.equals("btnDeleteMemo")) {
                String memoToDelete = binding.deleteMemoNum.getText().toString();
                controller.deleteMemo(memoToDelete);

            }
        }
    }

    public void modelPropertyChange(final PropertyChangeEvent evt){
        String propertyName = evt.getPropertyName();
        String propertyValue = evt.getNewValue().toString();

        Log.i(TAG, "New " + propertyName + " Value from Model: " + propertyValue);

        if( propertyName.equals(DefaultController.DATABASE_TEXT)) {
            String oldPropertyValue = binding.memoContents.getText().toString();

            if ( !oldPropertyValue.equals(propertyValue)) {
                binding.memoContents.setText(propertyValue);

            }

        }

    }


}
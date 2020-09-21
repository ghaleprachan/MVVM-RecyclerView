package com.example.recyclermvvm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.recyclermvvm.Adapters.MainActivityAdapter;
import com.example.recyclermvvm.Model.NicePlaces;
import com.example.recyclermvvm.ViewModel.MainActivityVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private MainActivityAdapter mAdapter;
    private ProgressBar mProgressBar;

    private MainActivityVM activityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        activityVM = new ViewModelProvider(this).get(MainActivityVM.class);
        activityVM.init();
        activityVM.getNicePlaces().observe(this, new Observer<List<NicePlaces>>() {
            @Override
            public void onChanged(List<NicePlaces> nicePlaces) {
                mAdapter.notifyDataSetChanged();
            }
        });

        activityVM.getIsUpdating().observe(this, new Observer<Boolean>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                    mRecyclerView.smoothScrollToPosition(Objects.requireNonNull(
                            activityVM.getNicePlaces().getValue()).size() - 1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityVM.addNewValue(new NicePlaces(
                        "https://cdn.britannica.com/67/178667-050-50791B2C/Durbar-Square-district-earthquake-Kathmandu-Nepal-April-25-2015.jpg",
                        "Kathmandu"
                ));
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainActivityAdapter(this, activityVM.getNicePlaces().getValue());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initUi() {
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
    }
}
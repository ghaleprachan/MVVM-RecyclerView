package com.example.recyclermvvm.ViewModel;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclermvvm.Model.NicePlaces;
import com.example.recyclermvvm.Repository.NicePlaceRepository;

import java.util.List;

public class MainActivityVM extends ViewModel {
    private MutableLiveData<List<NicePlaces>> mNicePlaces;
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init() {
        if (mNicePlaces != null) {
            /*This means we've already retrieved the data*/
            return;
        }
        /*Instantiate repository object*/
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    @SuppressLint("StaticFieldLeak")
    public void addNewValue(final NicePlaces nicePlaces) {
        mIsUpdating.setValue(true);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlaces> currentPlaces = mNicePlaces.getValue();
                assert currentPlaces != null;
                currentPlaces.add(nicePlaces);
                mNicePlaces.postValue(currentPlaces);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<NicePlaces>> getNicePlaces() {
        return mNicePlaces;
    }

    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }
}

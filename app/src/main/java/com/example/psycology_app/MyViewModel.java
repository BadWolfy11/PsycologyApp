package com.example.psycology_app;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// Create a ViewModel class
    public class MyViewModel extends ViewModel {
        private MutableLiveData<String> value = new MutableLiveData<>();

        // Save the value
        public void saveValue(String newValue) {
            value.setValue(newValue);
        }

        // Retrieve the value
        public LiveData<String> getValue() {
            return value;
        }
    }



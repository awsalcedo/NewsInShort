package com.asalcedo.newsinshort.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel@Inject constructor(): ViewModel() {

    init {
        Log.d(TAG, "Init block of NewsViewModel")
    }

    companion object{
        const val TAG = "NewsViewModel"
    }
}
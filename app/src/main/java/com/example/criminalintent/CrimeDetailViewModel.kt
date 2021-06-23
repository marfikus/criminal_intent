package com.example.criminalintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class CrimeDetailViewModel(): ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    private val crimeIdLiveData = MutableLiveData<UUID>() // trigger for the Transformations

    // update crimeLiveData by the trigger
    var crimeLiveData: LiveData<Crime?> =
            Transformations.switchMap(crimeIdLiveData) { crimeId ->
                crimeRepository.getCrime(crimeId)
            }

    // update trigger
    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

}
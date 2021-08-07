package com.movie.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movie.android.datamodel.MoviesList
import com.movie.android.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val dataForPopularRecyclerViewModel = MutableLiveData<MoviesList>()

    fun loadDataForRecyclerView() {
        compositeDisposable.add(
            mainRepository.getResponseFromRetrofit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dataForPopularRecyclerViewModel.value = it
                }
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
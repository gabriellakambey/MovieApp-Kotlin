package com.example.movieappkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappkotlin.api.RetrofitInstance
import com.example.movieappkotlin.model.LoginResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RegisViewModel(): ViewModel() {
    private val loginResponseModel = MutableLiveData<LoginResponseModel>()
    private val errorListener = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    fun regis(name: String, email: String, password: String, address: String, dob: String){
        compositeDisposable.add(RetrofitInstance.apiInterface.regis(name, email, password, address, dob)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoginResponseModel>() {
                override fun onSuccess(t: LoginResponseModel) {
                    if("success" == t.status){
                        loginResponseModel.value = t
                    }else{
                        errorListener.value = true
                    }
                }

                override fun onError(e: Throwable) {
                    errorListener.value = true
                }
            }))
    }
}
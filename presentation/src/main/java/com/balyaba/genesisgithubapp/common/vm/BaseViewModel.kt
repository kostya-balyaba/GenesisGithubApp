package com.balyaba.genesisgithubapp.common.vm

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balyaba.genesisgithubapp.common.utils.SingleLiveEvent


/**
 * Created by Baliaba Konstantin on 11.10.2020
 */

abstract class BaseViewModel<STATE, EFFECT, EVENT> : ViewModel() {

    private val _viewStates: MutableLiveData<STATE> = MutableLiveData()
    fun viewState(): LiveData<STATE> = _viewStates

    private var _viewState: STATE? = null
    protected var viewState: STATE
        get() = _viewState
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized ")
        set(value) {
            _viewState = value
            if (Looper.myLooper() == Looper.getMainLooper()) _viewStates.value = value
            else _viewStates.postValue(value)
        }

    private val _viewEffects: SingleLiveEvent<EFFECT> = SingleLiveEvent()
    fun viewEffects(): SingleLiveEvent<EFFECT> = _viewEffects

    private var _viewEffect: EFFECT? = null
    protected var viewEffect: EFFECT
        get() = _viewEffect
            ?: throw UninitializedPropertyAccessException("\"viewEffect\" was queried before being initialized ")
        set(value) {
            _viewEffect = value
            if (Looper.myLooper() == Looper.getMainLooper()) _viewEffects.value = value
            else _viewEffects.postValue(value)
        }

    abstract fun processEvent(viewEvent: EVENT)
}
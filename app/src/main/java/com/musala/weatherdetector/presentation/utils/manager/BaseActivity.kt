package com.musala.weatherdetector.presentation.utils.manager

import android.app.Dialog
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.musala.weatherdetector.presentation.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private var loadingBar: Dialog? = null
    private val baseActivityViewModel: BaseActivityViewModel by viewModels()

    @Inject
    lateinit var responseManager: ResponseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        observeResponse()
        observeLoading()
        observeSuccess()
        observeFailed()
        observeNoConnection()
        observeHideLoading()

    }

    private fun observeHideLoading() {
        baseActivityViewModel.observeHideLoading.observe(this,
            EventObserver {

            })
    }

    private fun observeNoConnection() {
        baseActivityViewModel.observeNoConnection.observe(this,
            EventObserver {


            })
    }

    private fun observeFailed() {
        baseActivityViewModel.observeFailed.observe(this,
            EventObserver { errorMessage ->


            })
    }

    private fun observeSuccess() {
        baseActivityViewModel.observeSuccess.observe(this,
            EventObserver { successMessage ->


            })
    }

    private fun observeLoading() {
        baseActivityViewModel.observeLoading.observe(this,
            EventObserver {

            })
    }


    open fun observeResponse() {
        responseManager.observeResponseManager.observe(this, EventObserver
        { responseResource ->
            try {
                baseActivityViewModel.getResponseState(responseResource)
            } catch (ex: NullPointerException) {
            }
        })
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }



}
package com.tarasov.cryptoapp21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tarasov.cryptoapp21.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//[2nd fun]//        val disposable = ApiFactory.apiService.getFullPriceList(fSyms = "BTC,ETH,EOS")
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       Log.d("TEST_OF_LOADING_DATA", it.toString())
            },{
                it.message?.let { it1 -> Log.d("TEST_OF_LOADING_DATA", it1) }
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
//ls42
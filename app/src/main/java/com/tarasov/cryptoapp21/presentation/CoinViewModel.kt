package com.tarasov.cryptoapp21.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tarasov.cryptoapp21.data.repository.CoinRepositoryImpl
import com.tarasov.cryptoapp21.domain.GetCoinInfoListUseCase
import com.tarasov.cryptoapp21.domain.GetCoinInfoUseCase
import com.tarasov.cryptoapp21.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}
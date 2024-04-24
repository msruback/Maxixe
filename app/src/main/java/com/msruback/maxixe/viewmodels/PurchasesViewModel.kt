package com.msruback.maxixe.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.msruback.maxixe.database.MaxixeDatabase
import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.queries.PurchaseSellerEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PurchasesViewModel(application: Application) : AndroidViewModel(application) {
    private val database = MaxixeDatabase.getDatabase(application.applicationContext)

    private var _purchases: MutableStateFlow<PagingData<PurchaseSellerEvent>> =
        MutableStateFlow(PagingData.empty())
    var purchases = _purchases.asStateFlow()
        private set
    var purchase by mutableStateOf(listOf<PurchaseSellerEvent>())
        private set

    init{
        viewModelScope.launch {
            Pager(
                config = PagingConfig(20, enablePlaceholders = true)
            ){
                database.purchaseDao().pagingSource()
            }.flow.cachedIn(viewModelScope).collect{
                _purchases.value = it
            }
            purchase = listOf()
        }
    }

    fun setPurchase(id: Long?){
        if(id !=null){
           // purchase = database.purchaseDao().select(id);
        }else{
            purchase = listOf()
        }
    }

    fun insertPurchase(purchase: Purchase): Job {
        return viewModelScope.launch(Dispatchers.IO){
            database.purchaseDao().insert(purchase)
        }
    }
}




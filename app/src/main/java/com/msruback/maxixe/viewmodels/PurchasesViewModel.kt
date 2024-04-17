package com.msruback.maxixe.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.msruback.maxixe.database.MaxixeDatabase
import com.msruback.maxixe.database.queries.PurchaseWithSeller
import kotlinx.coroutines.launch

class PurchasesViewModel(application: Application) : AndroidViewModel(application) {
    private val database = MaxixeDatabase.getDatabase(application.applicationContext)

    var purchases by mutableStateOf(listOf<PurchaseWithSeller>())
        private set
    var purchase by mutableStateOf(listOf<PurchaseWithSeller>())
        private set

    init{
        viewModelScope.launch {
            purchases =
                database.purchaseDao().selectAll()
            purchase = listOf()
        }
    }

    fun getPurchases(){
        viewModelScope.launch {
            purchases =
                database.purchaseDao().selectAll()
        }
    }

    fun setPurchase(id: Long?){
        if(id !=null){
           // purchase = database.purchaseDao().select(id);
        }else{
            purchase = listOf()
        }
    }
}




package com.barisgungorr.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.barisgungorr.data.datasource.MealsDataSource
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.ui.retrofit.HomeMealsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MealsRepository @Inject constructor (var mDao: MealsDataSource) {

    suspend fun getMeals(): List<Yemekler> = mDao.getMeals()

    suspend fun addMeals(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) =
        mDao.addMeals(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    suspend fun getBasketMeals() : List<Sepetler>  = mDao.getBasketMeals("BarisGungor")


}

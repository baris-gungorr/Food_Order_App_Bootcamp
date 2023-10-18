package com.barisgungorr.data.entity

import com.google.gson.annotations.SerializedName

data class SepetlerCevap (@SerializedName("sepet_yemekler") var yemekler:List<Sepetler>,
                          @SerializedName("success") var success:Int) {

}
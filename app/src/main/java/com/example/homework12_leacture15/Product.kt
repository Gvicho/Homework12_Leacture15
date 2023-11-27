package com.example.homework12_leacture15

import android.os.Parcel
import android.os.Parcelable

data class Product(val id:Int,
                   val productType:ProductType,
                   val name:String,
                   val desciption:String,
                   val colorList:MutableList<Color_>,
                   val imageList:MutableList<Int>,
                   val prise:Int,
                   var rating:Float,
                   var sold:Int) {

    val type:Int = 1
}

enum class Color_(val colorId: Int){
    WHITE(1),
    BLACK(2),
    RED(3),
    BLUE(4),
    ORANGE(5),
    GREY(6)
}
enum class ProductType(val productName:String){
    VASE("Vase"),
    CHAIR("Chair"),
    SOFA("Sofa"),
    BED("Bed"),
    CLOSET("Closet")
}

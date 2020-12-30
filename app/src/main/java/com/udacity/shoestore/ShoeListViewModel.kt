package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    init {
        _shoes.value = mutableListOf(
            Shoe("Air0",32.0,  "Nike",   "Sport1", mutableListOf("nike1.jpg")),
            Shoe("Air2",33.0,  "Nike",   "Sport2", mutableListOf("nike2.jpg")),
            Shoe("Air3",34.0,  "Adidas", "Sport3", mutableListOf("adidas1.jpg")),
            Shoe("Air4",35.0,  "Adidas", "Sport4", mutableListOf("adidas2.jpg")),
            Shoe("Air5",36.0,  "Puma",   "Sport5", mutableListOf("nike5.jpg")),
            Shoe("Air6",37.0,  "Puma",   "Sport6", mutableListOf("nike6.jpg")),
            Shoe("Air3",34.0,  "Reebok", "Sport3", mutableListOf("reebok1.jpg")),
            Shoe("Air4",35.0,  "Reebok", "Sport4", mutableListOf("reebok2.jpg")),
            Shoe("Air5",36.0,  "Brooks", "Sport5", mutableListOf("brooks1.jpg")),
            Shoe("Air6",37.0,  "Brooks", "Sport6", mutableListOf("brooks2.jpg"))
        )
    }

    fun addNewItem(newShoe: Shoe) {
        Timber.d("addNewItem $newShoe")

        val newList = _shoes.value?.toMutableList()

        newList?.add(newShoe)

        _shoes.value = newList?.toList()
    }
}
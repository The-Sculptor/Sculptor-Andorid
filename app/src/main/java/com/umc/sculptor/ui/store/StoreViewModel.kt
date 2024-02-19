package com.umc.sculptor.ui.store

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.ItemX
import com.umc.sculptor.data.model.remote.store.Stone
import com.umc.sculptor.data.model.remote.store.StoneItem

class StoreViewModel : ViewModel() {
    private val _selectedStatue = MutableLiveData<Stone>()// 선택된 아이템을 저장_ LiveData
    private val _selectedItem = MutableLiveData<Item>()
    private val _checkedListItem = MutableLiveData<ItemX>()
    private var _updateWornItem = MutableLiveData<StoneItem>()


    // 여러 아이템을 저장할 리스트 추가
    var _selectedItemsList = MutableLiveData<MutableList<String>>()
    var _checkedListItemsList = MutableLiveData<MutableList<String>>()//구매list 중 체크한 것들 리스트
    val selectedStatue: LiveData<Stone> get() = _selectedStatue
    val selectedItem: LiveData<Item> get() = _selectedItem
    val selectedListItem: LiveData<ItemX> get() = _checkedListItem//구매list 중 체크한 것

    init {
        _selectedItemsList.value = mutableListOf()
    }
    val selectedItemsList: LiveData<MutableList<String>> get() = _selectedItemsList
    fun addSelectedItemId(itemId: String) {
        _selectedItemsList.value?.add(itemId)
        _selectedItemsList.value = _selectedItemsList.value // LiveData 업데이트를 위한 임시 변경
    }
    fun removeSelectedItemId(itemId: String) {
        _selectedItemsList.value?.remove(itemId)
        _selectedItemsList.value = _selectedItemsList.value // LiveData 업데이트를 위한 임시 변경
    }

    ///
    fun addCheckedListItemId(itemId: String) {
        _checkedListItemsList.value?.add(itemId)
        _checkedListItemsList.value = _checkedListItemsList.value // LiveData 업데이트를 위한 임시 변경
    }
    fun removeCheckedListItemId(itemId: String) {
        _checkedListItemsList.value?.remove(itemId)
        _checkedListItemsList.value =  _checkedListItemsList.value // LiveData 업데이트를 위한 임시 변경
    }
    

    fun updateSelectedStatue(stone: Stone) {// 선택된 조각상 업데이트
        _selectedStatue.value = stone
    }
    fun updatereleasedStatue(stone: Stone) {// 선택 안 된 조각상 업데이트
        _selectedStatue.value = stone
    }

    fun updateSelectedItem_item(item:Item){//선택된 아이템(원석) 업데이트
        _selectedItem.value = item
        item.isSelected = true
        Log.d("viewModel", " ${item.itemName}-> ${item.isSelected}")
    }

    fun updatereleasedItem_item(item:Item) {// 선택된 조각상 업데이트
        _selectedItem.value = item
        item.isSelected = false
    }






}
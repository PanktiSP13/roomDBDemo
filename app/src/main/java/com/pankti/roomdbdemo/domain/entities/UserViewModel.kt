package com.pankti.roomdbdemo.domain.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pankti.roomdbdemo.domain.usecase.UserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel class UserViewModel @Inject constructor(private val useCase: UserDetailUseCase) : ViewModel() {

    private var _userList = MutableLiveData<List<UserDataModel>>(emptyList())
    var userList: LiveData<List<UserDataModel>> = _userList

    init {
        getUserList()
    }

    fun addUser(userDataModel: UserDataModel) {
        viewModelScope.launch {
            useCase.addUser(userDataModel)
            // refresh list
            getUserList()
        }
    }

    private fun getUserList() {
        viewModelScope.launch {
            useCase.getUserList().collect {
                // it will show recently added item on top
                if (it.isNotEmpty()) {
                    if (it.size > 1) _userList.value = it.reversed()
                    else _userList.value = it
                }else _userList.value = emptyList()
            }
        }
    }

    fun updateUserDetail(userDataModel: UserDataModel) {
        viewModelScope.launch {
            useCase.updateUser(userDataModel)
            // refresh list
            getUserList()
        }
    }


    fun deleteUser(userDataModel: UserDataModel) {
        viewModelScope.launch {
            useCase.deleteUser(userDataModel)
            // refresh list
            getUserList()
        }
    }

}
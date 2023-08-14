package com.pankti.roomdbdemo.domain.usecase

import com.pankti.roomdbdemo.domain.entities.UserDataModel
import com.pankti.roomdbdemo.domain.repo.UserDetailRepository
import kotlinx.coroutines.flow.Flow

class UserDetailUseCase(private val repository: UserDetailRepository) {


    // modify response here if you want

    suspend fun getUserList(): Flow<List<UserDataModel>> {
        return repository.getAllUsers()
    }

    suspend fun addUser(userDataModel: UserDataModel) = repository.addUser(userDataModel)

    suspend fun updateUser(userDataModel: UserDataModel) = repository.updateUserDetail(userDataModel)

    suspend fun deleteUser(userDataModel: UserDataModel) = repository.deleteUser(userDataModel)

    suspend fun getUserById(id : Long) = repository.getUserById(id)
}
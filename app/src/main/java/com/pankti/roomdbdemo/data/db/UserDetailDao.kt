package com.pankti.roomdbdemo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pankti.roomdbdemo.domain.entities.UserDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetail : UserDataModel)

    @Update
    suspend fun updateUserDetail(userDetail: UserDataModel)

    @Query("select * from `user_detail`")
    fun getAllUsers() : Flow<List<UserDataModel>>

    @Query("select * from `user_detail` where name=:userName")
    fun getUserDetailByName(userName:String) : List<UserDataModel>

    @Query("update `user_detail` set number=:userNumber")
    suspend fun updateUserNumber(userNumber : String)

    @Query("select * from `user_detail` where id=:userId")
    suspend fun getUserById(userId : Long) : UserDataModel?

    @Delete
    suspend fun deleteUser(userDetail: UserDataModel)
}
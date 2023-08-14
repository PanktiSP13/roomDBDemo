package com.pankti.roomdbdemo.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_detail")
data class UserDataModel(
    @PrimaryKey var id : Long = 0,
    var name: String = "",
    var number: String = "",
    var emailId : String = "") {}
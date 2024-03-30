package com.example.projectsagarbhat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveData(myEntity : MyEntity)

    //Only if you want to write a customized query
    @Query("select * from MyEntity")
    fun readData() : List<MyEntity>

//    @Query("select * from MyEntity")
    @Query("SELECT * FROM MyEntity WHERE name_column LIKE :username AND pass_column LIKE :password")
    fun checkUser(username:String, password:String) : List<MyEntity>
//    abstract fun checkUser(username: String, password: String, function: () -> Boolean)


}
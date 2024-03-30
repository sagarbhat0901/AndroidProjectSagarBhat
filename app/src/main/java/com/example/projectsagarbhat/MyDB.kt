package com.example.projectsagarbhat

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyEntity::class],version = 5)
abstract  class MyDB : RoomDatabase(){
    abstract  fun myDao() : MyDao
}


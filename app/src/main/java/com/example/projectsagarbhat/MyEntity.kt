package com.example.projectsagarbhat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_column")
    var myId : Int = 0

    @ColumnInfo(name = "name_column")
    var myUsername : String = ""

    @ColumnInfo(name = "pass_column")
    var myPassword : String = ""

    @ColumnInfo(name = "age_column")
    var myAge : String = ""

    @ColumnInfo(name = "fullname_column")
    var myFullName : String = ""


}
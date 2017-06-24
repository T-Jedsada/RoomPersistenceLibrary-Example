package pondthaitay.roompersistencelibrary.example

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(@PrimaryKey(autoGenerate = true) var id: Int? = null,
                         @ColumnInfo(name = "student_code") var code: Long? = null,
                         var firstName: String? = null,
                         var lastName: String? = null,
                         var email: String? = null,
                         var address: String? = null)
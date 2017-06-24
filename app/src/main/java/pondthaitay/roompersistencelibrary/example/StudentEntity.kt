package pondthaitay.roompersistencelibrary.example

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "student")
class StudentEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var code: Long? = null
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var address: String? = null
}

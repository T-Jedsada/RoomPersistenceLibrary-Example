package pondthaitay.roompersistencelibrary.example

import android.arch.persistence.room.ColumnInfo

data class AddressEntity(var street: String? = null,
                         var state: String? = null,
                         var city: String? = null,
                         var tambon : String? = null,
                         @ColumnInfo(name = "post_code") var postCode: Long? = 0)
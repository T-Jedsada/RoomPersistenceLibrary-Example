package pondthaitay.roompersistencelibrary.example.persistence

import android.arch.persistence.room.ColumnInfo

data class AddressModel(var street: String? = null,
                        var state: String? = null,
                        var city: String? = null,
                        @ColumnInfo(name = "post_code") var postCode: Long? = 0)
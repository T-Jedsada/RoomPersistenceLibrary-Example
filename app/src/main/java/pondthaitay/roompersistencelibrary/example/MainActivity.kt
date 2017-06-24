package pondthaitay.roompersistencelibrary.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appDatabase = AppDatabase.getAppDatabase(this)

        val jedsadaAddress = AddressEntity()
        jedsadaAddress.city = "Muang"
        jedsadaAddress.postCode = 50200
        jedsadaAddress.street = "Canal Road"
        jedsadaAddress.state = "Chiang Mai"

        val jedsada = StudentEntity()

        jedsada.code = 55021744
        jedsada.email = "jedsada@gmail.com"
        jedsada.firstName = "Jedsda"
        jedsada.lastName = "Tiwongvorakul"
        jedsada.address = jedsadaAddress

        Flowable.fromCallable { appDatabase.studentDao().insertStudent(jedsada) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    appDatabase.studentDao().getStudentAll()
                            .subscribe({
                                it.forEach {
                                    Log.e("POND", it.id.toString() + "\t" + it.firstName + "\n" +
                                            it.address?.state)
                                }
                            }, { it.printStackTrace() })
                }, { it.printStackTrace() })
    }
}

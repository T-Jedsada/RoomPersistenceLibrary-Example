package pondthaitay.roompersistencelibrary.example

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : LifecycleActivity() {

    val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appDatabase = AppDatabase.getAppDatabase(this)

        val scoopsAddress = AddressModel()
        scoopsAddress.city = "Muang"
        scoopsAddress.postCode = 50200
        scoopsAddress.street = "Canal Road"
        scoopsAddress.state = "Chiang Mai"

        val scoopsStudent = StudentEntity()
        scoopsStudent.code = 123456789
        scoopsStudent.email = "20scoops@gmail.com"
        scoopsStudent.firstName = "20scoops"
        scoopsStudent.lastName = "CNX"
        scoopsStudent.address = scoopsAddress

        appDatabase.studentDao().getStudentAll()
                .observe(this, Observer {
                    it?.forEach { Log.d(TAG, it.toString()) }
                })

        Flowable.fromCallable { appDatabase.studentDao().insertStudent(scoopsStudent) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { Log.d(TAG, "insert complete") }
    }
}

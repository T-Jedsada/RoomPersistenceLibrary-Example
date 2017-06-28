package pondthaitay.roompersistencelibrary.example

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import pondthaitay.roompersistencelibrary.example.persistence.AddressModel
import pondthaitay.roompersistencelibrary.example.persistence.StudentEntity

class MainActivity : LifecycleActivity() {

    val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val mMainViewModelFactory = Injection.provideMainViewModelFactory(this)
        val mMainViewModel = ViewModelProviders.of(this, mMainViewModelFactory)
                .get(MainViewModel::class.java)

        mMainViewModel.getStudentAllLiveDta().observe(this, Observer {
            it?.forEach { Log.d(TAG, "$it") }
        })

        btn_insert_student.setOnClickListener {
            mMainViewModel.insertStudent(scoopsStudent)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe { Log.d(TAG, "insert complete") }
        }
    }
}

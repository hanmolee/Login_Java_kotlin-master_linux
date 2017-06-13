package com.iot.login_java_kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

//리눅스환경에서 시작 6/12
class LoginActivity : AppCompatActivity() {

    val SUBMIT : Int = 1000
    val PASSWORD : String = "12"
    val REQUEST_CODE : Int = 100
    var RESULT_CODE : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        submitBtn.setOnClickListener {

            val Input_PW = fingerprintInput.text//입력받은 비밀번호를 변수에 저장

            if (PASSWORD.equals(Input_PW.toString())){//입력한 비밀번호가 맞다면

                var intnet = Intent(applicationContext as Context, MenuActivity::class.java)
                var data : ParcelData = ParcelData(12, "Power on!!")
                intnet.putExtra("data", data)
                startActivityForResult(intnet, REQUEST_CODE)


                if (RESULT_CODE == 888){

                    var intnet = Intent(applicationContext as Context, MainActivity::class.java)
                    startActivity(intnet)
                }

                /*val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val menufragment = fragmentManager.findFragmentById(R.id.fragment_one) as MenuFragment
                fragmentTransaction.add(menufragment as Fragment,"")

                fragmentTransaction.commit()*/

            }
            else
                toast("Try Again")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        this.RESULT_CODE = resultCode

        if (data != null){
            toast("requestCode : " + requestCode + "\n" + "resultCode : "+resultCode)
        }
    }
}

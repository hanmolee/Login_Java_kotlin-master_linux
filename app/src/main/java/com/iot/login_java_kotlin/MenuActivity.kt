package com.iot.login_java_kotlin

import Fragment.FragmentOne
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast

class MenuActivity : AppCompatActivity() {


    private val REQUEST_CODE: Int = 888
    private val repeat : Int = 200000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var intent = intent
        var bundle : Bundle = intent.extras
        var data : ParcelData = bundle.getParcelable("data")

        toast("현재 상태 : "+data._msg + "code" + data._poweron)

        if (data._poweron == 12){//지문인식이 된 경우

            var intent = Intent()
            setResult(REQUEST_CODE,intent)

            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment1 = FragmentOne()
            fragmentTransaction.replace(R.id.power, fragment1)
            fragmentTransaction.commit()

            //시동이 켜졌다는것을 잠깐 보여주기위해 Delay
            var handle : RequestHandler = RequestHandler()
            handle.sendEmptyMessageDelayed(0, 1000)

        }



        /*Thread.sleep(5000)
        */


    }

    inner class RequestHandler : Handler(){

        override fun handleMessage(msg : Message){

            for (i in 1..3){

                try {
                    Thread.sleep(1000)
                }
                catch (ex : InterruptedException)
                {}
            }
            finish()
        }

    }

}


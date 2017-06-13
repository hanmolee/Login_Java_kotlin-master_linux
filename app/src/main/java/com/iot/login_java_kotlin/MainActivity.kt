package com.iot.login_java_kotlin

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var myJson : String? = null

    val RESULT : String = "result"
    val BANK : String = "bank"
    val MYACCOUNT : String = "myaccount"

    val _bank : BankAccount = BankAccount(5000)
    val _myaccount : BankAccount = BankAccount(50000)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData("http://118.91.118.27/CarCat/select_bank.php")

        goBack.setOnClickListener {
            val intent = Intent(applicationContext as Context, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        btnToBank.setOnClickListener {
            transferMoney(_myaccount, _bank)

            finish()
        }

        refreshBalances()

    }



    private fun transferMoney(from: BankAccount, to: BankAccount) {
        val amountString = edtAmount.getText().toString()
        val amount = Integer.parseInt(amountString)
        from.transfermoney(to, amount)

        refreshBalances()
    }

    private fun refreshBalances() {
        try {

            val jsonObj: JSONObject = JSONObject(myJson)
            var jsonArray : JSONArray = jsonObj.getJSONArray(RESULT)

            jsonArray.let {
                for (i in 0.. it.length()) {//아 for문이 왜 안되는지 모르겠다 ㅠㅠ

                    var c: JSONObject = jsonArray.getJSONObject(i)

                    Log.d("d", c.toString())

                    txvBank.text = c.getString(BANK)
                    txvMyAccount.text = c.getString(MYACCOUNT)
                }
            }

        }

        catch (e: JSONException){
            Log.d("d", e.toString())
        }
    }


    fun getData(url : String) {
        class showAccount : AsyncTask<String, Void, String>() {

            override fun doInBackground(vararg params: String?): String? {

                var uri = params[0] as String
                var bufferedReader : BufferedReader

                try {
                    var url : URL = URL(uri)
                    var con : HttpURLConnection = url.openConnection() as HttpURLConnection
                    var sb : StringBuilder = StringBuilder()

                    bufferedReader = BufferedReader(InputStreamReader(con.inputStream))

                    var json : String? = null

                    while ({json = bufferedReader.readLine()} != null){
                        sb.append(json + "\n")
                    }

                    return sb.toString().trim()

                }
                catch (e : Exception){
                    return null

                }

            }

            override fun onPostExecute(result: String?) {
                myJson = result as String
                refreshBalances()
            }
        }

        var data : showAccount = showAccount()
        data.execute(url)
    }

}


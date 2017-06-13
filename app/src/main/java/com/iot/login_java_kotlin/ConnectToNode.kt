package com.iot.login_java_kotlin

import android.os.AsyncTask
import android.renderscript.ScriptGroup
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLConnection
import java.net.URLEncoder

/**
 * Created by hanmo on 17. 6. 12.
 */

class ConnectToNode(var bank : String, var money : String) : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String?): String {

        try {
            var bank : String = params[0] as String
            var money : String = params[1] as String

            val link : String = ""

            var data : String = URLEncoder.encode("bank", "UTF-8") + "=" + URLEncoder.encode(bank, "UTF-8")
            data += "&" + URLEncoder.encode("money", "UTF-8") + "=" + URLEncoder.encode(money, "UTF-8")

            val url : URL = URL(link)
            val con : URLConnection = url.openConnection()

            con.doOutput
            var wr : OutputStreamWriter = OutputStreamWriter(con.getOutputStream())

            wr.write(data)
            wr.flush()

            var reader : BufferedReader = BufferedReader(
                    InputStreamReader(con.getInputStream())
            )

            var sb : StringBuilder = StringBuilder()
            var line : String? = null


            return sb.toString()

        }
        catch (e : Exception){

            return e.toString()

        }

    }

    override fun onPreExecute() {
        super.onPreExecute()
    }


    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
    }

}

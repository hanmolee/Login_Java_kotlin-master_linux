package com.iot.login_java_kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val _bank : BankAccount = BankAccount(5000)
    val _myaccount : BankAccount = BankAccount(50000)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        txvBank.text = _bank.getBalance().toString()
        txvMyAccount.text = _myaccount.getBalance().toString()
    }

}

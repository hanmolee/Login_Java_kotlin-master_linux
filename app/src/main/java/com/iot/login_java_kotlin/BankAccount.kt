package com.iot.login_java_kotlin

/**
 * Created by User on 2017-06-02.
 */


class BankAccount(balance : Int){


    val FEE : Int = 1000
    var _balance : Int = 0
    private set

    fun getBalance() : Int{
        return _balance
    }

    init{
        this._balance = balance
    }

    fun transfermoney(other : BankAccount, amount : Int){

        if (this.equals(other))
            return

        val amountWithFee = amount + if (isVip) 0 else FEE//만원 이상 있다면 수수료 면제하도록

        if (_balance < amountWithFee)
            return

        _balance -= amountWithFee

        other._balance += amount

    }

    private val isVip : Boolean//만원 이상 있다면 수수료 면제하도록
    get() = _balance >= 10000


}
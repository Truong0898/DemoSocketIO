package com.example.demosocketio.ui

class MyConst {
    companion object {
        const val ITEM_SEND = 0
        const val ITEM_RECEIVED = 1
    }
}

object Const{
    var DEBUG = true
}

fun Any.logcat(
    tag: String? = null,
    msg: () -> String
) {
    if (!Const.DEBUG) return
    val name = this.javaClass.enclosingMethod?.name?.plus(":") ?: ""
    println("${tag ?: "Interactive"} --> $name${msg.invoke()}")
}
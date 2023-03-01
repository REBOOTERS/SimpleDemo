package com.example.tencenter.androiddemo

object TestAll {

    private var aaa:AAA ?= null

    @JvmStatic
    fun test() {
        println("-----------------------")
        aaa = AAA.C
    }

    fun useAAA(param: AAA?) {
        when (param) {
            AAA.C -> {
               val time =  OnlyOne.getInstance().elapsedRealtime
                if (time > 100) {
                    useAAA(AAA.A)
                }
            }
            AAA.A -> {
                val time = OnlyOne.getInstance().time
                if (time > 100) {
                    useAAA(AAA.D)
                }
            }
            else -> {
                val douban = DoubanBean()
                if (douban.id.isNullOrEmpty()) {
                    println("id is null")
                }
            }
        }
    }

    enum class AAA {
        A, B, C, D
    }
}
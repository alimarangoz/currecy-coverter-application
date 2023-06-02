package com.alimarangoz.currencyconverter.data

data class Currency(
    val name:String,
    val forexBuying:String,
    val forexSelling:String,
    val banknoteBuying:String,
    val banknoteSelling:String,
    val date:String,
    val unit:String,
)

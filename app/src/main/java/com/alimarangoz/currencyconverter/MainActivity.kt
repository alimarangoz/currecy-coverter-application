package com.alimarangoz.currencyconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class MainActivity : AppCompatActivity() {

    lateinit var forexBuyingTxt : MaterialAutoCompleteTextView
    lateinit var forexSellingTxt : MaterialAutoCompleteTextView
    lateinit var banknoteBuyingTxt : MaterialAutoCompleteTextView
    lateinit var banknoteSellingTxt : MaterialAutoCompleteTextView
    lateinit var dateTxt : TextView
    lateinit var unitTxt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forexBuyingTxt = findViewById(R.id.forex_buying_txt)
        forexSellingTxt = findViewById(R.id.forex_selling_txt)
        banknoteBuyingTxt = findViewById(R.id.banknote_buying_txt)
        banknoteSellingTxt = findViewById(R.id.banknote_selling_txt)
        dateTxt = findViewById(R.id.date)
        unitTxt = findViewById(R.id.unit)

        val autoCompleteTextView =  findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val currencies = resources.getStringArray(R.array.currencies)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item,currencies)
        autoCompleteTextView.setAdapter(arrayAdapter)

        val initialCurrency = currencies[0]
        Thread {
            val xml = CurrencyXml()
            val arr = xml.getCurrencyXml(initialCurrency)
            runOnUiThread {
                if (arr.isNotEmpty()) {
                    unitTxt.setText("Unit: " + arr[0].unit)
                    dateTxt.setText("Tarih: " + arr[0].date)
                    forexBuyingTxt.setText(arr[0].forexBuying + " TL")
                    forexSellingTxt.setText(arr[0].forexSelling + " TL")
                    banknoteBuyingTxt.setText(arr[0].banknoteBuying + " TL")
                    banknoteSellingTxt.setText(arr[0].banknoteSelling + " TL")
                }
            }
        }.start()

        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Thread {
                val xml = CurrencyXml()
                val arr = xml.getCurrencyXml(selectedItem)
                runOnUiThread {
                    if (arr.isNotEmpty()) {

                        unitTxt.setText("Unit: " + arr[0].unit)
                        dateTxt.setText("Tarih: " + arr[0].date)
                        forexBuyingTxt.setText(arr[0].forexBuying + " TL")
                        forexSellingTxt.setText(arr[0].forexSelling + " TL")
                        banknoteBuyingTxt.setText(arr[0].banknoteBuying + " TL")
                        banknoteSellingTxt.setText(arr[0].banknoteSelling + " TL")

                        if(arr[0].banknoteBuying.isEmpty() && arr[0].banknoteBuying.isEmpty()){
                            banknoteBuyingTxt.setText("-")
                            banknoteSellingTxt.setText("-")
                        }
                    }
                }
            }.start()
        }


    }

    override fun onResume() {
        super.onResume()
        val autoCompleteTextView =  findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val currencies = resources.getStringArray(R.array.currencies)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item,currencies)
        autoCompleteTextView.setAdapter(arrayAdapter)
    }
}
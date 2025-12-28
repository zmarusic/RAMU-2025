package ba.sum.fsre.kalkulator2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    var number : String? = null

    var firstNumber : Double = 0.0
    var lastNumber : Double = 0.0

    var status : String? = null
    var operator : Boolean = false

    val myFormatter = DecimalFormat("######.######")

    var history : String = ""
    var currentResult : String = ""

    var dotControl : Boolean = true
    var buttonEqualsControl : Boolean = false

    lateinit var sharedPreferences : SharedPreferences
    private lateinit var textViewResult: TextView
    private lateinit var textViewHistory: TextView

    private lateinit var btnZero: Button
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button

    private lateinit var btnAC: Button
    private lateinit var btnDel: Button
    private lateinit var btnDivide: Button
    private lateinit var btnMulti: Button
    private lateinit var btnMinus: Button
    private lateinit var btnPlus: Button
    private lateinit var btnEqual: Button
    private lateinit var btnDot: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.textViewResult)
        textViewHistory = findViewById(R.id.textViewHistory)

        btnZero = findViewById(R.id.btnZero)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)

        btnAC = findViewById(R.id.btnAC)
        btnDel = findViewById(R.id.btnDel)
        btnDivide = findViewById(R.id.btnDivide)
        btnMulti = findViewById(R.id.btnMulti)
        btnMinus = findViewById(R.id.btnMinus)
        btnPlus = findViewById(R.id.btnPlus)
        btnEqual = findViewById(R.id.btnEqual)
        btnDot = findViewById(R.id.btnDot)

        //val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // 2) init
        textViewResult.text = "0"


        //super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}

        textViewResult.text = "0"

        btnZero.setOnClickListener {
            onNumberClicked("0")
        }
        btnOne.setOnClickListener {
            onNumberClicked("1")
        }
        btnTwo.setOnClickListener {
            onNumberClicked("2")
        }
        btnThree.setOnClickListener {
            onNumberClicked("3")
        }
        btnFour.setOnClickListener {
            onNumberClicked("4")
        }
        btnFive.setOnClickListener {
            onNumberClicked("5")
        }
        btnSix.setOnClickListener {
            onNumberClicked("6")
        }
        btnSeven.setOnClickListener {
            onNumberClicked("7")
        }
        btnEight.setOnClickListener {
            onNumberClicked("8")
        }
        btnNine.setOnClickListener {
            onNumberClicked("9")
        }

        btnAC.setOnClickListener {

            onButtonACClicked()

        }
        btnDel.setOnClickListener {

            number?.let {

                if (it.length == 1){
                    onButtonACClicked()
                }else{
                    number = it.substring(0,it.length-1)
                    textViewResult.text = number
                    dotControl = !number!!.contains(".")
                }

            }

        }
        btnDivide.setOnClickListener {

            history = textViewHistory.text.toString()
            currentResult = textViewResult.text.toString()
            textViewHistory.text = history.plus(currentResult).plus("/")

            if (operator){
                when(status){

                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = textViewResult.text.toString().toDouble()

                }
            }

            status = "division"
            operator = false
            number = null
            dotControl = true

        }
        btnMulti.setOnClickListener {

            history = textViewHistory.text.toString()
            currentResult = textViewResult.text.toString()
            textViewHistory.text = history.plus(currentResult).plus("*")

            if (operator){
                when(status){

                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = textViewResult.text.toString().toDouble()

                }
            }

            status = "multiplication"
            operator = false
            number = null
            dotControl = true

        }
        btnMinus.setOnClickListener {

            history = textViewHistory.text.toString()
            currentResult = textViewResult.text.toString()
            textViewHistory.text = history.plus(currentResult).plus("-")

            if (operator){
                when(status){

                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = textViewResult.text.toString().toDouble()

                }
            }

            status = "subtraction"
            operator = false
            number = null
            dotControl = true

        }
        btnPlus.setOnClickListener {

            history = textViewHistory.text.toString()
            currentResult = textViewResult.text.toString()
            textViewHistory.text = history.plus(currentResult).plus("+")

            if (operator){
                when(status){

                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = textViewResult.text.toString().toDouble()

                }
            }

            status = "addition"
            operator = false
            number = null
            dotControl = true

        }
        btnEqual.setOnClickListener {

            history = textViewHistory.text.toString()
            currentResult = textViewResult.text.toString()


            if (operator){
                when(status){

                    "multiplication" -> multiply()
                    "division" -> divide()
                    "subtraction" -> minus()
                    "addition" -> plus()
                    else -> firstNumber = textViewResult.text.toString().toDouble()

                }

                textViewHistory.text = history.plus(currentResult).plus("=").plus(textViewResult.text.toString())

            }

            operator = false
            dotControl = true
            buttonEqualsControl = true

        }
        btnDot.setOnClickListener {

            if (dotControl){
                number = if (number == null){
                    "0."
                }else if (buttonEqualsControl){

                    if (textViewResult.text.toString().contains(".")){
                        textViewResult.text.toString()
                    }else{
                        textViewResult.text.toString().plus(".")
                    }

                }
                else{
                    "$number."
                }

                textViewResult.text = number
            }

            dotControl = false
        }

    }

    fun onButtonACClicked(){

        number = null
        status = null
        textViewResult.text = "0"
        textViewHistory.text = ""
        firstNumber = 0.0
        lastNumber = 0.0
        dotControl = true
        buttonEqualsControl = false

    }

    fun onNumberClicked(clickedNumber : String){

        if (number == null){
            number = clickedNumber
        }else if (buttonEqualsControl){

            number = if (dotControl){
                clickedNumber
            }else{
                textViewResult.text.toString().plus(clickedNumber)
            }

            firstNumber = number!!.toDouble()
            lastNumber = 0.0
            status = null
            textViewHistory.text = ""

        }
        else{
            number += clickedNumber
        }

        textViewResult.text = number

        operator = true
        buttonEqualsControl = false

    }

    fun plus(){

        lastNumber = textViewResult.text.toString().toDouble()
        firstNumber += lastNumber
        textViewResult.text = myFormatter.format(firstNumber)

    }

    fun minus(){

        lastNumber = textViewResult.text.toString().toDouble()
        firstNumber -= lastNumber
        textViewResult.text = myFormatter.format(firstNumber)

    }

    fun multiply(){

        lastNumber = textViewResult.text.toString().toDouble()
        firstNumber *= lastNumber
        textViewResult.text = myFormatter.format(firstNumber)

    }

    fun divide(){

        lastNumber = textViewResult.text.toString().toDouble()

        if (lastNumber == 0.0){
            Toast.makeText(applicationContext,"The divisor cannot be zero", Toast.LENGTH_LONG).show()
        }else{
            firstNumber /= lastNumber
            textViewResult.text = myFormatter.format(firstNumber)
        }
    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = this.getSharedPreferences("Dark Theme", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("switch",false)
        /*
        if (isDarkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

         */

    }

    override fun onPause() {
        super.onPause()

        sharedPreferences = this.getSharedPreferences("calculations", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val resultToSave = textViewResult.text.toString()
        val historyToSave = textViewHistory.text.toString()
        val numberToSave = number
        val statusToSave = status
        val operatorToSave = operator
        val dotToSave = dotControl
        val equalToSave = buttonEqualsControl
        val firstNumberToSave = firstNumber.toString()
        val lastNumberToSave = lastNumber.toString()

        editor.apply{
            putString("result",resultToSave)
            putString("history", historyToSave)
            putString("number",numberToSave)
            putString("status",statusToSave)
            putBoolean("operator", operatorToSave)
            putBoolean("dot", dotToSave)
            putBoolean("equal", equalToSave)
            putString("first", firstNumberToSave)
            putString("last", lastNumberToSave)
            apply()
        }

    }

    override fun onStart() {
        super.onStart()

        sharedPreferences = this.getSharedPreferences("calculations", Context.MODE_PRIVATE)

        textViewResult.text = sharedPreferences.getString("result","0")
        textViewHistory.text = sharedPreferences.getString("history","")

        number = sharedPreferences.getString("number",null)
        status = sharedPreferences.getString("status",null)
        operator = sharedPreferences.getBoolean("operator",false)
        dotControl = sharedPreferences.getBoolean("dot",true)
        buttonEqualsControl = sharedPreferences.getBoolean("equal",false)
        firstNumber = sharedPreferences.getString("first","0.0")!!.toDouble()
        lastNumber = sharedPreferences.getString("last","0.0")!!.toDouble()

    }
}
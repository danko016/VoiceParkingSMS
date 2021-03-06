package com.example.dev.parking

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class Main2Activity() : AppCompatActivity() {

    var activityVehicleText: TextView? = null
    var activityPlateText: TextView? = null
    var addVehicleDialog: AddVehicleDialog? = null
    var confirmDialog: ConfirmDialog? = null

    var valuePlate: String = ""
    var valueVehicle: String = ""

    var chosenVehicleText: String = ""
    var chosenPlateText: String = ""

    private val VOICE_RECOGNITION_REQUEST_CODE = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result: ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            TVreconizedText.text = result[0]
            Log.d("tag", "textView " + TVreconizedText.text)
        }

        try {
            when (TVreconizedText.text.toString().toLowerCase()) {
                "zone 1" -> {
                    prepareToSend()
                    BTNSendSMS?.setBackgroundColor(Color.BLUE)
                }

                "zone 1:30" -> {
                    prepareToSend()
                    BTNSendSMSZone1Half?.setBackgroundColor(Color.BLUE)
                }

                "zone 2" -> {
                    prepareToSend()
                    BTNSendSMSZone2?.setBackgroundColor(Color.BLUE)
                }
                "zone 3" -> {
                    prepareToSend()
                    BTNSendSMSZone3?.setBackgroundColor(Color.BLUE)
                }
                "zone 4" -> {
                    prepareToSend()
                    BTNSendSMSZone4?.setBackgroundColor(Color.BLUE)
                }
                "zone 5" -> {
                    prepareToSend()
                    BTNSendSMSZone5?.setBackgroundColor(Color.BLUE)
                }
                "zone 6" -> {
                    prepareToSend()
                    BTNSendSMSZone6?.setBackgroundColor(Color.BLUE)
                }
                else -> Toast.makeText(applicationContext, "Unexisting zone", Toast.LENGTH_SHORT).show()

            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    fun startRecognition() {
        val intent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        getSavedVehicle()
        getSavedPlate()

        activityVehicleText = findViewById(R.id.TVGetVehicle) as TextView
        activityPlateText = findViewById(R.id.TVGetPlate) as TextView

        recognitionBTN.onClick {
            startRecognition()
        }

        TVChooseVehicle.onClick {
            addVehicleDialog = AddVehicleDialog(this, this)
            addVehicleDialog?.show()
        }

        BTNSendSMS?.onClick {
            prepareToSend()
            BTNSendSMS?.setBackgroundColor(Color.BLUE)
        }

        BTNSendSMSZone1Half?.onClick {
            prepareToSend()
            BTNSendSMSZone1Half?.setBackgroundColor(Color.BLUE)
        }

        BTNSendSMSZone2?.onClick {
            prepareToSend()
            BTNSendSMSZone2?.setBackgroundColor(Color.BLUE)
        }

        BTNSendSMSZone3?.onClick {
            prepareToSend()
            BTNSendSMSZone3?.setBackgroundColor(Color.BLUE)
        }

        BTNSendSMSZone4?.onClick {
            prepareToSend()
            BTNSendSMSZone4?.setBackgroundColor(Color.BLUE)
        }

        BTNSendSMSZone5?.onClick {
            prepareToSend()
            BTNSendSMSZone5?.setBackgroundColor(Color.BLUE)
        }

        BTNSendSMSZone6?.onClick {
            prepareToSend()
            BTNSendSMSZone6?.setBackgroundColor(Color.BLUE)
        }

        BTNdeleteVehicle?.onClick {
            TVGetVehicle.text = ""
            TVGetPlate.text = ""
            BTNdeleteVehicle?.setTextColor(Color.BLACK)
        }
    }

    val phoneZone1: String = "098268804"
    val phoneZone2: String = "098268804"
    val phoneZone3: String = "098268804"
    val phoneZone4: String = "098268804"
    val phoneZone5: String = "098268804"
    val phoneZone6: String = "098268804"

    fun prepareToSend() {
        saveVehicle()
        savePlate()
        confirmDialog = ConfirmDialog(this, this)
        confirmDialog?.show()
    }

    fun setTimer(button: Button) {
        object : CountDownTimer(6000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                TVCount.text = ("Seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                TVCount.text = ("Parking elapsed")
                button.setBackgroundColor(Color.RED)
            }

        }.start()
    }

    fun setTimerHalf(button: Button) {
        object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                TVCount.text = ("Seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                TVCount.text = ("Parking elapsed")
                button.setBackgroundColor(Color.RED)
            }

        }.start()
    }

    fun sendSMS(number: String, message: String) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(number, null, message, null, null)
            Toast.makeText(applicationContext, "Message sent", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun savePlate() {
        chosenPlateText = activityPlateText?.text.toString()
        Log.d("tag", "plate " + chosenPlateText)
        val editor = getSharedPreferences("prefs_n", MODE_PRIVATE).edit()
        editor.putString("name", chosenPlateText)
        editor.apply()
    }

    fun saveVehicle() {
        chosenVehicleText = activityVehicleText?.text.toString()
        Log.d("tag", "vehicle " + chosenVehicleText)
        val editorVehicle = getSharedPreferences("prefs_vehicle", MODE_PRIVATE).edit()
        editorVehicle.putString("name", chosenVehicleText)
        editorVehicle.apply()
    }

    fun getSavedVehicle() {
        val prefsVehicle = getSharedPreferences("prefs_vehicle", MODE_PRIVATE)
        valueVehicle = prefsVehicle.getString("text_veh", chosenVehicleText)
        val vehicle = prefsVehicle.getString("name", "No name defined")
        TVGetVehicle.text = vehicle
    }

    fun getSavedPlate() {
        val prefsPlate = getSharedPreferences("prefs_n", MODE_PRIVATE)
        valuePlate = prefsPlate.getString("text", chosenPlateText)
        val plate = prefsPlate.getString("name", "No name defined")
        TVGetPlate.text = plate
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}




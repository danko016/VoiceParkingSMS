package com.example.dev.parking

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.SmsManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity() : AppCompatActivity() {

    var activityVehicleText: TextView? = null
    var activityPlateText: TextView? = null
    var addVehicleDialog: AddVehicleDialog? = null

    var valuePlate: String = ""
    var valueVehicle: String = ""

    var chosenVehicleText: String = ""
    var chosenPlateText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        getSavedVehicle()
        getSavedPlate()

        activityVehicleText = findViewById(R.id.TVGetVehicle) as TextView
        activityPlateText = findViewById(R.id.TVGetPlate) as TextView

        TVChooseVehicle.onClick {
            addVehicleDialog = AddVehicleDialog(this, this)
            addVehicleDialog?.show()
        }

        BTNSendSMS?.onClick {
            saveVehicle()
            savePlate()
            sendSMS(phoneZone1)
            BTNSendSMS?.setTextColor(Color.RED)

        }

        BTNSendSMSZone2?.onClick {
            saveVehicle()
            savePlate()
            sendSMS(phoneZone2)
            BTNSendSMSZone2?.setTextColor(Color.RED)
        }

        BTNSendSMSZone3?.onClick {
            saveVehicle()
            savePlate()
            sendSMS(phoneZone3)
            BTNSendSMSZone3?.setTextColor(Color.RED)
        }

        BTNSendSMSZone4?.onClick {
            saveVehicle()
            savePlate()
            sendSMS(phoneZone4)
            BTNSendSMSZone4?.setTextColor(Color.RED)
        }

        BTNSendSMSZone5?.onClick {
            saveVehicle()
            savePlate()
            sendSMS(phoneZone5)
            BTNSendSMSZone5?.setTextColor(Color.RED)
        }

        BTNSendSMSZone6?.onClick {
            saveVehicle()
            savePlate()
            sendSMS(phoneZone6)
            BTNSendSMSZone6?.setTextColor(Color.RED)
        }

        BTNdeleteVehicle?.onClick {
            TVGetVehicle.text = ""
            TVGetPlate.text = ""
            BTNdeleteVehicle?.setTextColor(Color.BLACK)
        }
    }

    val phoneZone1: String = "700101"
    val phoneZone2: String = "700102"
    val phoneZone3: String = "700103"
    val phoneZone4: String = "700104"
    val phoneZone5: String = "700105"
    val phoneZone6: String = "700106"

    fun sendSMS(number: String) {
        val message: String = chosenPlateText
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
}




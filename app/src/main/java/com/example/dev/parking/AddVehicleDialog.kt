package com.example.dev.parking

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.mcxiaoke.koi.ext.onClick

/**
 * Created by dev on 14.10.16..
 */
class AddVehicleDialog(context: Context, val activity: Main2Activity): Dialog(context){

    var vehicleET: EditText? = null
    var plateET: EditText? = null
    var cancelBTN : Button? = null
    var addBTN : Button? = null
    var vehicleString: String = ""
    var plateString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicle_dialog_layout)

        vehicleET = findViewById(R.id.ETEnterCarName) as EditText
        plateET = findViewById(R.id.ETEnterPlate) as EditText
        cancelBTN = findViewById(R.id.BTNCancelAdd) as Button
        addBTN = findViewById(R.id.BTNAddVehicleAdd) as Button

        cancelBTN?.onClick {
            this.dismiss()
        }

        addBTN?.onClick {
            vehicleString = vehicleET?.text.toString()
            plateString = plateET?.text.toString()
            activity.activityVehicleText?.text = vehicleString
            activity.activityPlateText?.text = plateString
            this.dismiss()
        }
    }

}
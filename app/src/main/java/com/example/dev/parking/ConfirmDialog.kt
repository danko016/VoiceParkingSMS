package com.example.dev.parking

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_main2.*

/**
 * Created by dev on 28.10.16..
 */
class ConfirmDialog(context: Context, val activity: Main2Activity) : Dialog(context) {

    var yesButton: Button? = null
    var noButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        this.window?.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.string.dialogTitle)
        setContentView(R.layout.confirm_dialog_layout)

        val voice: String = activity.TVreconizedText.text.toString().toLowerCase()

        yesButton = findViewById(R.id.BTNYes) as Button
        noButton = findViewById(R.id.BTNNo) as Button

        if (activity.BTNSendSMS.isPressed || voice == "zone 1") {
            yesButton?.onClick {
                activity.sendSMS(activity.phoneZone1, activity.chosenPlateText)
                activity.BTNSendSMS.setBackgroundColor(Color.GREEN)
                activity.setTimer(activity.BTNSendSMS)
                this.dismiss()
            }
        } else if (activity.BTNSendSMSZone2.isPressed || voice == "zone 2") {
            yesButton?.onClick {
                activity.sendSMS(activity.phoneZone2, activity.chosenPlateText)
                activity.BTNSendSMSZone2.setBackgroundColor(Color.GREEN)
                activity.setTimer(activity.BTNSendSMSZone2)
                this.dismiss()
            }
        } else if (activity.BTNSendSMSZone3.isPressed || voice == "zone 3") {
            yesButton?.onClick {
                activity.sendSMS(activity.phoneZone3, activity.chosenPlateText)
                activity.BTNSendSMSZone3.setBackgroundColor(Color.GREEN)
                activity.setTimer(activity.BTNSendSMSZone3)
                this.dismiss()
            }
        } else if (activity.BTNSendSMSZone4.isPressed || voice == "zone 4") {
            yesButton?.onClick {
                activity.sendSMS(activity.phoneZone4, activity.chosenPlateText)
                activity.BTNSendSMSZone4.setBackgroundColor(Color.GREEN)
                activity.setTimer(activity.BTNSendSMSZone4)
                this.dismiss()
            }
        } else if (activity.BTNSendSMSZone5.isPressed || voice == "zone 5") {
            yesButton?.onClick {
                activity.sendSMS(activity.phoneZone5, activity.chosenPlateText)
                activity.BTNSendSMSZone5.setBackgroundColor(Color.GREEN)
                activity.setTimer(activity.BTNSendSMSZone5)
                this.dismiss()
            }
        } else if (activity.BTNSendSMSZone6.isPressed || voice == "zone 6") {
            yesButton?.onClick {
                activity.sendSMS(activity.phoneZone6, activity.chosenPlateText)
                activity.BTNSendSMSZone6.setBackgroundColor(Color.GREEN)
                activity.setTimer(activity.BTNSendSMSZone6)
                this.dismiss()
            }
        } else if (activity.BTNSendSMSZone1Half.isPressed || voice == "zone 1:30") {
            yesButton?.onClick {
                activity.sendSMS(activity.phoneZone1, activity.chosenPlateText + "#30")
                activity.BTNSendSMSZone1Half.setBackgroundColor(Color.GREEN)
                activity.setTimerHalf(activity.BTNSendSMSZone1Half)
                this.dismiss()
            }
        }

        noButton?.onClick {
            this.dismiss()
        }
    }
}
package com.example.dev.parking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

/**
 * Created by dev on 20.10.16..
 */
class VRecognition: Activity() {

    var phrase: String? = null
    private val VOICE_RECOGNITION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startRecognition()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK){
            val result: ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            TVreconizedText.text = result[0]
        }

    }

    fun startRecognition(){
        val intent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE)
    }

    fun getResult(): String? {
        return phrase
    }

}
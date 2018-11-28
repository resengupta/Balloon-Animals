package com.balloonanimals.medgenkiosk.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.balloonanimals.medgenkiosk.R

class PrescriptionActivity: AppCompatActivity() {

    companion object {

        fun createIntent(context: Context?): Intent = Intent(context, PrescriptionActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription_doctor)

    }
}
package com.alftendev.simplesoundquicksettings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alftendev.simplesoundquicksettings.databinding.ActivityMainBinding
import com.google.android.material.color.DynamicColors
import com.alftendev.simplesoundquicksettings.Utils.isDoNotDisturbPermissionGranted
import com.alftendev.simplesoundquicksettings.Utils.requestDoNotDisturbPermission

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.grantPermissionButton.setOnClickListener {
            if (!isDoNotDisturbPermissionGranted(this)) {
                requestDoNotDisturbPermission(this)
            }
        }

        binding.themeButton.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }

        binding.soundVibrationButton.setOnClickListener {
            startActivity(Intent(this, SoundVibrationActivity::class.java))
        }

        binding.premiumButton.setOnClickListener {
            startActivity(Intent(this, PremiumActivity::class.java))
        }

        binding.creditsButton.setOnClickListener {
            startActivity(Intent(this, CreditsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updatePermissionsButtonState()
    }

    private fun updatePermissionsButtonState() {
        val isGranted = isDoNotDisturbPermissionGranted(this)
        binding.permissionBanner.visibility = if (isGranted) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}
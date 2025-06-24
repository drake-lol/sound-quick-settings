package com.alftendev.simplesoundquicksettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alftendev.simplesoundquicksettings.databinding.ActivitySoundVibrationBinding
import com.google.android.material.color.DynamicColors

class SoundVibrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        val binding = ActivitySoundVibrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.category_sound_vibration_title)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.sound_vibration_settings_container, SoundVibrationFragment())
                .commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
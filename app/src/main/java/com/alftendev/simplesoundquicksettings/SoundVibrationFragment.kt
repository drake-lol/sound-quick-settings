package com.alftendev.simplesoundquicksettings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SoundVibrationFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.sound_vibration_preferences, rootKey)
    }
}
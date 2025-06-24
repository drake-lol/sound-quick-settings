package com.alftendev.simplesoundquicksettings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class ThemeFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.theme_preferences, rootKey)
    }
}
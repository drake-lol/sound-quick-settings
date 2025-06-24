package com.alftendev.simplesoundquicksettings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class PremiumFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.premium_preferences, rootKey)
    }
}
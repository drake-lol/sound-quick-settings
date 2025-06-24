package com.alftendev.simplesoundquicksettings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class CreditsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.credits_preferences, rootKey)
        findPreference<Preference>("version")?.summary = BuildConfig.VERSION_NAME
    }
}
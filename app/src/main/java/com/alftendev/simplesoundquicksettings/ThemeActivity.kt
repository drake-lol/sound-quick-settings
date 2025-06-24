package com.alftendev.simplesoundquicksettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alftendev.simplesoundquicksettings.databinding.ActivityThemeBinding
import com.google.android.material.color.DynamicColors

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.category_theme_title)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.theme_settings_container, ThemeFragment())
                .commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
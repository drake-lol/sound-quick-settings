package com.alftendev.simplesoundquicksettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alftendev.simplesoundquicksettings.databinding.ActivityCreditsBinding
import com.google.android.material.color.DynamicColors

class CreditsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityCreditsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.category_credits_title)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.credits_settings_container, CreditsFragment())
                .commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
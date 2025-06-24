package com.alftendev.simplesoundquicksettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alftendev.simplesoundquicksettings.databinding.ActivityPremiumBinding
import com.google.android.material.color.DynamicColors

class PremiumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityPremiumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.category_premium_title)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.premium_settings_container, PremiumFragment())
                .commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
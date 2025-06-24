package com.alftendev.simplesoundquicksettings.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.media.AudioManager
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.alftendev.simplesoundquicksettings.MainActivity
import com.alftendev.simplesoundquicksettings.R
import com.alftendev.simplesoundquicksettings.Utils

class SoundTile : TileService() {
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == AudioManager.RINGER_MODE_CHANGED_ACTION) {
                updateSoundTile()
            }
        }
    }

    private fun updateSoundTile() {
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        if (qsTile == null) {
            return
        }

        when (audioManager.ringerMode) {
            AudioManager.RINGER_MODE_NORMAL -> {
                qsTile.label = getString(R.string.sound)
                qsTile.icon =
                    Icon.createWithResource(this, R.drawable.baseline_notifications_active_24)
                qsTile.state = Tile.STATE_ACTIVE
            }

            AudioManager.RINGER_MODE_VIBRATE -> {
                qsTile.label = getString(R.string.vibration)
                qsTile.icon = Icon.createWithResource(this, R.drawable.baseline_vibration_24)
                qsTile.state = Tile.STATE_INACTIVE
            }

            AudioManager.RINGER_MODE_SILENT -> {
                qsTile.label = getString(R.string.silent)
                qsTile.icon =
                    Icon.createWithResource(this, R.drawable.baseline_notifications_off_24)
                qsTile.state = Tile.STATE_INACTIVE
            }
        }

        qsTile.updateTile()
    }

    override fun onClick() {
        super.onClick()

        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val nextRingerMode = when (audioManager.ringerMode) {
            AudioManager.RINGER_MODE_NORMAL -> AudioManager.RINGER_MODE_VIBRATE
            AudioManager.RINGER_MODE_VIBRATE -> AudioManager.RINGER_MODE_SILENT
            AudioManager.RINGER_MODE_SILENT -> AudioManager.RINGER_MODE_NORMAL
            else -> return
        }

        // Changing to silent mode requires Do Not Disturb permission.
        // If not granted, open the app to let the user grant it.
        if (nextRingerMode == AudioManager.RINGER_MODE_SILENT && !Utils.isDoNotDisturbPermissionGranted(this)) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivityAndCollapse(intent)
            return
        }

        audioManager.ringerMode = nextRingerMode
        updateSoundTile()
    }

    override fun onStartListening() {
        super.onStartListening()
        updateSoundTile()
    }

    override fun onCreate() {
        super.onCreate()

        this.registerReceiver(
            broadcastReceiver,
            IntentFilter(AudioManager.RINGER_MODE_CHANGED_ACTION)
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            this.unregisterReceiver(broadcastReceiver)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun onTileAdded() {
        super.onTileAdded()

        updateSoundTile()
    }

    override fun onTileRemoved() {
        super.onTileRemoved()

        if (qsTile == null) {
            return
        }

        qsTile.state = Tile.STATE_UNAVAILABLE
        qsTile.updateTile()
    }
}
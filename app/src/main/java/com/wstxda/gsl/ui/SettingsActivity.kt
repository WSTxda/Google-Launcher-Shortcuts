package com.wstxda.gsl.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.wstxda.gsl.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdgeNoContrast()
        setContentView(R.layout.activity_settings)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        collapsingToolbar.title = getString(R.string.app_settings)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        if (savedInstanceState == null) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.setGraph(R.navigation.nav_settings)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav_host_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom * 2)
            insets
        }
    }

    private fun enableEdgeToEdgeNoContrast() {
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        if (Build.VERSION.SDK_INT >= 29) {
            window.isNavigationBarContrastEnforced = false
        }
    }
}
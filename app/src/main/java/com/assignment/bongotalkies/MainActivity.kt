package com.assignment.bongotalkies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

/**
 * Once Hilt is set up in your Application class and an application-level component is available,
 * Hilt can provide dependencies to other Android classes that have the @AndroidEntryPoint annotation:
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main)
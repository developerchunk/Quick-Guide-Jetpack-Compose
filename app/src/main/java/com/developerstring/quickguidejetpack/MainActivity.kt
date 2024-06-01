package com.developerstring.quickguidejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.developerstring.quickguidejetpack.textfield.AutoCompleteTextFieldScreen
import com.developerstring.quickguidejetpack.ui.theme.QuickGuideJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickGuideJetpackTheme {
                AutoCompleteTextFieldScreen()
            }
        }
    }
}
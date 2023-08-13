package org.sirekanyan.outline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import org.sirekanyan.outline.api.OutlineApi
import org.sirekanyan.outline.api.model.Key
import org.sirekanyan.outline.ui.theme.OutlineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val api = remember { OutlineApi() }
            val state = rememberMainState()
            val keys by produceState(listOf<Key>(), state.selected) {
                value = state.selected?.let { api.getKeys(it) } ?: listOf()
            }
            OutlineTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainContent(api, state, keys)
                }
            }
        }
    }
}

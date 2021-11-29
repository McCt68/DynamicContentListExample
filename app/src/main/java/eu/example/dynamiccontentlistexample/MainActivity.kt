package eu.example.dynamiccontentlistexample

// Now 42 tested Git branch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
// Example of using kotlin code with Jetpack Composable functions
// calls @GreetingList
@Composable
fun MainScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList() // call @GreetingList
    }
}

@Composable
fun GreetingList() {
    // Remember state when recomposition happens
    val greetingListState = remember {mutableStateListOf<String>("knud", "Bettine")}
    for (name in greetingListState){
        Greeting(name = name)
    }

    // state is changed when we press button, and recomposition will happen
    //
    Button(onClick = { greetingListState.add("New name") }) {
        Text(text = "Add new name")
    }
}

// greet a name
@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.h5
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()

}
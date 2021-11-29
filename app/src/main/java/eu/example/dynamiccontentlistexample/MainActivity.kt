package eu.example.dynamiccontentlistexample

// 43 State Hoisting

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
    // Remember state when recomposition happens
    val greetingListState = remember {
        mutableStateListOf<String>("knud", "Bettine")
    }

    // Layout
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // call @GreetingList with parameters
        // 1 parameter greetingListState
        // 2 parameter a buttonClick  / lambda function
        // the lambda function code executes when the button is clicked
        GreetingList(greetingListState, { greetingListState.add("New name") })
    }
}

// higher order function
// first parameter a namesList of String
// second parameter a function that don't return anything ( Unit ) - The buttonClick is called
@Composable
fun GreetingList(namesList: List<String>, buttonClick: () -> Unit) {

    for (name in namesList){
        Greeting(name = name)
    }

    // Trigger recomposition when we press the button
    // state is changed when we press button, and recomposition will happen
    Button(onClick = buttonClick) {
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
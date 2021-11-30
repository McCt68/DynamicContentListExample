package eu.example.dynamiccontentlistexample

// 46 Lift upState to viewModel
// Handle the state in a viewModel class
// Kinda bad example
// app will copy the text i enter at input field to the button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
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
fun MainScreen(viewModel: MainViewModel = MainViewModel()){
    // value gotten from class MainViewModel .textFieldState
    val newNameStateContent = viewModel.textFieldState.observeAsState("")

    // Layout
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // call @GreetingMessage with parameters
        GreetingMessage(
            newNameStateContent.value
        ) {newName -> viewModel.onTextChanged(newName)} // type lambda
    }
}

// Higher order function
@Composable
fun GreetingMessage(
    textFieldValue: String, // value of input entered in textField
    textFieldUpdate: (newName: String) -> Unit // lambda with 1 parameter, dont return anything
) {

    // Textfield with 2 parameters
    // value - the input text to be shown in the text field
    // onValueChange - the callback that is triggered when the input service updates the text
    TextField(
        value = textFieldValue,
        onValueChange = textFieldUpdate,
        textStyle = MaterialTheme.typography.h5
    )

    // Trigger recomposition when we press the button
    // state is changed when we press button, and recomposition will happen
    Button(onClick = { }) {
        Text(textFieldValue)
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
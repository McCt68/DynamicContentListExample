package eu.example.dynamiccontentlistexample

// 44 State Hoisting

import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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

    // MainScreen is the entry piont for states
    // Remember state when recomposition happens
    val greetingListState = remember {
        mutableStateListOf<String>("knud", "Bettine")
    }

    // State value for the textfield, with an empty string as start
    val newNameStateContent = remember { mutableStateOf("")}

    // Layout
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // call @GreetingList with parameters
        GreetingList(
            greetingListState, // type list of strings
            { greetingListState.add(newNameStateContent.value) }, // type lambda
            newNameStateContent.value, // type value of input text field
            {newName -> newNameStateContent.value = newName }) // type lambda
    }
}

// Higher order function
// first parameter a namesList of String
// second parameter a function that don't return anything ( Unit ) - The buttonClick is called
// third parameter is the textFieldValue
// fourth parameter is an takes a lambda function which don't return anything
// the lambda takes a parameter which is what we enter at the keyboard to the textField
@Composable
fun GreetingList(
    namesList: List<String>,
    buttonClick: () -> Unit, // lambda with no parameter, dont return anything
    textFieldValue: String, // value of input entered in textField
    textFieldUpdate: (newName: String) -> Unit // lambda with 1 parameter, dont return anything
) {

    for (name in namesList){
        Greeting(name = name)
    }

    // Textfield with 2 parameters
    // value - the input text to be shown in the text field
    // onValueChange - the callback that is triggered when the input service updates the text
    TextField(
        value = textFieldValue,
        onValueChange = textFieldUpdate,
        textStyle = MaterialTheme.typography.h5,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii) // just testing
    )

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
package eu.example.dynamiccontentlistexample

// Video 38

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import eu.example.dynamiccontentlistexample.ui.theme.DynamicContentListExampleTheme

//
val namesList: ArrayList<String> = arrayListOf("John", "Michael", "Anna", "Ingrid", "Bente")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingList(names = namesList)
        }
    }
}

// Example of using kotlin code with Jetpack Composable functions
// takes a list of names as parameter
@Composable
fun GreetingList(names: List<String>){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Another way of doing a for loop. Calls @Greeting for each name in the List
        names.forEach{ Greeting(name = it)}

        Button(onClick = { namesList.add("New name") }) {
            Text(text = "Add new name")
    }

    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.h4
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GreetingList(names = namesList)

}
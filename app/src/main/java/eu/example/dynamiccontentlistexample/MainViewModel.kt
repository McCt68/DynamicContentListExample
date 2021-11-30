package eu.example.dynamiccontentlistexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// The viewModel takes care of all the state handling

// creating class of type ViewModel ( of Android lifecycle ?))
// takes a parameter of the actual state
class MainViewModel: ViewModel() {
    val textFieldState = MutableLiveData("")

    fun onTextChanged(newText: String){
        textFieldState.value = newText
    }
}
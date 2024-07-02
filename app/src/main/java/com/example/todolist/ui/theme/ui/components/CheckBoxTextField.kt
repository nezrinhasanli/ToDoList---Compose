package com.example.todolist.ui.theme.ui.components

import android.view.KeyEvent
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.theme.room.CheckedItem
import com.example.todolist.ui.theme.viewmodel.ToDoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckBoxTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester = FocusRequester(),
    nextFocusRequester: FocusRequester = FocusRequester(),
    viewModel: ToDoListViewModel
){

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                nextFocusRequester.requestFocus()
            }
        ),
        modifier = Modifier.onKeyEvent {
            if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.addCheckedItem(
                    CheckedItem(
                        checked = false,
                        note = ""
                    )
                )
//                focusRequester.requestFocus()
            }
            if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DEL) {
                viewModel.removeCheckedItem(
                    CheckedItem(
                        checked = false,
                        note = ""
                    )
                )
            }
            false
        }
            .focusRequester(focusRequester)

    )
}
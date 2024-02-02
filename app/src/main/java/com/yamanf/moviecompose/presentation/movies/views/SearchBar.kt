package com.yamanf.moviecompose.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
    onCancelled : (Boolean) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val keyboardActions = KeyboardActions(
        onDone = {
            if (text.isNotBlank()){
                onSearch(text)
                keyboardController?.hide()
            }
        }
    )

    val keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    )

    Box(modifier = modifier) {
        TextField(value = text,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search icon",
                )
            },
            trailingIcon = {
                if (isFocused){
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close search icon",
                        modifier = Modifier.clickable {
                            focusManager.clearFocus()
                            text = ""
                            isFocused = false
                            onCancelled(true)
                        }
                    )
                }
            },
            onValueChange = {
                text = it
            },
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            textStyle = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                }
        )

        if (isFocused.not() && hint.isNotBlank()) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(top = 17.dp, start = 64.dp),
                fontSize = 20.sp
            )
        }

    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        hint = "Batman Movies"
    )
}
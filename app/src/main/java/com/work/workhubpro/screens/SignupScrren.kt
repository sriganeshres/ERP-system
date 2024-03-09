package com.work.workhubpro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.work.workhubpro.R
import com.work.workhubpro.composables.CheckBoxComposable
import com.work.workhubpro.composables.HeadingTextComposable
import com.work.workhubpro.composables.MyTextField
import com.work.workhubpro.composables.NormalTextComposable
import com.work.workhubpro.composables.PasswordTextField

@Composable
fun SignupScreen() {
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
        ){
            NormalTextComposable(value = stringResource(id = R.string.hey_there))
            HeadingTextComposable(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(labelValue = stringResource(id = R.string.first_name), painterResource(id = R.drawable.profile))
            MyTextField(labelValue = stringResource(id = R.string.last_name), painterResource(id = R.drawable.profile))
            MyTextField(labelValue = stringResource(id = R.string.email), painterResource(id = R.drawable.mail))
            PasswordTextField(labelValue = stringResource(id = R.string.password), painterResource(id = R.drawable.mail))
            Spacer(modifier = Modifier.height(16.dp))
            CheckBoxComposable(value = stringResource(id = R.string.terms_and_conditions))
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignupScreen()
}
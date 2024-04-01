package com.work.workhubpro.ui.screens.createOrg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
//import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.work.workhubpro.R
import com.work.workhubpro.ui.composables.CheckBoxComposable
import com.work.workhubpro.ui.composables.HeadingTextComposable
import com.work.workhubpro.ui.composables.MyTextField
import com.work.workhubpro.ui.composables.NormalTextComposable
import com.work.workhubpro.ui.navigation.Navscreen
import com.work.workhubpro.ui.screens.CreateOrg.CreateOrganisationViewModel


@Composable


fun Create_Org_Screen(navController: NavController) {


    var email by remember { mutableStateOf("") }
    var organisationName by remember { mutableStateOf("") }
    var companyType by remember { mutableStateOf("") }
    var domainName by remember { mutableStateOf("") }
    var adminName by remember { mutableStateOf("") }

    val createOrgViewModel: CreateOrganisationViewModel = hiltViewModel()

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            NormalTextComposable(value = stringResource(id = R.string.hey_there))
            HeadingTextComposable(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextField(
                labelValue = stringResource(id = R.string.Company_email),
                painterResource(id = R.drawable.outline_mail_outline_black_20),
                textValue = email,
                onValueChange = { email = it }
            )

            MyTextField(
                labelValue = stringResource(id = R.string.organisation_name),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = organisationName,
                onValueChange = { organisationName = it }
            )
            MyTextField(
                labelValue = stringResource(id = R.string.company_type),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = companyType,
                onValueChange = { companyType = it }
            )
            MyTextField(
                labelValue = stringResource(id = R.string.domain_name),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = domainName,
                onValueChange = { domainName = it }
            )
            MyTextField(
                labelValue = stringResource(id = R.string.admin_name),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = adminName,
                onValueChange = { adminName = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CheckBoxComposable(value = stringResource(id = R.string.terms_and_conditions))
            Button(
                onClick = {
                    createOrgViewModel.createOrg(
                        organisationName,
                        email,
                        adminName,
                        domainName,
                        companyType
                    )
                    navController.navigate(Navscreen.Signup.route)
                }
            ) {
                Text(text = "Create Org")
            }
//            Button(){Text(text="Create Organisation")}
        }
    }
}
//@Preview
//@Composable
//fun CreateOrgScreenPreview() {
//    Create_Org_Screen(navController = NavController)
//}
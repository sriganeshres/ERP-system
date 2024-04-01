package com.work.workhubpro.ui.screens.createOrg

//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.work.workhubpro.R
import com.work.workhubpro.ui.composables.CheckBoxComposable
import com.work.workhubpro.ui.composables.HeadingTextComposable
import com.work.workhubpro.ui.composables.MyTextField
import com.work.workhubpro.ui.composables.NormalTextComposable
import com.work.workhubpro.ui.composables.PasswordTextField
import com.work.workhubpro.ui.navigation.Navscreen
import com.work.workhubpro.ui.screens.CreateOrg.CreateOrganisationViewModel


@Composable


fun Create_OrgScreen(navController: NavController) {


    var email by remember { mutableStateOf("") }
    var organisationName by remember { mutableStateOf("") }
    var companyType by remember { mutableStateOf("") }
    var domainName by remember { mutableStateOf("") }
    var adminName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var adminEmail by remember { mutableStateOf("") }


    val createOrgViewModel: CreateOrganisationViewModel = hiltViewModel()

    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(scrollState)
        ) {

            NormalTextComposable(value = stringResource(id = R.string.hey_there))
            HeadingTextComposable(value = stringResource(id = R.string.create_Org))
            Spacer(modifier = Modifier.height(20.dp))



            MyTextField(
                labelValue = stringResource(id = R.string.organisation_name),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = organisationName,
                onValueChange = { organisationName = it }
            )
            MyTextField(
                labelValue = stringResource(id = R.string.Company_email),
                painterResource(id = R.drawable.outline_mail_outline_black_20),
                textValue = email,
                onValueChange = { email = it }
            )
            MyTextField(
                labelValue = stringResource(id = R.string.company_type),
                painterResource(id = R.drawable.company_symbol),
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
                painterResource(id = R.drawable.admin_symbol),
                textValue = adminName,
                onValueChange = { adminName = it }
            )

            MyTextField(
                labelValue = stringResource(id = R.string.description),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = description,
                onValueChange = { description = it }
            )

            Spacer(modifier = Modifier.height(20.dp))
            HeadingTextComposable(value = stringResource(id = R.string.admin_Details))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextField(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = firstName,
                onValueChange = { firstName = it }

            )
            MyTextField(labelValue = stringResource(id = R.string.last_name),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = lastName,
                onValueChange = { lastName = it })
            MyTextField(labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.outline_mail_outline_black_20),
                textValue = adminEmail,
                onValueChange = { adminEmail = it })
            PasswordTextField(labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.outline_password_black_20),
                textValue = password,
                onValueChange = { password = it })

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
                    navController.navigate(Navscreen.Bottom.route + "/${firstName}")
                }
            ) {
                Text(text = "Create Org")
            }
            Spacer(modifier = Modifier.height(16.dp))
//            Button(){Text(text="Create Organisation")}
        }
    }
}
//@Preview
//@Composable
//fun CreateOrgScreenPreview() {
//    Create_Org_Screen(navController = NavController)
//}
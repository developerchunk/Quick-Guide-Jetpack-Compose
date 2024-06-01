package com.developerstring.whatsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SendWhatsAppMessageScreen(
    context: Context
) {

    // default country code, but of-course you can make changes to it
    var countryCode by remember {
        mutableStateOf("+91")
    }

    var mobileNumber by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Send WhatsApp Message", fontSize = 20.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(60.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .weight(3f),
                value = countryCode,
                onValueChange = {
                    countryCode = it
                },
                singleLine = true
            )

            OutlinedTextField(
                modifier = Modifier.weight(7f),
                value = mobileNumber,
                onValueChange = {
                    mobileNumber = it
                },
                placeholder = {
                    Text(text = "Mobile No")
                },
                singleLine = true
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .heightIn(min = 180.dp),
            value = message,
            onValueChange = {
                message = it
            },
            placeholder = {
                Text(text = "Message")
            },
            singleLine = false
        )

        Spacer(modifier = Modifier.height(60.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                onClickWhatsApp(
                    context = context,
                    mobileNumber = countryCode + mobileNumber,
                    message = message
                )
            }) {
                Text(text = "Send Message")
            }
        }


    }

}

fun onClickWhatsApp(
    context: Context,
    mobileNumber: String,
    message: String
) {

    // WhatsApp allows to send messages using the API links which has 2 parameters that are mobile number with country code(without '+' symbol in prefix) and message
    // sample link: "https://wa.me/1XXXXXXXXXX?text=I%27m%20interested%20in%20your%20car%20for%20sale"
    // link format: "https://wa.me/[MobileNumber]?text=[Message - replacing (whitespace) ' ' with '%20']"

    // Now we just have to open the link in the user's device
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(
            "https://wa.me/${mobileNumber.removePrefix("+")}?text=${
                message.replace(
                    " ",
                    "%20"
                )
            }"
        )
    )
    context.startActivity(intent)

}
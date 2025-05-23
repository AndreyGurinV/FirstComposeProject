package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstComposeProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactDetails(
                        contact = Contact(
                            name = "Евгений",
                            surname = "Андреевич",
                            familyName = "Лукашин",
                            imageRes = null,
                            isFavorite = true,
                            phone = "+7 495 495 95 95",
                            address = "г. Москва, 3-я улица Строителей, д.25, кв.12",
                            email = "ELukashin@practicum.ru"
                        ),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(25.dp)) {
        if (contact.imageRes == null) {
            RoundInitials(contact.name.take(1) + contact.familyName.take(1))
        } else {
            Image(
                modifier = Modifier.size(120.dp, 80.dp),
                painter = painterResource(id = contact.imageRes),
                contentDescription = null
            )
        }
        Text(
            text = "${contact.name} ${if (contact.surname != null) contact.surname else ""}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Row(
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            Text(
                text = "${contact.familyName}: ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                )
            )
            if (contact.isFavorite) Image(
                modifier = Modifier.padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = null
            )
        }
        DetailsRow(stringResource(R.string.phone), contact.phone)
        DetailsRow(stringResource(R.string.address), contact.address)
        if (contact.email != null)
            DetailsRow(stringResource(R.string.email), contact.email)
    }
}

@Composable
fun DetailsRow(header: String, value: String){
    Row(
        modifier = Modifier.padding(top = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = "${header}: ",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = value,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

        }
    }
}

@Composable
fun RoundInitials(initials: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(bottom = 10.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
        )
        Text(
            text = initials
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoPhotoPreview() {
    FirstComposeProjectTheme {
        ContactDetails(Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            imageRes = null,
            isFavorite = true,
            phone = "+7 495 495 95 95",
            address = "г. Москва, 3-я улица Строителей, д.25, кв.12",
            email = "ELukashin@practicum.ru"
        ))
    }
}
@Preview(showBackground = true)
@Composable
fun WithPhotoPreview() {
    FirstComposeProjectTheme {
        ContactDetails(Contact(
            name = "Василий",
            surname = null,
            familyName = "Кузякин",
            imageRes = R.drawable.face,
            isFavorite = false,
            phone = "---",
            address = "Ивановская область, дер. Крутово, д.4",
            email = null
        ))
    }
}
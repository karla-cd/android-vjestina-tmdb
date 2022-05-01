package agency.five.tmdb.ui.theme

import agency.five.tmdb.R
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily(Font(R.font.quicksand_regular)),
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
        ),
        body2 = TextStyle(
                fontFamily = FontFamily(Font(R.font.quicksand_regular)),
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
        ),
        h1 = TextStyle(
                fontFamily = FontFamily(Font(R.font.quicksand_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
        ),
        h2 = TextStyle(
                fontFamily = FontFamily(Font(R.font.quicksand_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textDecoration = TextDecoration.Underline
        )
)

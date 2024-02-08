package com.example.institutoharia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.institutoharia.ui.theme.InstitutoHariaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstitutoHariaTheme {
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(0) }

    if (shouldShowOnboarding == 0) {
        MiInicio(
            fpbClick = { shouldShowOnboarding = 1 },
            fpmClick = { shouldShowOnboarding = 2 },
            fpsClick = { shouldShowOnboarding = 3 }
        )
    } else if (shouldShowOnboarding == 1){
        MiFPB(
            inicioClick = { shouldShowOnboarding = 0 },
            fpmClick = { shouldShowOnboarding = 2 },
            fpsClick = { shouldShowOnboarding = 3 }
        )
    } else if (shouldShowOnboarding == 2) {
        MiFPM(
            fpbClick = { shouldShowOnboarding = 1},
            inicioClick = { shouldShowOnboarding = 0},
            fpsClick = {shouldShowOnboarding = 3}
        )
    } else {
        MiFPS(
            fpbClick = { shouldShowOnboarding = 1},
            fpmClick = { shouldShowOnboarding = 2},
            inicioClick = {shouldShowOnboarding = 0}
        )
    }
}

/******************* GENERAL **********************/
@Composable
fun MiTopBar() {
    Row(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .height(75.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val context = LocalContext.current
        val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www3.gobiernodecanarias.org/medusa/edublog/iesharia/")) }

        TextButton(onClick = { context.startActivity(intent) }) {
            Image(
                painter = painterResource(id = R.drawable.logo_ies_haria),
                contentDescription = "logoPlaneate.png"
            )
        }

        Spacer(modifier = Modifier.padding(6.dp))

        Text(
            text = "I.E.S Haría",
            color = Color.Green
        )
    }
}

@Composable
fun MiBotonPlaneate(onClick: () -> Unit, @DrawableRes imagenBoton: Int, contentDescription: String) {
    TextButton(
        onClick = { onClick() },
        modifier = Modifier.size(135.dp)
    ) {
        Image(
            painter = painterResource(id = imagenBoton),
            contentDescription = contentDescription,
            modifier = Modifier.size(135.dp)
        )
    }
}

@Composable
fun MiTitulo(
    texto: String,
    fontWeight: FontWeight,
    textAlign: TextAlign
) {
    Text(
        text = texto,
        fontSize = 24.sp,
        fontWeight = fontWeight,
        textAlign = textAlign,
        modifier = Modifier.padding(16.dp)
    )
}
@Composable
fun MiContenido(
    texto: String,
) {
    Text(
        text = texto,
        fontSize = 16.sp,
        modifier = Modifier.padding(start = 8.dp, end = 4.dp)
    )
    Spacer(modifier = Modifier.padding(16.dp))
}

/******************* GENERAL **********************/


/******************* PRIMERA PÁGINA **********************/
@Composable
fun MiInicio(fpbClick: () -> Unit, fpmClick: () -> Unit, fpsClick: () -> Unit) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MiTopBar()
        Image(
            painter = painterResource(id = R.drawable.centro_menu_1),
            contentDescription = "centro_menu_1.png"
        )
        MiInformacionDelCentro()
        MisBotonesInicio(fpbClick, fpmClick, fpsClick)
    }
}

@Composable
fun MiInformacionDelCentro() {
    MiTitulo("Información del Centro", FontWeight.Bold, TextAlign.Center)

    MiContenido(
        "Dirección: C. Santiago Noda, 4, 35520 Haría, Las Palmas.\n" +
              "Contactos: 928 30 30 14\n" +
              "Email: 35009206@gobiernodecanarias.org.\n" +
              "Dispone de la ESO, Bachillerato y FP Básica, Grado Medio y Superior de Informática y Comunicaciones.\n" +
              "Cuenta con transporte escolar gratuito, turno de mañana y cafetería."
    )
}

@Composable
fun MisBotonesInicio(fpbClick: () -> Unit, fpmClick: () -> Unit, fpsClick: () -> Unit) {
    Row {
        MiBotonPlaneate(onClick = fpbClick, imagenBoton = R.drawable.fpb, contentDescription = "fpb.png")
        MiBotonPlaneate(onClick = fpmClick, imagenBoton = R.drawable.fpm, contentDescription = "fpm.png")
        MiBotonPlaneate(onClick = fpsClick, imagenBoton = R.drawable.fps, contentDescription = "fps.png")
    }
}
/******************* PRIMERA PÁGINA **********************/


/******************* FP BASICO **********************/
@Composable
fun MiFPB(inicioClick: () -> Unit, fpmClick: () -> Unit, fpsClick: () -> Unit){
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            MiTopBar()
            MiInformacionYRequisitosFPB()
            MisBotonesFPB(inicioClick, fpmClick, fpsClick)
            MisAsiganturasFPB1()
        }
    }
}

@Composable
fun MiInformacionYRequisitosFPB(){
    OutlinedCard (
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.padding(12.dp)
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Información del Ciclo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                    append(
                        "Tiene una duración de 2000 horas totales.\n" +
                                "Se aprende montaje de equipos en sistemas microinformáticos y redes de transmisión de datos, operaciones auxiliares en montaje y mantenimiento de sistemas informáticos, realizar operaciones para el almacenamiento y transporte de sistemas, periféricos y consumibles; y por último realizar comprobaciones rutinarias de verificación en el montaje\n"
                    )
                }
            },
            modifier = Modifier.padding(16.dp),
        )

        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Requisitos del Ciclo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                    append(
                        "Tiene una duración de 2000 horas totales.\n" +
                                "Se aprende montaje de equipos en sistemas microinformáticos y redes de transmisión de datos, operaciones auxiliares en montaje y mantenimiento de sistemas informáticos, realizar operaciones para el almacenamiento y transporte de sistemas, periféricos y consumibles; y por último realizar comprobaciones rutinarias de verificación en el montaje\n"
                    )
                }
            },
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun MisBotonesFPB(inicioClick: () -> Unit, fpmClick: () -> Unit, fpsClick: () -> Unit){
    Row {
        MiBotonPlaneate(onClick = inicioClick, imagenBoton = R.drawable.inicio, contentDescription = "inicio.png")
        MiBotonPlaneate(onClick = fpmClick, imagenBoton = R.drawable.fpm, contentDescription = "fpm.png")
        MiBotonPlaneate(onClick = fpsClick, imagenBoton = R.drawable.fps, contentDescription = "fps.png")
    }
}

@Composable
fun MisAsiganturasFPB1(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.cienciasaplicadas,
        R.drawable.comunicacionsociedad,
        R.drawable.equiposelectricos,
        R.drawable.mantenimiento,
    ),
    nombre: List<String> = listOf(
        "Ciencias Aplicadas I",
        "Comunicación y sociedad I",
        "Equipos Eléctricos y Electrónicos",
        "Instalación y mantenimiento para transmisión de datos"
    ),
    descripcion: List<String> = listOf(
        "Aplicación del conocimiento científico básico a necesidades humanas y al desarrollo tecnológico.\nHoras Semanales: 6 horas\nHoras Totales: 215 horas.",
        "Se desarrolla las habilidades y destrezas lingüísticas, además de alcanzar el nivel de precisión, claridad y fluidez requeridas, para comunicarse en su entorno.\nHoras Semanales: 6 horas\nHoras Totales: 215 horas\n",
        "Se basa en el montaje y mantenimiento de equipos eléctricos y electrónicos\nHoras Semanales: 11 horas\nHoras Totales: 305 horas",
        "Se administra, gestiona, instala y mantiene las redes.\nHoras Semanales: 7 horas\nHoras Totales: 217 horas"
    )

){
    MiForAsignaturasFPB1(
        imagenAsignatura = imagenAsignatura,
        nombre = nombre,
        descripcion = descripcion
    )
}

@Composable
fun MiForAsignaturasFPB1(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    Text(
        text = "Asignaturas del Primer Año",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .clickable {
                        expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
                    }
                    .border(2.dp, Color.Black)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = imagenAsignatura[cadaItem]),
                        contentDescription = nombre[cadaItem]
                    )

                    if (expandirBox == cadaItem){
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = nombre[cadaItem],
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = descripcion[cadaItem],
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
            }
        }
    }
}
/******************* FP BASICO **********************/


/******************* FP MEDIO **********************/
@Composable
fun MiFPM(fpbClick: () -> Unit, inicioClick: () -> Unit, fpsClick: () -> Unit){
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            MiTopBar()
            MiInformacionYRequisitosFPM()
            MisBotonesFPM(fpbClick, inicioClick, fpsClick)
            MisAsiganturasFPM1()
        }
    }
}

@Composable
fun MiInformacionYRequisitosFPM(){
    OutlinedCard (
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.padding(12.dp)
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Información del Ciclo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                    append(
                        "Tiene una duración de 2000 horas totales.\n" +
                                "Se aprende montaje de equipos en sistemas microinformáticos y redes de transmisión de datos, operaciones auxiliares en montaje y mantenimiento de sistemas informáticos, realizar operaciones para el almacenamiento y transporte de sistemas, periféricos y consumibles; y por último realizar comprobaciones rutinarias de verificación en el montaje\n"
                    )
                }
            },
            modifier = Modifier.padding(16.dp),
        )

        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Requisitos del Ciclo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                    append(
                        "Tiene una duración de 2000 horas totales.\n" +
                                "Para poder acceder a la FP Básica, es necesario tener quince años o cumplirlos durante el año natural y no superar los diecisiete en el momento de acceso.\n" +
                                "Haber cursado el primer ciclo de Educación Secundaria Obligatoria (3ºESO) o haber cursado el segundo ciclo de Educación Secundaria Obligatoria.\n" +
                                "Haber propuesto el equipo docente a los padres, madres o tutores legales la incorporación del alumno o alumna a un ciclo de Formación Profesional Básica.\n" +
                                "Se debe cumplir con todos los requisitos.\n"
                    )
                }
            },
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun MisBotonesFPM(fpmClick: () -> Unit, inicioClick: () -> Unit, fpsClick: () -> Unit){
    Row {
        MiBotonPlaneate(onClick = fpmClick, imagenBoton = R.drawable.fpb, contentDescription = "fpm.png")
        MiBotonPlaneate(onClick = inicioClick, imagenBoton = R.drawable.inicio, contentDescription = "inicio.png")
        MiBotonPlaneate(onClick = fpsClick, imagenBoton = R.drawable.fps, contentDescription = "fps.png")
    }
}

@Composable
fun MisAsiganturasFPM1(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.cienciasaplicadas,
        R.drawable.comunicacionsociedad,
        R.drawable.equiposelectricos,
        R.drawable.mantenimiento,
    ),
    nombre: List<String> = listOf(
        "Ciencias Aplicadas I",
        "Comunicación y sociedad I",
        "Equipos Eléctricos y Electrónicos",
        "Instalación y mantenimiento para transmisión de datos"
    ),
    descripcion: List<String> = listOf(
        "Aplicación del conocimiento científico básico a necesidades humanas y al desarrollo tecnológico.\nHoras Semanales: 6 horas\nHoras Totales: 215 horas.",
        "Se desarrolla las habilidades y destrezas lingüísticas, además de alcanzar el nivel de precisión, claridad y fluidez requeridas, para comunicarse en su entorno.\nHoras Semanales: 6 horas\nHoras Totales: 215 horas\n",
        "Se basa en el montaje y mantenimiento de equipos eléctricos y electrónicos\nHoras Semanales: 11 horas\nHoras Totales: 305 horas",
        "Se administra, gestiona, instala y mantiene las redes.\nHoras Semanales: 7 horas\nHoras Totales: 217 horas"
    )

){
    MiForAsignaturasFPM1(
        imagenAsignatura = imagenAsignatura,
        nombre = nombre,
        descripcion = descripcion
    )
}

@Composable
fun MiForAsignaturasFPM1(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    Text(
        text = "Asignaturas del Primer Año",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .clickable {
                        expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
                    }
                    .border(2.dp, Color.Black)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = imagenAsignatura[cadaItem]),
                        contentDescription = nombre[cadaItem]
                    )

                    if (expandirBox == cadaItem){
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = nombre[cadaItem],
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = descripcion[cadaItem],
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
            }
        }
    }
}
/******************* FP MEDIO **********************/


/******************* FP SUPERIOR **********************/
@Composable
fun MiFPS(fpbClick: () -> Unit, fpmClick: () -> Unit, inicioClick: () -> Unit){
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            MiTopBar()
            MiInformacionYRequisitosFPS()
            MisBotonesFPS(fpbClick, fpmClick, inicioClick)
            MisAsiganturasFPS1()
        }
    }
}

@Composable
fun MiInformacionYRequisitosFPS(){
    OutlinedCard (
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.padding(12.dp)
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Información del Ciclo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                    append(
                        "Tiene una duración de 2000 horas totales.\n" +
                                "Se aprende a instalar y configurar software básico y de aplicación, redes locales cableadas, inalámbricas o mixtas y conectadas a redes públicas, a mantener servicios multiusuarios, aplicaciones y dispositivos compartidos en un entorno de red local, montar y configurar ordenadores, etc.\n"
                    )
                }
            },
            modifier = Modifier.padding(16.dp),
        )

        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Requisitos del Ciclo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                    append(
                        "Tiene una duración de 2000 horas totales.\n" +
                                "Se aprende montaje de equipos en sistemas microinformáticos y redes de transmisión de datos, operaciones auxiliares en montaje y mantenimiento de sistemas informáticos, realizar operaciones para el almacenamiento y transporte de sistemas, periféricos y consumibles; y por último realizar comprobaciones rutinarias de verificación en el montaje\n"
                    )
                }
            },
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun MisBotonesFPS(fpbClick: () -> Unit, fpmClick: () -> Unit, inicioClick: () -> Unit){
    Row {
        MiBotonPlaneate(onClick = fpbClick, imagenBoton = R.drawable.fpb, contentDescription = "fpm.png")
        MiBotonPlaneate(onClick = fpmClick, imagenBoton = R.drawable.fpm, contentDescription = "fps.png")
        MiBotonPlaneate(onClick = inicioClick, imagenBoton = R.drawable.inicio, contentDescription = "inicio.png")
    }
}

@Composable
fun MisAsiganturasFPS1(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.cienciasaplicadas,
        R.drawable.comunicacionsociedad,
        R.drawable.equiposelectricos,
        R.drawable.mantenimiento,
    ),
    nombre: List<String> = listOf(
        "Ciencias Aplicadas I",
        "Comunicación y sociedad I",
        "Equipos Eléctricos y Electrónicos",
        "Instalación y mantenimiento para transmisión de datos"
    ),
    descripcion: List<String> = listOf(
        "Aplicación del conocimiento científico básico a necesidades humanas y al desarrollo tecnológico.\nHoras Semanales: 6 horas\nHoras Totales: 215 horas.",
        "Se desarrolla las habilidades y destrezas lingüísticas, además de alcanzar el nivel de precisión, claridad y fluidez requeridas, para comunicarse en su entorno.\nHoras Semanales: 6 horas\nHoras Totales: 215 horas\n",
        "Se basa en el montaje y mantenimiento de equipos eléctricos y electrónicos\nHoras Semanales: 11 horas\nHoras Totales: 305 horas",
        "Se administra, gestiona, instala y mantiene las redes.\nHoras Semanales: 7 horas\nHoras Totales: 217 horas"
    )

){
    MiForAsignaturasFPS1(
        imagenAsignatura = imagenAsignatura,
        nombre = nombre,
        descripcion = descripcion
    )
}

@Composable
fun MiForAsignaturasFPS1(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    Text(
        text = "Asignaturas del Primer Año",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .clickable {
                        expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
                    }
                    .border(2.dp, Color.Black)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = imagenAsignatura[cadaItem]),
                        contentDescription = nombre[cadaItem]
                    )

                    if (expandirBox == cadaItem){
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = nombre[cadaItem],
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = descripcion[cadaItem],
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
            }
        }
    }
}
/******************* FP SUPERIOR **********************/
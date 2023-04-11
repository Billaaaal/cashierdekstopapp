import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.zIndex
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import org.jetbrains.skia.impl.Log
import org.jetbrains.skia.paragraph.TextBox
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.concurrent.timerTask
import kotlin.math.round

data class product(
    val number:String,
    val productName:String,
    val price: Float

)


@Composable
fun totalTextContainer(totalTextValue:String){
    Text(
        totalTextValue,

        fontFamily = FontFamily(
            androidx.compose.ui.text.platform.Font(
                resource = "font.ttf"
            )
        ),
        fontSize = (22).sp
        , color = Color(0XFFFFFFFF),
        modifier = Modifier
            .padding(end = 20.dp)
    )
}

@Composable
fun item(
    product:String,
    price:String

){
    Row (
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(
            product,
            modifier = Modifier
                .padding(start = 20.dp),

                fontFamily = FontFamily(
                androidx.compose.ui.text.platform.Font(
                resource = "font.ttf"
                )
                ),
            fontSize = (15).sp
            , color = Color(0XFF000000)
        )
        Spacer(
            modifier = Modifier
                .weight(1.0f)
        )
        Text(
            price,

            fontFamily = FontFamily(
                androidx.compose.ui.text.platform.Font(
                    resource = "font.ttf"
                )
            ),
            fontSize = (15).sp
            , color = Color(0XFFef6369),
            modifier = Modifier
                .padding(end = 20.dp)
        )


    }

}

fun clearTotal(){
    //val url = "http://192.168.31.79:5005" //Desktop
    val url = "http://192.168.31.11:5005/clear_total"




    Fuel
        .post(url)
        .header(mapOf("Content-Type" to "application/json"))
        .body("payload")
        .response { response_->

        }
}



fun formatPrice(priceToFormat:Float):String{
    val formatter = DecimalFormat("#,##0.00 €", DecimalFormatSymbols(Locale.FRANCE))
    val formattedPrice = formatter.format(priceToFormat).toString()
    return formattedPrice

}

fun Double.round(decimals: Int): Float {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (round(this * multiplier) / multiplier).toFloat()
}



@Composable
@Preview
fun App() {

    clearTotal()






    var isScanning = false

    val chargingStationsSearchResultsList = remember { mutableStateListOf<product>() }

    var totalText = mutableStateOf("-")

    //chargingStationsSearchResultsList.clear()
    //totalText.value = "0,00€"










    //println("NOOOOOOOOOOOOO")

    fun updateTotal(){
        var newTotalText = "111"// formatPrice(chargingStationsSearchResultsList.sumOf { it.price.toDouble() }.round(2))
        //println("The old text was ${totalText} and the new is ${newTotalText}")
        //totalText.value = newTotalText


    }

    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun runOnUiThread(block: suspend () -> Unit) = uiScope.launch { block() }








    SocketHandler.setSocket()
    SocketHandler.establishConnection()

    val mSocket = SocketHandler.getSocket()




    val mSocket_ = SocketHandler.getSocket()




    val coroutineScope = rememberCoroutineScope()



    // mSocket_.on("receive_total") { args ->

        //  coroutineScope.launch {

            //     var productObject = args[0].toString()

            //totalText.value = productObject
            //      Timer().schedule(timerTask {
                //totalText.value = productObject

                //      }, 100)



            //   }



        //secondCoroutineScope.launch {
            //println(isScanning.toString())
            // Timer().schedule(timerTask {
                // listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude+0.01, cameraPosition.position.target.longitude+0.01)))
                //listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude)))
                //change it to a single one, not a list
               //totalText.value = productObject
                                      // println("RECEIVED TOTALLLLLLLLLLLLLL ${productObject}")

            // }, 10)

//
        //}

        // }




    mSocket.on("receive_string") { args ->






        coroutineScope.launch {
            chargingStationsSearchResultsList.clear()



        }

        //coroutineScope.launch {
            //println(isScanning.toString())
        //        Timer().schedule(timerTask {
            // listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude+0.01, cameraPosition.position.target.longitude+0.01)))
            //listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude)))
            //change it to a single one, not a list
                       //totalText.value = "99,99€"

                        // }, 10)

//
            // }




        //println(args[0].javaClass.name) //prints args[0]'s type

        //ce code est problématique !!
        //changer de thread ou autre (voir evstationsapp)



        var productObject = args[0].toString()

        //print(productObject)

        val jsonArray = JSONArray(productObject)

        //println(jsonArray.length())

        // coroutineScope.launch {
            //println(isScanning.toString())
        //    Timer().schedule(timerTask {
                // listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude+0.01, cameraPosition.position.target.longitude+0.01)))
                //listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude)))
                //change it to a single one, not a list
                //totalText.value = jsonArray.getJSONObject(jsonArray.length()-1).getString("total")

        //   }, 1)

//
        // }




        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val number = jsonObject.getString("number")
            //val total = jsonObject.getString("total")
            val price = jsonObject.getFloat("price")
            val name = jsonObject.getString("name")

            //println(name)

            coroutineScope.launch {
                //println(isScanning.toString())
                Timer().schedule(timerTask {
                    // listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude+0.01, cameraPosition.position.target.longitude+0.01)))
                    //listOfStations.add(Marker(cameraPosition.position.target.latitude.toString(), LatLng(cameraPosition.position.target.latitude, cameraPosition.position.target.longitude)))
                    //change it to a single one, not a list
                    chargingStationsSearchResultsList.add(product(number, name, price))

                }, 1)


            }








            // }







            // Do something with the values, for example, print them

        }

        //coroutineScope.launch {
            //println(isScanning.toString())
        //  if (isScanning == true){

                //  }else{

                //chargingStationsSearchResultsList.add(product(number, name, price))

                //var newTotalText = "111"// formatPrice(chargingStationsSearchResultsList.sumOf { it.price.toDouble() }.round(2))
                //println("The old text was ${totalText} and the new is ${newTotalText}")
                //totalText.value = total

                //        totalText.value = jsonArray.getJSONObject(jsonArray.length()-1).getString("total")



                //       Timer().schedule(timerTask {

                    //           isScanning = false


                    //       }, 100)

                //    }

        //}









        //val jsonArray = JSONArray(productObject.toString())
        //val jsonObject: JSONObject = JSONObject(productObject.toString())


        //var productName = jsonObject.get("name").toString()
        //var productNumber = jsonObject.get("number").toString()
        //var productPrice = jsonObject.get("price").toString().toFloat()
        //var total = jsonObject.get("total").toString()




        //coroutineScope.launch {
            //println(isScanning.toString())
        //if (isScanning == true){

        //}else{
        //    chargingStationsSearchResultsList.add(product(productNumber, productName, productPrice))

                //var newTotalText = "111"// formatPrice(chargingStationsSearchResultsList.sumOf { it.price.toDouble() }.round(2))
                //println("The old text was ${totalText} and the new is ${newTotalText}")
               // totalText.value = total




           // }

        //}


        //println("Total : ${sum}")


    }



    Box(
        Modifier.size(300.dp, 680.dp).background(Color(0xFF2b2f31)),
    ){


        //chargingStationsSearchResultsList.add(product("","",""))
        //chargingStationsSearchResultsList.add(product("","",""))
        //chargingStationsSearchResultsList.add(product("","",""))
        //chargingStationsSearchResultsList.add(product("","",""))





        val state = rememberLazyListState()

        LazyColumn(Modifier
            .width(260.dp)
            .height(560.dp)
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .align(Alignment.TopCenter)
            , state) {
            chargingStationsSearchResultsList.forEachIndexed { index, (number, productName ,price) ->

                item {





                    //println(formattedAmount) // Output: 1,11 €

                    var priceToDisplay = formatPrice(price)




                    item(productName, priceToDisplay)








                }














            }

        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(
                scrollState = state
            )
        )

        Row (
            modifier = Modifier
                .padding(bottom = 80.dp)
                .height(60.dp)
                .width(260.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .align(Alignment.BottomCenter)
                .background(Color(0XFFFF6666))
                .zIndex(2f)
            ,
            verticalAlignment = Alignment.CenterVertically

        ){
            Text(
                "Total",
                modifier = Modifier
                    .padding(start = 20.dp),

                fontFamily = FontFamily(
                    androidx.compose.ui.text.platform.Font(
                        resource = "font.ttf"
                    )
                ),
                fontSize = (20).sp
                , color = Color(0XFF000000)
            )
            Spacer(
                modifier = Modifier
                    .weight(1.0f)
            )
            totalTextContainer(totalText.value)



        }

        Button(
            onClick = {

                chargingStationsSearchResultsList.clear()
                //totalText.value = "0,00€"
                clearTotal()


        },
            modifier = Modifier
                .align(Alignment.BottomCenter)


        ){
            Text(text = "Clear")

        }
    }












    //fun socketclientmain() {
    //    val socket = Socket("127.0.0.1", 5005)
    //    val ins = BufferedReader(InputStreamReader(socket.getInputStream()))


    //}
    //socketclientmain()



}



fun main() = application {
    val state = WindowState(size = DpSize(Dp.Unspecified, Dp.Unspecified))


    Window(onCloseRequest = ::exitApplication, state=state) {
        App()
    }
}


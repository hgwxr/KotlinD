package com.hgwxr.kotlintest.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hgwxr.gameplane.GameHomeActivity
import com.hgwxr.gametiantianshuqianlibrary.tiantianshuqian.TianTianShuQianActivity
import com.hgwxr.gestures.GesturesActivity
import com.hgwxr.hello_jni.HelloJniActivity
import com.hgwxr.kotlintest.R
import com.hgwxr.navigationlibrary.NavigationActivity
import com.hgwxr.rekotlin.ReKotlinActivity
import com.hgwxr.retrofitlibrary.RetrofitActivity
import kotlinx.android.synthetic.main.data_item.view.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.*
import javax.net.SocketFactory


class DataAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = mutableListOf<DataItem>()

    init {
        list.add(
            DataItem(
                "",
                "retrofit-demo",
                View.OnClickListener { RetrofitActivity.start(context as Activity) })
        )
        list.add(
            DataItem(
                "",
                "Navigation-demo",
                View.OnClickListener { NavigationActivity.start(context as Activity) })
        )
        list.add(
            DataItem(
                "",
                "Game-demo",
                View.OnClickListener { GameHomeActivity.start(context as Activity) }
            ))
        list.add(
            DataItem(
                "",
                "TianTianShuQian-demo",
                View.OnClickListener { TianTianShuQianActivity.start(context as Activity) }
            ))
        list.add(
            DataItem(
                "",
                "re-kotlin",
                View.OnClickListener { ReKotlinActivity.start(context as Activity) }
            ))
        list.add(
            DataItem(
                "",
                "gesture",
                View.OnClickListener {

                    GesturesActivity.start(context as Activity)
                }
            ))
        list.add(
            DataItem(
                "",
                "helloJni",
                View.OnClickListener {
                    HelloJniActivity.start(context as Activity)
                }
            ))
        list.add(
            DataItem(
                "",
                "socket",
                View.OnClickListener {
                    Thread() {
                        kotlin.run {
//                            testSocket()
                            sendHTTP()

                        }
                    }.start()
                }
            ))
    }


    private val URL = "www.baidu.com"
    private val CONTENT =
        "GET http://www.baidu.com/  HTTP/1.1\r\nHost: www.baidu.com\r\n\r\n"
    private val PORT = 80

    fun sendHTTP() {
        try {
            val socket = Socket(URL, PORT) //建立TCP/IP链接
            val out: OutputStream = socket.getOutputStream()
            val `in` =
                BufferedReader(InputStreamReader(socket.getInputStream(), "UTF-8"))
            out.write(CONTENT.toByteArray()) //发送数据
            var d = -1
            while (`in`.read().also { d = it } != -1) { //接收
                print(d.toChar()) //输出到控制台
            }
        } catch (e: UnknownHostException) { // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) { // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }
    fun testSocket() {
        val u = "www.baidu.com"
        val address = InetAddress.getByName(u)
        val socket = Socket(address, 80)
        Log.e("======>NET", "" + socket.isConnected)

        val select = ProxySelector.getDefault().select(URI("http://" + u))
        for (proxy in select) {
            val message = proxy.address()
            Log.e("======>NET0", "" + message)
            if (message != null) {
                val inetSocketAddress = message as InetSocketAddress
                //                val socket1 = Socket(inetSocketAddress.hostName, inetSocketAddress.port)
                val proxy1 = Proxy(Proxy.Type.HTTP, inetSocketAddress)
                val socket1 = SocketFactory.getDefault().createSocket()

                val epoint = inetSocketAddress
                val addr = epoint.address
                val port = epoint.port

                val security = System.getSecurityManager()
                if (security != null) {
                    if (epoint.isUnresolved) security.checkConnect(
                        epoint.hostName,
                        port
                    ) else security.checkConnect(addr.hostAddress, port)
                }
                try {
                    socket1.connect(epoint, 3000)
                }catch (e:Exception){
                    e.printStackTrace()
                }
                connect(socket1)
                //               Platform.get()
                //                    .connectSocket(rawSocket, route.socketAddress(), connectTimeout)
                Log.e("======>NET12", "" + socket1.isConnected)
                Log.e("======>NET1", "${inetSocketAddress.hostName}   ${inetSocketAddress.port}")
            } else {
                Log.e("======>else  NET2", address.hostAddress)
//                val socket2 = SocketFactory.getDefault().createSocket()
//                socket2.connect(socket.remoteSocketAddress, 3000)
                connect(socket)
            }

        }
        Log.e("======>NET2", address.hostAddress)

//    Socket()
    }

    private fun connect(socket1: Socket) {

        val inputStream = socket1.getInputStream()
        val outputStream = socket1.getOutputStream()
        val s = "GET http://www.baidu.com/  HTTP/1.1\r\nHost: www.baidu.com\r\n\r\n"
        outputStream.write(s.toByteArray());

        val bufferedInputStream = BufferedReader(InputStreamReader(inputStream))
        var readLine = bufferedInputStream.readLine()
        while ((readLine.isNotEmpty())) {
            Log.e("======>NET12--->", "" +readLine)
            readLine = bufferedInputStream.readLine()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.bindData(list[position])
            }
            else -> {
            }
        }
    }
}


class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(item: DataItem) {
        itemView.item_data_tv.text = item.title
        itemView.item_root.setOnClickListener {
            item.jumpAction.onClick(it)
        }
    }
}

data class DataItem(val icon: String, val title: String, val jumpAction: View.OnClickListener) {}
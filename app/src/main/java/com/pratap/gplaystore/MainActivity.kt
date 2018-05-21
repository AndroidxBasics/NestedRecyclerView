package com.pratap.gplaystore

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.pratap.gplaystore.adapters.RecyclerViewDataAdapter
import com.pratap.gplaystore.models.Data
import com.pratap.gplaystore.models.Section

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.lang.reflect.Type

import cz.msebera.android.httpclient.Header
import java.util.*
import java.util.Arrays.asList
import com.google.common.collect.Lists.newArrayList

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var pDialog: ProgressDialog? = null

    internal var allSampleData: MutableList<Data>? = null

//www
    private val TEST_URL = "http://monaasoft.com/indianfm/api/test.json"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar) as Toolbar

        allSampleData = ArrayList()

        if (toolbar != null) {
            setSupportActionBar(toolbar)
            toolbar!!.title = "G PlayStore"

        }


//        var myList = ArrayList<Section>(asList(
//                Section("name", "https://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png"),
//                Section("asd", "https://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png")
//
//        ))

//        var myList = newArrayList<Section>(
//                Section("name", "https://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png"),
//                Section("asd", "https://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png")
//        )

//        allSampleData!!.add(Data("my section", myList ))
//        allSampleData!!.add(Data("my next section", myList ))


        allSampleData = newArrayList<Data>(
                Data("first", newArrayList<Section>(
                        Section("nouns", "https://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png"),
                        Section("ajd.", "https://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png")
                )),
                Data("second", newArrayList<Section>(
                        Section("verbs", "https://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png")

                ))
        )

        if (allSampleData != null) {


            val my_recycler_view = findViewById(R.id.my_recycler_view) as RecyclerView

            my_recycler_view.setHasFixedSize(true)

            val adapter = RecyclerViewDataAdapter(this@MainActivity, allSampleData)

            my_recycler_view.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            my_recycler_view.adapter = adapter


        }
//www
     //   testWebService(TEST_URL)


    }


    fun showProgressDialog() {

        pDialog = ProgressDialog(this@MainActivity)
        pDialog!!.setMessage("Please wait...")
        pDialog!!.setCancelable(false)

        if (!pDialog!!.isShowing)
            pDialog!!.show()
    }

    fun dismissProgressDialog() {
        if (pDialog != null)
            pDialog!!.dismiss()

    }

    //www
    fun testWebService(url: String) {
        showProgressDialog()

        val client = AsyncHttpClient()
        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>?, response: JSONObject?) {
                dismissProgressDialog()

                if (statusCode == 200 && response != null) {
                    Log.i("response-", response.toString())


                    try {
                        val dataArary = response.getJSONArray("data")



                        for (i in 0..dataArary.length() - 1) {
                            val sectionObj = dataArary.get(i) as JSONObject

                            val title = sectionObj.getString("title")


                            val sections = ArrayList<Section>()


                            val sectionsArray = sectionObj.getJSONArray("section")

                            for (j in 0..sectionsArray.length() - 1) {

                                val obj = sectionsArray.get(j) as JSONObject


                                val section = Section()

                                section.name = obj.getString("name")
                                section.image = obj.getString("image")


                                sections.add(section)
                            }


                            val data = Data()

                            data.title = title
                            data.section = sections


                            allSampleData!!.add(data)
                            for (i in 0..allSampleData!!.size - 1) {
                                println("allSampleData ${allSampleData!![i].section!![0].name}")
                            }
                        }


                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity, "Parsing Error", Toast.LENGTH_SHORT).show()
                    }


                    // Converst json to Object Model data
                    /* Gson gson = new Gson();
                    Type collectionType = new TypeToken<Data>() {
                    }.getType();
                    allSampleData = gson.fromJson(response.toString(), collectionType);*/


                    // setting data to RecyclerView

                    if (allSampleData != null) {


                        val my_recycler_view = findViewById(R.id.my_recycler_view) as RecyclerView

                        my_recycler_view.setHasFixedSize(true)

                        val adapter = RecyclerViewDataAdapter(this@MainActivity, allSampleData)

                        my_recycler_view.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

                        my_recycler_view.adapter = adapter


                    }


                } else {
                    Toast.makeText(this@MainActivity, "Connection Error", Toast.LENGTH_SHORT).show()

                }


            }

            override fun onFailure(statusCode: Int, headers: Array<Header>?, responseString: String?, throwable: Throwable) {
                dismissProgressDialog()
                Toast.makeText(this@MainActivity, "Connection Error", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>?, throwable: Throwable, errorResponse: JSONObject?) {
                dismissProgressDialog()
                Toast.makeText(this@MainActivity, "Connection Error", Toast.LENGTH_SHORT).show()
            }
        })
    }


    /* public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            SectionDataModel dm = new SectionDataModel();

            dm.setTitle("Section " + i);

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }

            dm.setSection(singleItem);

            allSampleData.add(dm);

        }
    }*/
}

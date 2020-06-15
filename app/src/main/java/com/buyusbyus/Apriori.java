package com.buyusbyus;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.buyusbyus.Model.Rules;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Apriori  extends AppCompatActivity {
    private RulesAdapter adapter;

    List<Rules> rulesList = new ArrayList<>();
    List<Rules> rules = new ArrayList<>();





    public List<Rules> getRules() {
        String json = "{\"items\":[{\"itemA\":\"Mini beignets pomme\",\"itemB\":\"Mini beignets framboise\"},{\"itemA\":\"escalope\",\"itemB\":\"mushroom cream sauce\"},{\"itemA\":\"pasta\",\"itemB\":\"escalope\"},{\"itemA\":\"fromage blanc\",\"itemB\":\"honey\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"ground beef\"},{\"itemA\":\"tomato sauce\",\"itemB\":\"ground beef\"},{\"itemA\":\"olive oil\",\"itemB\":\"light cream\"},{\"itemA\":\"olive oil\",\"itemB\":\"whole wheat pasta\"},{\"itemA\":\"shrimp\",\"itemB\":\"pasta\"},{\"itemA\":\"milk\",\"itemB\":\"avocado\"},{\"itemA\":\"burgers\",\"itemB\":\"cake\"},{\"itemA\":\"burgers\",\"itemB\":\"chocolate\"},{\"itemA\":\"burgers\",\"itemB\":\"milk\"},{\"itemA\":\"cake\",\"itemB\":\"tomatoes\"},{\"itemA\":\"cereals\",\"itemB\":\"ground beef\"},{\"itemA\":\"chicken\",\"itemB\":\"milk\"},{\"itemA\":\"chicken\",\"itemB\":\"nan\"},{\"itemA\":\"chicken\",\"itemB\":\"olive oil\"},{\"itemA\":\"chicken\",\"itemB\":\"olive oil\"},{\"itemA\":\"shrimp\",\"itemB\":\"chocolate\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"chocolate\"},{\"itemA\":\"soup\",\"itemB\":\"milk\"},{\"itemA\":\"ground beef\",\"itemB\":\"cooking oil\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"eggs\"},{\"itemA\":\"red wine\",\"itemB\":\"eggs\"},{\"itemA\":\"nan\",\"itemB\":\"escalope\"},{\"itemA\":\"pasta\",\"itemB\":\"nan\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"french fries\"},{\"itemA\":\"nan\",\"itemB\":\"fromage blanc\"},{\"itemA\":\"tomatoes\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"frozen vegetables\",\"itemB\":\"ground beef\"},{\"itemA\":\"olive oil\",\"itemB\":\"milk\"},{\"itemA\":\"soup\",\"itemB\":\"milk\"},{\"itemA\":\"tomatoes\",\"itemB\":\"milk\"},{\"itemA\":\"shrimp\",\"itemB\":\"mineral water\"},{\"itemA\":\"olive oil\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"shrimp\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"shrimp\",\"itemB\":\"tomatoes\"},{\"itemA\":\"tomatoes\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"grated cheese\",\"itemB\":\"ground beef\"},{\"itemA\":\"tomatoes\",\"itemB\":\"ground beef\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"milk\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"mineral water\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"nan\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"ground beef\"},{\"itemA\":\"olive oil\",\"itemB\":\"milk\"},{\"itemA\":\"soup\",\"itemB\":\"milk\"},{\"itemA\":\"tomato sauce\",\"itemB\":\"nan\"},{\"itemA\":\"pepper\",\"itemB\":\"ground beef\"},{\"itemA\":\"shrimp\",\"itemB\":\"ground beef\"},{\"itemA\":\"tomato sauce\",\"itemB\":\"ground beef\"},{\"itemA\":\"mineral water\",\"itemB\":\"spaghetti\"},{\"itemA\":\"nan\",\"itemB\":\"olive oil\"},{\"itemA\":\"shrimp\",\"itemB\":\"olive oil\"},{\"itemA\":\"soup\",\"itemB\":\"olive oil\"},{\"itemA\":\"olive oil\",\"itemB\":\"milk\"},{\"itemA\":\"soup\",\"itemB\":\"milk\"},{\"itemA\":\"whole wheat pasta\",\"itemB\":\"milk\"},{\"itemA\":\"soup\",\"itemB\":\"olive oil\"},{\"itemA\":\"olive oil\",\"itemB\":\"mineral water\"},{\"itemA\":\"nan\",\"itemB\":\"olive oil\"},{\"itemA\":\"shrimp\",\"itemB\":\"nan\"},{\"itemA\":\"olive oil\",\"itemB\":\"pancakes\"},{\"itemA\":\"olive oil\",\"itemB\":\"tomatoes\"},{\"itemA\":\"whole wheat rice\",\"itemB\":\"tomatoes\"},{\"itemA\":\"nan\",\"itemB\":\"milk\"},{\"itemA\":\"burgers\",\"itemB\":\"cake\"},{\"itemA\":\"burgers\",\"itemB\":\"nan\"},{\"itemA\":\"burgers\",\"itemB\":\"nan\"},{\"itemA\":\"nan\",\"itemB\":\"cake\"},{\"itemA\":\"nan\",\"itemB\":\"cereals\"},{\"itemA\":\"chicken\",\"itemB\":\"nan\"},{\"itemA\":\"chicken\",\"itemB\":\"nan\"},{\"itemA\":\"chicken\",\"itemB\":\"nan\"},{\"itemA\":\"eggs\",\"itemB\":\"mineral water\"},{\"itemA\":\"mineral water\",\"itemB\":\"chocolate\"},{\"itemA\":\"chocolate\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"milk\",\"itemB\":\"mineral water\"},{\"itemA\":\"milk\",\"itemB\":\"chocolate\"},{\"itemA\":\"shrimp\",\"itemB\":\"mineral water\"},{\"itemA\":\"shrimp\",\"itemB\":\"nan\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"nan\"},{\"itemA\":\"soup\",\"itemB\":\"milk\"},{\"itemA\":\"olive oil\",\"itemB\":\"mineral water\"},{\"itemA\":\"shrimp\",\"itemB\":\"mineral water\"},{\"itemA\":\"nan\",\"itemB\":\"ground beef\"},{\"itemA\":\"milk\",\"itemB\":\"mineral water\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"nan\"},{\"itemA\":\"red wine\",\"itemB\":\"nan\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"french fries\"},{\"itemA\":\"mineral water\",\"itemB\":\"frozen smoothie\"},{\"itemA\":\"nan\",\"itemB\":\"tomatoes\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"milk\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"mineral water\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"nan\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"olive oil\",\"itemB\":\"mineral water\"},{\"itemA\":\"soup\",\"itemB\":\"mineral water\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"nan\",\"itemB\":\"olive oil\"},{\"itemA\":\"soup\",\"itemB\":\"nan\"},{\"itemA\":\"nan\",\"itemB\":\"tomatoes\"},{\"itemA\":\"shrimp\",\"itemB\":\"nan\"},{\"itemA\":\"shrimp\",\"itemB\":\"mineral water\"},{\"itemA\":\"mineral water\",\"itemB\":\"tomatoes\"},{\"itemA\":\"nan\",\"itemB\":\"olive oil\"},{\"itemA\":\"shrimp\",\"itemB\":\"nan\"},{\"itemA\":\"shrimp\",\"itemB\":\"nan\"},{\"itemA\":\"nan\",\"itemB\":\"tomatoes\"},{\"itemA\":\"nan\",\"itemB\":\"grated cheese\"},{\"itemA\":\"nan\",\"itemB\":\"tomatoes\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"nan\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"nan\"},{\"itemA\":\"herb & pepper\",\"itemB\":\"nan\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"nan\",\"itemB\":\"olive oil\"},{\"itemA\":\"soup\",\"itemB\":\"nan\"},{\"itemA\":\"olive oil\",\"itemB\":\"mineral water\"},{\"itemA\":\"pancakes\",\"itemB\":\"mineral water\"},{\"itemA\":\"mineral water\",\"itemB\":\"tomatoes\"},{\"itemA\":\"nan\",\"itemB\":\"pepper\"},{\"itemA\":\"shrimp\",\"itemB\":\"nan\"},{\"itemA\":\"tomato sauce\",\"itemB\":\"nan\"},{\"itemA\":\"nan\",\"itemB\":\"mineral water\"},{\"itemA\":\"olive oil\",\"itemB\":\"mineral water\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"shrimp\",\"itemB\":\"nan\"},{\"itemA\":\"soup\",\"itemB\":\"nan\"},{\"itemA\":\"nan\",\"itemB\":\"olive oil\"},{\"itemA\":\"soup\",\"itemB\":\"nan\"},{\"itemA\":\"nan\",\"itemB\":\"whole wheat pasta\"},{\"itemA\":\"soup\",\"itemB\":\"nan\"},{\"itemA\":\"nan\",\"itemB\":\"mineral water\"},{\"itemA\":\"nan\",\"itemB\":\"pancakes\"},{\"itemA\":\"nan\",\"itemB\":\"olive oil\"},{\"itemA\":\"nan\",\"itemB\":\"whole wheat rice\"},{\"itemA\":\"mineral water\",\"itemB\":\"chocolate\"},{\"itemA\":\"mineral water\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"frozen vegetables\",\"itemB\":\"chocolate\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"milk\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"mineral water\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"mineral water\",\"itemB\":\"chocolate\"},{\"itemA\":\"mineral water\",\"itemB\":\"shrimp\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"milk\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"mineral water\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"mineral water\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"mineral water\",\"itemB\":\"frozen vegetables\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"mineral water\",\"itemB\":\"spaghetti\"},{\"itemA\":\"mineral water\",\"itemB\":\"spaghetti\"},{\"itemA\":\"mineral water\",\"itemB\":\"spaghetti\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"},{\"itemA\":\"mineral water\",\"itemB\":\"milk\"}]}";

        Gson gson = new Gson();
        Exemple example = gson.fromJson(json,Exemple.class);

        //adding our stringrequest to queue

        return example.getItems();

    }



}
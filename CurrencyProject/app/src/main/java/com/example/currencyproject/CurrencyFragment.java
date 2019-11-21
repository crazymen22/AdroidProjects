package com.example.currencyproject;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CurrencyFragment extends Fragment {

    private List<String> siteList;

    public CurrencyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency, container, false);

        siteList = new ArrayList<>();
        siteList = Arrays.asList("https://minfin.com.ua/ua/currency/", "https://finance.i.ua/", "https://creditdnepr.com.ua/currency");

        Spinner siteSpinner = view.findViewById(R.id.siteList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, siteList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        siteSpinner.setAdapter(adapter);
        return view;
    }

//    private class ProgressTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... path) {
//
//            String content;
//            try {
//                content = getContent(path[0]);
//            } catch (IOException ex) {
//                content = ex.getMessage();
//            }
//
//            return content;
//        }
//
//        @Override
//        protected void onPostExecute(String content) {
//
//            contentText = content;
//            contentView.setText(content);
//            webView.loadData(content, "text/html; charset=utf-8", "utf-8");
//            Toast.makeText(getActivity(), "Данные загружены", Toast.LENGTH_SHORT)
//                    .show();
//        }
//
//        private String getContent(String path) throws IOException {
//            BufferedReader reader = null;
//            try {
//                URL url = new URL(path);
//                HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
//                c.setRequestMethod("GET");
//                c.setReadTimeout(10000);
//                c.connect();
//                reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
//                StringBuilder buf = new StringBuilder();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    buf.append(line + "\n");
//                }
//                return (buf.toString());
//            } finally {
//                if (reader != null) {
//                    reader.close();
//                }
//            }
//        }
//    }
}

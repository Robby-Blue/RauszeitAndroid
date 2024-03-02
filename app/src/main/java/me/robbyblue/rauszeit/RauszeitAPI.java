package me.robbyblue.rauszeit;

import android.os.AsyncTask;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import me.robbyblue.rauszeit.day.Day;
import me.robbyblue.rauszeit.eventpreview.EventPreview;
import me.robbyblue.rauszeit.eventpreview.SearchResultEventPreview;

public class RauszeitAPI {

    public static void getAllEvents(AllEventsCallback callback) {
        new HttpRequestTask("https://rauszeit-termine.org/", (result) -> {
            Document doc = Jsoup.parse(result);

            Element eventsList = doc.getElementsByClass("css-events-list").get(0);

            ArrayList<Day> days = new ArrayList<>();

            // +=2 bc one day is two elements: h5 date and table events
            // -1 bc theres one more element at the bottom
            for (int i = 0; i < eventsList.childrenSize() - 1; i += 2) {
                String date = eventsList.child(i).text();

                days.add(new Day(date, eventsList.child(i + 1)));
            }

            callback.onResult(days);
        }).execute();
    }

    public static void getEvent(String url, EventCallback callback) {
        new HttpRequestTask(url, (result) -> {
            Document doc = Jsoup.parse(result);

            Event event = new Event(doc.getElementsByClass("article-inner").get(0));

            callback.onResult(event);
        }).execute();
    }

    public static void getSearchResults(String query, EventPreviewsCallback callback) {
        new HttpRequestTask("https://rauszeit-termine.org/?s=" + query, (result) -> {
            Document doc = Jsoup.parse(result);

            Element eventsElement = doc.getElementsByClass("content-masonry").get(0);

            ArrayList<EventPreview> events = new ArrayList<>();

            for (Element child : eventsElement.children()) {
                events.add(new SearchResultEventPreview(child));
            }

            callback.onResult(events);
        }).execute();
    }

    public static void getLocation(String url, LocationCallback callback) {
        new HttpRequestTask(url, (result) -> {
            Document doc = Jsoup.parse(result);

            Element article = doc.getElementsByClass("article-inner").get(0);

            callback.onResult(new Location(article));
        }).execute();
    }

    public interface AllEventsCallback {
        void onResult(ArrayList<Day> days);
    }

    public interface EventPreviewsCallback {
        void onResult(ArrayList<EventPreview> events);
    }

    public interface EventCallback {
        void onResult(Event event);
    }

    public interface LocationCallback {
        void onResult(Location location);
    }

    public static class HttpRequestTask extends AsyncTask<Void, String, String> {

        private final String urlString;
        private final Callback callback;

        public HttpRequestTask(String urlString, Callback callback) {
            this.urlString = urlString;
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(false);

                InputStream inputStream = connection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String res) {
            try {
                callback.onResult(res);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public interface Callback {
            void onResult(String result) throws JSONException;
        }
    }

}

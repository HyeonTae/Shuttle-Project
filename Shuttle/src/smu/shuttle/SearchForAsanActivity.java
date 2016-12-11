package smu.shuttleapp.home;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import smu.shuttleapp.R;
import smu.shuttleapp.R.id;
import smu.shuttleapp.R.layout;
import smu.shuttleapp.model.Bus;

public class SearchForAsanActivity extends Activity {
    ListView busListView = null;
    boolean asyncFlag = false;
    BusListAdapter bAdapter = null;
    ArrayList<ViewHolder> Data;
    final String serverIp = "http://192.168.0.34:8060";
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_asan);
        new GetAllBus().execute(serverIp);
        sp = getSharedPreferences("sp", MODE_PRIVATE);
        busListView = (ListView) findViewById(R.id.busList);
    }

    private class BusListAdapter extends BaseAdapter {
        Context context;

        
        public BusListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return Data.size();
        }

        @Override
        public Object getItem(int position) {
            return Data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                viewHolder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item1, null);

                viewHolder.start = (TextView)convertView.findViewById(R.id.start);
                viewHolder.last = (TextView)convertView.findViewById(R.id.last);
                viewHolder.hour = (TextView)convertView.findViewById(R.id.hour);
                viewHolder.minute = (TextView)convertView.findViewById(R.id.minute);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.start.setText(Data.get(position).getStart2());
            viewHolder.last.setText(Data.get(position).getLast2());
            viewHolder.hour.setText(Data.get(position).getHour2());
            viewHolder.minute.setText(Data.get(position).getMinute2());
            
            return convertView;
        }
    }
    
    public class ViewHolder {

        public TextView start, last, hour, minute;
        String start2,last2,hour2,minute2;

        public ViewHolder(){

        }
        public ViewHolder(String start2, String last2, String hour2, String minute2){
            this.start2 = start2;
            this.last2 = last2;
            this.hour2 = hour2;
            this.minute2 = minute2;
        }

        public String getStart2(){
            return start2;
        }
        public String getLast2(){
            return last2;
        }
        public String getHour2(){
            return hour2;
        }
        public String getMinute2(){
            return minute2;
        }

    }


    private class GetAllBus extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(serverIp + "/ShuttleServer/main.do?" + "action=classSearchBusForAsan").openConnection();

                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String data = null;
                    Data = new ArrayList<ViewHolder>();
                    ArrayList<String> arrayList = new ArrayList<String>();
                    while ((data = br.readLine()) != null) {
                        arrayList.add(data);
                    }

                    try {
                        JSONArray jsonArray = new JSONArray(arrayList.get(0));
                        for (int i = 0; i< jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Data.add(new ViewHolder(jsonObject.getString("dep"), jsonObject.getString("dest"), jsonObject.getString("hour"), jsonObject.getString("min")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // Toast.makeText(getApplicationContext(), "GetAllDebt :
            // "+darr.toString(), Toast.LENGTH_SHORT).show();
            super.onPostExecute(result);

            bAdapter = new BusListAdapter(getApplicationContext());
            busListView.setAdapter(bAdapter);
            bAdapter.notifyDataSetChanged();
        }
    }
}

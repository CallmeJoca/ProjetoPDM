package pdm.ubi5stars;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class ViewMonument extends Activity {

    private DatabaseHandler DBhelper;
    String nomeMonumento;
    ListView lv;
    ListViewAdapterComentarios lv_adapt;
    ArrayList<Com> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_monument);
        ViewMonument.this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle(nomeMonumento);
        //setActionBar(toolbar);

        Intent iCameFromHomescreen = getIntent();
        //IDMonumento = iCameFromHomescreen.getStringExtra("Nome");

        lv = findViewById(R.id.list_comentarios);
        items = DBhelper.getAllComentariosMonumento(0);
        Log.i("iTEMS", String.valueOf(items.size()));
        lv_adapt = new ListViewAdapterComentarios(this, R.layout.item_row, items);
        lv.setAdapter(lv_adapt);

        TextView publicidade = findViewById(R.id.publi);
        publicidade.setText(DBhelper.showPubli(DBhelper.getPubli()));
    }

    public void goMaps(View v) {

        DBhelper = new DatabaseHandler(ViewMonument.this);

        String localizacao = DBhelper.getLocalização("UBI");
        String[] parts = localizacao.split(",");
        String latitude = parts[0];
        String longitude = parts[1];

        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude;
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(mapIntent);
    }

    public void goBack(View v) {
        Intent iGoToHomescreen = new Intent();
        setResult(RESULT_OK,iGoToHomescreen);
        super.finish();
    }
}

//PERMISSOES NO MANIFESTO.
//<uses-permission android:name="android.permission.INTERNET"/>
//<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

//Coordenadas UBI 40°16'40.3"N 7°30'32.5"W
// NATA 40.280756, -7.504680
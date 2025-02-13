package pdm.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UBI5stars extends Activity {

    DatabaseHandler DBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubi5stars);

        DBhelper = new DatabaseHandler(this);
        DBhelper.getWritableDatabase();
        DBhelper.populateDatabase();
    }

    // Completar com a o nome da classe (activity) para onde ir.
    public void handleButPesquisa (View v) {

        Intent iGoSearch = new Intent(this, SearchMonument.class);
        startActivity(iGoSearch);
    }

    // Completar com a o nome da classe (activity) para onde ir.
    public void handleButVerMapa (View v) {
        Intent iGoToMapa = new Intent(this, Mapa.class);
        startActivity(iGoToMapa);
    }
}

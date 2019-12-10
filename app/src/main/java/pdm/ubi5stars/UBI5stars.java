package pdm.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UBI5stars extends Activity {

    DatabaseHandler DBhelper;
    String monumento = "UBI";

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
        Intent iGoToPesquisa = new Intent(this, AddComment.class);
        iGoToPesquisa.putExtra("monumento", monumento);
        startActivity(iGoToPesquisa);
    }

    // Completar com a o nome da classe (activity) para onde ir.
    public void handleButVerMapa (View v) {
        //Intent iGoToVerMapa = new Intent(this, ... );
        //startActivity(iGoToVerMapa);
    }
}

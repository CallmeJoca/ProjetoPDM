package pdm.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class Mapa extends Activity {

    ListView monumentos;
    Toolbar toolbar;
    ArrayAdapter<String> mons;
    ArrayList<Mon> all_monuments;
    ArrayList<String> lala;
    DatabaseHandler DBhelper;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Mapa & Monumentos");

        monumentos = findViewById(R.id.list_monumentos);

        /* ELIMINAR AS 3 LINHAS SEGUINTES E Pôr o UBI5STARS EM LAUNCHER */
        DBhelper = new DatabaseHandler(this);
        DBhelper.getWritableDatabase();
        DBhelper.populateDatabase();

        all_monuments = new ArrayList<>();
        all_monuments = DBhelper.getAllMonumentos();
        lala = new ArrayList<>();

        for (i=0; i<all_monuments.size(); i++) {
            lala.add(all_monuments.get(i).nome);
        }

        mons = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lala);
        monumentos.setAdapter(mons);

        monumentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //obtem-se o nome do item que foi clicado
                String nomeMonumento = parent.getItemAtPosition(position).toString();
                //vai-se buscar o ID do nome do Pessegueiro clicado, ao getId
                ArrayList<Mon> todosMon = DBhelper.allMonumentos();
                int id_Mon = DBhelper.searchIDmonumento(nomeMonumento, todosMon);
                Toast.makeText(getApplicationContext(), "ID = " + id_Mon , Toast.LENGTH_SHORT).show();

                Intent iGoToViewMonument = new Intent(Mapa.this, ViewMonument.class);
                iGoToViewMonument.putExtra("Nome", nomeMonumento);
                iGoToViewMonument.putExtra("ID", id_Mon);
                startActivityForResult(iGoToViewMonument, 5);
            }
        });




    }


}

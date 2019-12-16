package pdm.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

// esta atividade representa a pagina de pesquisa de monumentos por nome/tipo
public class SearchMonument extends Activity {

    private DatabaseHandler dbHelper;
    ArrayList<Mon> monumentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_monument);
        dbHelper = new DatabaseHandler(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        dbHelper.getWritableDatabase();
    }
    @Override
    protected void onPause() {
        super.onPause();
        dbHelper.close();
    }
    // abrir a página individual do monumento que o utilizador pretende consultar
    public void onOpenClick(View view) {
        Intent iOpenViewMonument = new Intent(this, ViewMonument.class);
        iOpenViewMonument.putExtra("monumento", monumentos);
        startActivity(iOpenViewMonument);
    }
    // efetuar a pesquisa e construir o layout para apresentar os resultados desta pesquisa
    public void onSearchClick(View view) {

        LinearLayout oItemWindow = (LinearLayout) findViewById(R.id.small_window);
        EditText oSearchExpression = (EditText) findViewById(R.id.search_expression);
        Spinner oDropdownSearchOptions = (Spinner) findViewById(R.id.spinner);

        monumentos = new ArrayList<>();
        monumentos = dbHelper.allMonumentos();

        for (Mon i : monumentos){

            LinearLayout oLL1 = (LinearLayout) getLayoutInflater().inflate(R.layout.monument_row, null);
            oLL1.setId(i.id * 10);

            TextView oMonumentName = (TextView) oLL1.findViewById(R.id.monument_name);
            oMonumentName.setId(i.id * 10 + 1);
            oMonumentName.setText(i.nome);

            TextView oMonumentCategory = (TextView) oLL1.findViewById(R.id.monument_category);
            oMonumentCategory.setId(i.id * 10 + 2);
            oMonumentCategory.setText(i.categoria);

            TextView oMonumentLocation = (TextView) oLL1.findViewById(R.id.localizacao);
            oMonumentLocation.setId(i.id * 10 + 3);
            oMonumentLocation.setText(i.localizacao);

            Button oButao = (Button) oLL1.findViewById(R.id.butao_abrir_monumento);
            oButao.setId(i.id * 10 + 4);
            oItemWindow.addView(oLL1);
        }
    }
}


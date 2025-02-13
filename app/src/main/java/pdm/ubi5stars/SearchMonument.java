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
import android.widget.Toast;

import java.util.ArrayList;

// esta atividade representa a pagina de pesquisa de monumentos por nome/tipo
public class SearchMonument extends Activity {

    private DatabaseHandler dbHelper;
    ArrayList<Mon> monumentos;

    @Override
    // gerar o layout principal e definir o gestor de base de dados
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_monument);
        dbHelper = new DatabaseHandler(this);
    }
    @Override
    // carregar o gestor de base de dados
    protected void onResume() {
        super.onResume();
        dbHelper.getWritableDatabase();
    }
    @Override
    // fechar o gestor de base de dados
    protected void onPause() {
        super.onPause();
        dbHelper.close();
    }
    // abrir a página individual do monumento que o utilizador pretende consultar
    public void onOpenClick(View view) {

        LinearLayout oLL1 = (LinearLayout) getLayoutInflater().inflate(R.layout.monument_row, null);
        TextView oMonumentName = (TextView) oLL1.findViewById(R.id.monument_name);

        Intent iOpenViewMonument = new Intent(this, ViewMonument.class);
        iOpenViewMonument.putExtra("Nome", oMonumentName.getText().toString());
        iOpenViewMonument.putExtra("ID", dbHelper.searchIDmonumento(oMonumentName.getText().toString(), dbHelper.allMonumentos()));

        startActivity(iOpenViewMonument);
    }
    // efetuar a pesquisa e construir o layout para apresentar os resultados desta pesquisa
    public void onSearchClick(View view) {

        LinearLayout oItemWindow = (LinearLayout) findViewById(R.id.small_window);
        EditText oSearchExpression = (EditText) findViewById(R.id.search_expression);
        Spinner oDropdownSearchOptions = (Spinner) findViewById(R.id.spinner);

        // chamada do controlador da base de dados para receber os campos que interessam ao utilizador

        monumentos = new ArrayList<>();
        if (oDropdownSearchOptions.getSelectedItem().toString().equals("Tudo")) {
            monumentos = dbHelper.allMonumentos();

        }
        else {
            monumentos = dbHelper.someMonumentos(oDropdownSearchOptions.getSelectedItem().toString() , oSearchExpression.getText().toString());
        }

        if (monumentos.size() == 0) {
            Toast.makeText(getApplicationContext(), "Nenhum resultado encontrado!", Toast.LENGTH_LONG).show();
        }

        // gerar o layout dinamico para cada resultado devolvido da base de dados
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


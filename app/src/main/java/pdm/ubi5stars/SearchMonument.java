package pdm.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.LinearLayout;
import android.widget.TextView;

// esta atividade representa a pagina de pesquisa de monumentos por nome/tipo
public class SearchMonument extends Activity {

    private SQLiteDatabase oSQLiteDB;
    private DatabaseHandler dbHelper;
    private String monumento;
    private int idMonumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_monument);

    }
    @Override
    protected void onResume() {
        super.onResume();
        oSQLiteDB = dbHelper.getWritableDatabase();
    }
    @Override
    protected void onPause() {
        super.onPause();
        dbHelper.close();
    }

    public void onOpenClick(View view) {
        Intent iOpenViewMonument = new Intent(this, ViewMonument.class);
        iOpenViewMonument.putExtra("monumento", monumento);
        startActivity(iOpenViewMonument);
    }

    public void onSearchClick(View view) {

        dbHelper = new DatabaseHandler(this);
        oSQLiteDB = dbHelper.getWritableDatabase();

        LinearLayout oItemWindow = (LinearLayout) findViewById(R.id.small_window);
        EditText oSearchExpression = (EditText) findViewById(R.id.search_expression);
        Spinner oDropdownSearchOptions = (Spinner) findViewById(R.id.spinner);

        Cursor oCursor = oSQLiteDB.query(String.valueOf(dbHelper.allMonumentos()), new String[]{"*"},null,null,null,null,null,null);

        boolean bCarryOn = oCursor.moveToFirst();
        while(bCarryOn) {

            LinearLayout oLL1 = (LinearLayout) getLayoutInflater().inflate(R.layout.monument_row, null);
            oLL1.setId(oCursor.getInt(0));

            TextView oMonumentName = (TextView) oLL1.findViewById(R.id.monument_name);
            oMonumentName.setId(oCursor.getInt(0));
            oMonumentName.setText(oCursor.getString(1));

            TextView oMonumentCategory = (TextView) oLL1.findViewById(R.id.monument_category);
            oMonumentCategory.setId(oCursor.getInt(0));
            oMonumentCategory.setText(oCursor.getString(2));

            TextView oMonumentLocation = (TextView) oLL1.findViewById(R.id.localizacao);
            oMonumentLocation.setId(oCursor.getInt(0));
            oMonumentLocation.setText(oCursor.getString(3));

            Button oButao = (Button) oLL1.findViewById(R.id.butao_abrir_monumento);
            oButao.setId(oCursor.getInt(0));
            oItemWindow.addView(oLL1);

            bCarryOn = oCursor.moveToNext();
        }


        oCursor.close();
    }
}


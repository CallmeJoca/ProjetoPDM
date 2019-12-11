package pdm.ubi5stars;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ScrollView;
import android.widget.LinearLayout;

// esta atividade representa o a pagina de pesquisa de monumentos por nome/tipo
public class SearchMonument extends Activity {

    private DatabaseHandler dbHelper;
    private String monumento;
    private int idMonumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_monument);

        dbHelper = new DatabaseHandler(this);

        EditText oSearchExpression = (EditText) findViewById(R.id.search_expression);
        Spinner oDropdownSearchOptions = (Spinner) findViewById(R.id.spinner);
        ScrollView oScrollView = (ScrollView) findViewById(R.id.scroll_window);
        LinearLayout oItemWindow = (LinearLayout) findViewById(R.id.small_window);



    }
    @Override
    protected void onPause() {

        super.onPause();
        dbHelper.close();
    }

}


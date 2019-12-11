package pdm.ubi5stars;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.widget.EditText;
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




    }
    @Override
    protected void onPause() {

        super.onPause();
        dbHelper.close();
    }

}


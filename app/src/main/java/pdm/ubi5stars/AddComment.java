package pdm.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// esta atividade representa o formulário de adicionar um comentário relativo a um monumento
public class AddComment extends Activity {

    private DatabaseHandler dbHelper;
    private RatingBar rBar;
    private float classificacao;
    private String monumento;
    private int idMonumento;
    private ArrayList<Mon> allMonumentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comentario);

        dbHelper = new DatabaseHandler(this);

        // é preciso receber o intento que especifica que monumento queremos comentar
        Bundle bundle  = getIntent().getExtras();

        // `monumento` guarda o nome do monumento selecionado na atividade anterior
        monumento = bundle.getString("monumento");

        // saber o id do monumento
        allMonumentos = dbHelper.allMonumentos();
        idMonumento = dbHelper.searchIDmonumento(monumento, allMonumentos);

        Toast.makeText(getApplicationContext(), "idMonumento: "+ String.valueOf(idMonumento), Toast.LENGTH_SHORT).show();

        // saber que quantidade de estrelas foram selecionadas na RatingBar
        rBar = (RatingBar) findViewById(R.id.ratingBar);

        rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                // `rating` guarda o valor da classificação escolhida
                classificacao = rBar.getRating();

                // mostra o número de estrelas selecionadas
                Toast.makeText(getApplicationContext(), "Rating: "+ String.valueOf(classificacao), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {

        super.onPause();
        dbHelper.close();
    }

    // método que regressa à atividade anterior
    // neste caso volta à atividade que mostra as informações relativas ao monumento que estamos a comentar
    public void goBack(View v) {

        Intent iGoToInfo = new Intent();
        setResult(RESULT_OK, iGoToInfo);
        super.finish();
    }

    /* É PRECISO API26 PARA USAR A DATA

    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    */

    public void inserirComentarioBD(View v) {

        EditText text = (EditText) findViewById (R.id.comment);

        // inserir comentário
        Com c;

        try {
            String comentario = text.getText().toString();

            c = new Com(comentario, idMonumento, classificacao);
            dbHelper.insertComentario(c);

        } catch (Exception e) {

        }

        finish();

        // na classe Com não é preciso especificar o id do utilizador pois, na versão base, só existe um user
        // na classe DatabaseHandler foram alterados métodos, pois a classificação é um float
    }
}

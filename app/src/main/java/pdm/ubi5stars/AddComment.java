package pdm.ubi5stars;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

// esta atividade representa o formulário de adicionar um comentário relativo a um monumento
public class AddComment extends Activity {

    private DatabaseHandler dbHelper;
    private RatingBar rBar;
    private float classificacao;
    private String monumento;
    private int idMonumento;
    private ArrayList<Mon> allMonumentos;
    private Toolbar bar;
    private TextView showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comentario);

        // personalizar título da atividade (toolbar)
        bar = (Toolbar) findViewById (R.id.toolbar);
        bar.setTitle("Adicionar Comentário");

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

                // `classificacao` guarda o valor da classificação escolhida
                classificacao = rBar.getRating();

                // mostra o número de estrelas selecionadas
                Toast.makeText(getApplicationContext(), "Rating: "+ String.valueOf(classificacao), Toast.LENGTH_SHORT).show();
            }
        });

        //mostrar data
        showData = (TextView) findViewById (R.id.data);
        showData.setText(getData());
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

    // devolve uma String com a data atual dd/MM/yyyy
    public String getData() {

        //retira o Data atual do dispositivo
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        String data = sdf.format(c.getTime());

        return data;
    }

    // insere um comentário na base de dados local
    public void inserirComentarioBD(View view) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:

                        // inserir comentário
                        EditText text = (EditText) findViewById (R.id.comment);
                        EditText nome = (EditText) findViewById (R.id.nomePessoa);
                        String d = getData();

                        Com c;

                        try {
                            String comentario = text.getText().toString();
                            String nPessoa = nome.getText().toString();

                            // verificar se os campos estão todos preenchidos
                            if(comentario.equals("") || text.equals("") || classificacao == 0.0f) {
                                Toast.makeText(AddComment.this, "Erro! Complete o formulário", Toast.LENGTH_SHORT).show();
                                break;
                            }

                            // inserir o conteúdo na BD
                            c = new Com(d, nPessoa, comentario, idMonumento, classificacao);
                            dbHelper.insertComentario(c);

                            finish();

                        } catch (Exception e) {
                            Log.w("Nada", "u");
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Tem a certeza que pertence adicionar?").setPositiveButton("SIM", dialogClickListener)
                .setNegativeButton("NÃO", dialogClickListener).show();

    }
}

package pdm.ubi5stars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapterComentarios extends ArrayAdapter<Com> {

    private String nome;
    private Context mContext;
    private int n;
    DatabaseHandler dbh;

    public ListViewAdapterComentarios(Context context, int n, List<Com> items) {
        super(context, n, items);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        dbh = new DatabaseHandler(mContext);

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(R.layout.item_row, null);
        }

        Com c = getItem(position);

        if (c != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.username);
            TextView tt2 = (TextView) v.findViewById(R.id.data);
            TextView tt3 = (TextView) v.findViewById(R.id.comentario);
            RatingBar rb = (RatingBar) v.findViewById(R.id.classificacao);


            if (tt1 != null) {
                tt1.setText(c.username);
            }

            if (tt2 != null) {
                tt2.setText(c.data);
            }

            if (tt3 != null) {
                tt3.setText(c.texto);
            }

            if (rb != null) {
                String numero_estrelas = String.valueOf(c.classificacao);
                Float num_estrelas = Float.parseFloat(numero_estrelas);
                rb.setRating(num_estrelas);
            }
        }
        return v;
    }
}
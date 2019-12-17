package pdm.ubi5stars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DB_Version=3;//versão da base de dados
    private static final String DB_Name="UBI5stars.db";//nome da base de dados

    //////////////////////////////////////////////////////////
    private static final String Table_Monumentos="Monumentos";
    //+++
    private static final String Col1_Monumento_Id="Id_Monumento";
    private static final String Col2_Monumento_Nome="Nome_Monumento";
    private static final String Col3_Monumento_Info="Info_Monumento";
    private static final String Col4_Monumento_Categoria="Categoria_Monumento";
    private static final String Col5_Monumento_Localizacao="Localizacao_Monumento";
    //+++

    //---
    private static final String Creat_Monumentos_Table= "CREATE TABLE " + Table_Monumentos + "(" +
            Col1_Monumento_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Col2_Monumento_Nome + " TEXT, " +
            Col3_Monumento_Info+ " TEXT, " +
            Col4_Monumento_Categoria + " TEXT, " +
            Col5_Monumento_Localizacao + " TEXT " + ")";
    //---



    ///////////////////////////////////////////////////////////
    private static final String Table_Comentarios="Comentarios";
    //+++
    private static final String Col1_Comentario_Id="Id_Comentario";
    private static final String Col2_Comentario_Classificacao="Classificacao_Comentario";
    private static final String Col3_Comentario_Data="Data_Comentario";
    private static final String Col4_Comentario_Id_Monumento="Id_Monumento_Comentario";
    private static final String Col15_Comentario_Username="Username";
    private static final String Col16_Comentario_Texto="Texto";
    //+++

    //---
    private static final String Creat_Comentario_Table= "CREATE TABLE " + Table_Comentarios + "(" +
            Col1_Comentario_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Col2_Comentario_Classificacao + " FLOAT, " +
            Col3_Comentario_Data + " TEXT, " +
            Col4_Comentario_Id_Monumento + " INTEGER, " +
            Col15_Comentario_Username + " TEXT, " +
            Col16_Comentario_Texto + " TEXT, " +
            " FOREIGN KEY(" + Col4_Comentario_Id_Monumento + ") " + "REFERENCES " + Table_Monumentos + "(" + Col1_Monumento_Id + ") )";

    //---

    /////////////////////////////////////////////////////////////////////////////////
    private static final String Table_Publicidades="Publicidades";
    //+++
    private static final String Col1_Publicidade_Id="Id_Publicidade";
    private static final String Col2_Publicidade_Conteudo="Conteudo_Publicidade";
    //+++

    //---
    private static final String Creat_Publicidades= "CREATE TABLE " + Table_Publicidades + "(" +
            Col1_Publicidade_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Col2_Publicidade_Conteudo + " TEXT " + ")";

    //---
    ///////////////////////////////////////////////////////////////////////////////////////////


    public DatabaseHandler(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Creat_Monumentos_Table);
        db.execSQL(Creat_Comentario_Table);
        db.execSQL(Creat_Publicidades);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  " + Table_Monumentos);
        db.execSQL("DROP TABLE IF EXISTS  " + Table_Comentarios);
        db.execSQL("DROP TABLE IF EXISTS  " + Table_Publicidades);

        onCreate(db);
    }

    public boolean verifyMonumentoExistsAlready(Mon monumento) {
        String query = "Select * from "+ Table_Monumentos +" where " + Col2_Monumento_Nome + "='" + monumento.nome + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // se já existe um monumento com o mesmo nome, supõe-se que é o próprio monumento que se quer adicionar novamente.
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }

        // se não
        else {
            cursor.close();
            return false;
        }

    }

    //Método para inserir um MONUMENTO na tabela "Table_Monumentos"
    public void insertMonumento(Mon monumento){

        if (verifyMonumentoExistsAlready(monumento)) {
            // não adiciona novamente o monumento.
        }
        else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(Col2_Monumento_Nome, monumento.getNome());
            valores.put(Col3_Monumento_Info, monumento.getInfo());
            valores.put(Col4_Monumento_Categoria, monumento.getCategoria());
            valores.put(Col5_Monumento_Localizacao, monumento.getLocalizacao());

            db.insert(Table_Monumentos, null, valores);
            db.close();
        }
    }

    public boolean verifyComentarioExistsAlready(Com comentario) {
        String query = "Select * from "+ Table_Comentarios +" where " + Col16_Comentario_Texto + "='" + comentario.texto + "' and " + Col15_Comentario_Username + "='" + comentario.username + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // se já existe um comentário igual
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }

        // se não
        else {
            cursor.close();
            return false;
        }
    }

    //Método para inserir um COMENTARIO na tabela "Table_Comentarios"
    public void insertComentario(Com comentario){

        if (verifyComentarioExistsAlready(comentario)) {
            // se já existe um comentário igual, não faz nada.
        }

        else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(Col2_Comentario_Classificacao, comentario.getClassificacao());
            valores.put(Col3_Comentario_Data, comentario.getData());
            valores.put(Col4_Comentario_Id_Monumento, comentario.getMonumento());
            valores.put(Col15_Comentario_Username, comentario.getUsername());
            valores.put(Col16_Comentario_Texto, comentario.getTexto());

            db.insert(Table_Comentarios, null, valores);
            db.close();
        }
    }

    public boolean verifyPublicidadeExistsAlready(Pub publicidade) {
        String query = "Select * from "+ Table_Publicidades +" where " + Col2_Publicidade_Conteudo + "='" + publicidade.conteudo + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // se já existe uma publicidade igual
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }

        // se não
        else {
            cursor.close();
            return false;
        }
    }
    //Método para inserir uma PUBLICIDADE na tabela "Table_Publicidade"
    public void insertPublicidade(Pub publicidade){

        if (verifyPublicidadeExistsAlready(publicidade)) {
            // se já existe uma publicidade igual a esta, não se adiciona novamente.
        }

        else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(Col2_Publicidade_Conteudo, publicidade.getConteudo());

            db.insert(Table_Publicidades, null, valores);
            db.close();
        }
    }


    //Método para eliminar um MONUMENTO
    public void deleteMonumento(int id) {
        SQLiteDatabase db =getWritableDatabase();

        // para eliminar um monumento é preciso, primeiro, eliminar os comentários referentes a esse mesmo monumento.
        String whereComentario=Col4_Comentario_Id_Monumento+"=?";
        String whereMonumento=Col1_Monumento_Id+"=?";
        String it[]={""+id};

        db.delete(Table_Monumentos,whereComentario,it);
        db.delete(Table_Monumentos,whereMonumento,it);
    }

    //Método para eliminar um Comentario
    public void deleteComentario(int id) {
        SQLiteDatabase db =getWritableDatabase();
        String where=Col1_Comentario_Id+"=?";
        String it[]={""+id};

        db.delete(Table_Comentarios,where,it);
    }

    //Método para eliminar uma Publicidade
    public void deletePublicidade(int id) {
        SQLiteDatabase db =getWritableDatabase();
        String where=Col1_Publicidade_Id+"=?";
        String it[]={""+id};

        db.delete(Table_Publicidades,where,it);
    }

    //Método para alterar Monumento
    public void updateMonumento(Mon m){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Col1_Monumento_Id, m.getId());
        valores.put(Col2_Monumento_Nome, m.getNome());
        valores.put(Col3_Monumento_Info, m.getInfo());
        valores.put(Col4_Monumento_Categoria, m.getCategoria());
        valores.put(Col5_Monumento_Localizacao, m.getLocalizacao());

        String where=Col1_Monumento_Id+"=?";
        String it[]={""+m.id};
        db.update(Table_Monumentos, valores, where, it);
    }


    //Método para alterar comentario
    public void updateComentario(Com c){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Col1_Comentario_Id, c.getId());
        valores.put(Col2_Comentario_Classificacao, c.getClassificacao());
        valores.put(Col3_Comentario_Data, c.getData());
        valores.put(Col4_Comentario_Id_Monumento, c.getMonumento());
        valores.put(Col15_Comentario_Username, c.getUsername());
        valores.put(Col16_Comentario_Texto, c.getTexto());

        String where=Col1_Comentario_Id+"=?";
        String it[]={""+c.id};
        db.update(Table_Comentarios, valores, where, it);

    }

    //Método para alterar publicidade
    public void updatePublicidade(Pub p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Col1_Publicidade_Id, p.getId());
        valores.put(Col2_Publicidade_Conteudo, p.getConteudo());

        String where=Col1_Publicidade_Id+"=?";
        String it[]={""+p.id};
        db.update(Table_Publicidades, valores, where, it);

    }

    //Método para return o Monumento
    public Mon getMonumentoid(int id) {
        Mon monumento = new Mon();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Table_Monumentos + " WHERE " + Col1_Monumento_Id + " = " + id;


        Cursor res = db.rawQuery(selectQuery, null);
        res.moveToNext();

        monumento.setId(res.getInt(res.getColumnIndex(Col1_Monumento_Id)));
        monumento.setNome(res.getString(res.getColumnIndex(Col2_Monumento_Nome)));
        monumento.setInfo(res.getString(res.getColumnIndex(Col3_Monumento_Info)));
        monumento.setCategoria(res.getString(res.getColumnIndex(Col4_Monumento_Categoria)));
        monumento.setLocalizacao(res.getString(res.getColumnIndex(Col5_Monumento_Localizacao)));

        res.close();
        db.close();
        return monumento;
    }


    //Método para return o Comentario
    public Com getComentariosid(int id) {
        Com comentario= new Com();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Table_Comentarios + " WHERE " + Col1_Comentario_Id + " = " + id;


        Cursor res = db.rawQuery(selectQuery, null);
        res.moveToNext();

        comentario.setId(res.getInt(res.getColumnIndex(Col1_Comentario_Id)));
        comentario.setClassificacao(res.getFloat(res.getColumnIndex(Col2_Comentario_Classificacao)));
        comentario.setData(res.getString(res.getColumnIndex(Col3_Comentario_Data)));
        comentario.setMonumento(res.getInt(res.getColumnIndex(Col4_Comentario_Id_Monumento)));
        comentario.setUsername(res.getString(res.getColumnIndex(Col15_Comentario_Username)));
        comentario.setTexto(res.getString(res.getColumnIndex(Col16_Comentario_Texto)));

        res.close();
        db.close();
        return comentario;
    }

    //Método para return o Publicidade
    public Pub getPublicidadeid(int idd) {
        Pub publicidade = new Pub();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Table_Publicidades + " WHERE " + Col1_Publicidade_Id + " = " + idd;


        Cursor res = db.rawQuery(selectQuery, null);
        res.moveToNext();


        publicidade.setId(res.getInt(res.getColumnIndex(Col1_Publicidade_Id)));
        publicidade.setConteudo(res.getString(res.getColumnIndex(Col2_Publicidade_Conteudo)));


        res.close();
        db.close();
        return publicidade;
    }

    //Método para return todos os Monumentos

    public ArrayList<Mon> allMonumentos () {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_Monumentos, null);

        ArrayList<Mon> monumentos = new ArrayList<Mon>();

        boolean carryOn = res.moveToFirst();

        while (carryOn) {
            Mon m = new Mon(res.getInt(0),
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4));

            monumentos.add(m);

            carryOn = res.moveToNext();
        }

        res.close();
        return monumentos;
    }

    public ArrayList<Mon> someMonumentos (String cat, String expression) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_Monumentos + " WHERE " + Col4_Monumento_Categoria + "='" + cat + "' AND " + Col2_Monumento_Nome  + " LIKE " + "'%"+ expression +"%'", null);

        ArrayList<Mon> monumentos = new ArrayList<Mon>();

        boolean carryOn = res.moveToFirst();

        while (carryOn) {
            Mon m = new Mon(res.getInt(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4));

            monumentos.add(m);
            carryOn = res.moveToNext();
        }
        res.close();
        return monumentos;
    }

    //Método para return todos os Comentarios
    public ArrayList<Com> getAllComentarios() {
        ArrayList<Com> array_list = new ArrayList<Com>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_Comentarios, null);

        while (res.moveToNext()) {
            Com comentario = new Com();

            comentario.setId(res.getInt(res.getColumnIndex(Col1_Comentario_Id)));
            comentario.setClassificacao(res.getFloat(res.getColumnIndex(Col2_Comentario_Classificacao)));
            comentario.setData(res.getString(res.getColumnIndex(Col3_Comentario_Data)));
            comentario.setMonumento(res.getInt(res.getColumnIndex(Col4_Comentario_Id_Monumento)));
            comentario.setUsername(res.getString(res.getColumnIndex(Col15_Comentario_Username)));
            comentario.setTexto(res.getString(res.getColumnIndex(Col16_Comentario_Texto)));

            array_list.add(comentario);
        }

        res.close();
        db.close();

        return array_list;
    }

    //Método para return todas as Publicidades
    public ArrayList<Pub> getAllPublicidades() {
        ArrayList<Pub> array_list = new ArrayList<Pub>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_Publicidades, null);

        while (res.moveToNext()) {
            Pub publicidade = new Pub();

            publicidade.setId(res.getInt(res.getColumnIndex(Col1_Publicidade_Id)));
            publicidade.setConteudo(res.getString(res.getColumnIndex(Col2_Publicidade_Conteudo)));

            array_list.add(publicidade);
        }

        res.close();
        db.close();

        return array_list;
    }

    public String getLocalização (String nomeMonumento) {

        SQLiteDatabase rDB = this.getReadableDatabase();

        int aux;

        ArrayList<String> local = new ArrayList<>();

        String query = "Select " + Col5_Monumento_Localizacao + " from " + Table_Monumentos + " where "+ Col2_Monumento_Nome + " like '" + nomeMonumento + "'";
        Cursor cursor = rDB.rawQuery(query, null);

        while (cursor.moveToNext()) {
            aux = cursor.getColumnIndex(Col5_Monumento_Localizacao);
            local.add(cursor.getString(aux));
        }
        cursor.close();

        return local.get(0);
    }

    //Obtem o ID do Monumento com o nome  nomeMonumento --------------- NÃO FUNCIONA
    /*
    public int getMonID (String nomeMonumento) {
        SQLiteDatabase rDB = this.getReadableDatabase();

        int aux;
        ArrayList<Integer> monumento = new ArrayList<>();
        String query = "Select " + Col1_Monumento_Id + " from " + Table_Monumentos + " where Nome_Monumento like " + "'" + nomeMonumento + "'";
        Cursor cursor = rDB.rawQuery(query, null);

        while (cursor.moveToNext()) {
            aux = cursor.getColumnIndex(Col5_Monumento_Localizacao);
            monumento.add(cursor.getInt(aux));
        }
        cursor.close();

        int ID = monumento.get(0);
        return ID;
    }*/

    // devolve o ID de um monumento no ArrayList de todos os monumentos ----- FUNCIONA
    public int searchIDmonumento(String name, ArrayList<Mon> monumentos) {

        for(Mon i : monumentos){
            if(i.getNome().equals(name))
                return i.getId();
        }
        return -1;
    }

    //Método para return todos os Comentarios
    public ArrayList<Com> getAllComentariosMonumento(int idMonumento) {
        ArrayList<Com> array_list = new ArrayList<Com>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_Comentarios + " where " + Col4_Comentario_Id_Monumento + " like " + idMonumento, null);

        while (res.moveToNext()) {
            Com comentario = new Com();

            comentario.setId(res.getInt(res.getColumnIndex(Col1_Comentario_Id)));
            comentario.setClassificacao(res.getFloat(res.getColumnIndex(Col2_Comentario_Classificacao)));
            comentario.setData(res.getString(res.getColumnIndex(Col3_Comentario_Data)));
            comentario.setMonumento(res.getInt(res.getColumnIndex(Col4_Comentario_Id_Monumento)));
            comentario.setUsername(res.getString(res.getColumnIndex(Col15_Comentario_Username)));
            comentario.setTexto(res.getString(res.getColumnIndex(Col16_Comentario_Texto)));

            array_list.add(comentario);
        }

        res.close();
        db.close();

        return array_list;
    }

    // Devolve as data dos comentários do Monumento de id 'idMonumento'
    public ArrayList getComentData (int idMonumento) {
        SQLiteDatabase rDB = this.getReadableDatabase();

        int aux;
        ArrayList<String> datas = new ArrayList<>();
        String query = "Select * from " + Table_Comentarios + " where " + Col4_Comentario_Id_Monumento + " like " + idMonumento;
        Cursor cursor = rDB.rawQuery(query, null);

        while (cursor.moveToNext()) {
            aux = cursor.getColumnIndex(Col3_Comentario_Data);
            datas.add(cursor.getString(aux));
        }
        cursor.close();

        return datas;
    }

    // Devolve os autores dos comentários do Monumento de id 'idMonumento'
    public ArrayList getComentUser (int idMonumento) {
        SQLiteDatabase rDB = this.getReadableDatabase();

        int aux;
        ArrayList<String> users = new ArrayList<>();
        String query = "Select * from " + Table_Comentarios + " where " + Col4_Comentario_Id_Monumento + " like " + idMonumento;
        Cursor cursor = rDB.rawQuery(query, null);

        while (cursor.moveToNext()) {
            aux = cursor.getColumnIndex(Col15_Comentario_Username);
            users.add(cursor.getString(aux));
        }
        cursor.close();

        return users;
    }

    // Devolve as classificações dos comentários do Monumento de id 'idMonumento'
    public ArrayList getComentClassificacao (int idMonumento) {
        SQLiteDatabase rDB = this.getReadableDatabase();

        int aux;
        ArrayList<String> classificacoes = new ArrayList<>();
        String query = "Select * from " + Table_Comentarios + " where " + Col4_Comentario_Id_Monumento + " like " + idMonumento;
        Cursor cursor = rDB.rawQuery(query, null);

        while (cursor.moveToNext()) {
            aux = cursor.getColumnIndex(Col2_Comentario_Classificacao);
            classificacoes.add(cursor.getString(aux));
        }
        cursor.close();

        return classificacoes;
    }

    // Devolve as classificações dos comentários do Monumento de id 'idMonumento'
    public ArrayList getComentTexto (int idMonumento) {
        SQLiteDatabase rDB = this.getReadableDatabase();

        int aux;
        ArrayList<String> classificacoes = new ArrayList<>();
        String query = "Select * from " + Table_Comentarios + " where " + Col4_Comentario_Id_Monumento + " like " + idMonumento;
        Cursor cursor = rDB.rawQuery(query, null);

        while (cursor.moveToNext()) {
            aux = cursor.getColumnIndex(Col16_Comentario_Texto);
            classificacoes.add(cursor.getString(aux));
        }
        cursor.close();

        return classificacoes;
    }


    public int getPubli () {
        SQLiteDatabase rDB = this.getReadableDatabase();
        int aux;
        ArrayList<Integer> count = new ArrayList<>();
        String query = "Select " + Col1_Publicidade_Id + " from " + Table_Publicidades;
        Cursor cursor = rDB.rawQuery(query, null);
        while (cursor.moveToNext()) {
            aux = cursor.getColumnIndex(Col1_Publicidade_Id);
            count.add(cursor.getInt(aux));
        }
        cursor.close();

        int maxID = count.size();

        double randomDouble = Math.random();
        randomDouble = randomDouble * maxID + 1;
        int randomInt = (int) randomDouble;
        System.out.println(randomInt);

        return randomInt;
    }

    public String showPubli(int id) {
        SQLiteDatabase rDB = this.getReadableDatabase();
        int aux;
        ArrayList<String> conteudo = new ArrayList<>();
        String query = "Select " + Col2_Publicidade_Conteudo + " from " + Table_Publicidades + " where " + Col1_Publicidade_Id + " like " + id;
        Cursor cursor = rDB.rawQuery(query, null);
        while (cursor.moveToNext()) {
            aux = cursor.getColumnIndex(Col2_Publicidade_Conteudo);
            conteudo.add(cursor.getString(aux));
        }
        cursor.close();

        return conteudo.get(0);
    }

    public void populateDatabase () {

        String espaco = "Espaço de Lazer";
        String mon = "Monumento";
        String museu = "Museu";
        String arte = "Arte Urbana";
        String come = "Zona Comercial";
        String desp = "Zona Desportiva";
        String esc = "Escola";
        String trans = "Transporte";




        // criar três objetos, um de cada classe.
        Mon monumento_Serra = new Mon("Serra da Estrela", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", espaco, "40.321816,-7.613841");
        Mon monumento_Praca = new Mon("Praça do Município", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", espaco, "40.2806606,-7.5043491");
        Mon monumento_Pelourinho = new Mon("UBI3", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", espaco, "40.282774,-7.504674");
        Mon monumento_UBIIV = new Mon("UBI Faculdade de Ciências Sociais e Humanas", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", esc, "40.288480,-7.5148922");
        Mon monumento_UBIEng = new Mon("UBI Faculdade de Engenharia", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", esc, "40.278465,-7.511785");
        Mon monumento_UBIFAL = new Mon("UBI Faculdade de Artes e Letras", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", esc, "40°16'40.3\"N,7°30'32.5\"W");
        Mon monumento_UBIFC = new Mon("UBI Faculdade de Ciências", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", esc, "40.269925, -7.493817");
        Mon monumento_UBIFCS = new Mon("UBI Faculdade de Ciências da Saúde", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", esc, "40.269925, -7.493817");
        Mon monumento_Reitoria = new Mon("Reitoria da UBI", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", esc, "40.275702, -7.509246");
        Mon monumento_Fonte = new Mon("Fonte das Galinhas", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", esc, "40.284156, -7.501539");
        Mon monumento_Rato = new Mon("Rotunda do Rato", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", espaco, "40.277273, -7.510689");
        Mon monumento_Garagem = new Mon("Garagem de S. João", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", espaco, "40.281986, -7.501584");
        Mon monumento_Igreja1 = new Mon("Igreja da Misericórdia", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", mon, "40.281435, -7.501579");
        Mon monumento_Igreja2 = new Mon("Igreja de S. Tiago", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", mon, "40.280241, -7.503140");
        Mon monumento_Igreja3 = new Mon("Igreja de N Sra da Conceição", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", mon, "40.277491, -7.496795");
        Mon monumento_Igreja4 = new Mon("Igreja da Santíssima Trindade", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", mon, "40.277913, -7.496225");
        Mon monumento_Igreja5 = new Mon("Igreja dos Penedos Altos", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", mon, "40.288943, -7.498707");
        Mon monumento_Capela1 = new Mon("Capela de S. Martinho", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", mon, "40.278389, -7.504501");
        Mon monumento_Capela2 = new Mon("Capela do Calvário", "Universidade da Beira Interior, situada na Cidade Neve: Covilhã", mon, "40.282603, -7.506352");

        Com comentario_sample = new Com("28/10/2019", "Pedro" , "Não há neve no inverno!!!! Qual cidade neve qual quê!!!", 1, 3.0f);
        Com comentario_sample1 = new Com("29/10/2019", "Ana" , "Péssimo, horrível, horripilante!", 10, 1.0f);
        Com comentario_sample2 = new Com("30/10/2019", "Marco" , "Experiência única. Muito religiosa.", 15, 4.5f);

        Pub publicidade_sample = new Pub("Venha conhecer a UBI!");

        // inseri-los na base de dados.
        insertMonumento(monumento_Serra);
        insertMonumento(monumento_Praca);
        insertMonumento(monumento_Pelourinho);
        insertMonumento(monumento_UBIIV);
        insertMonumento(monumento_UBIEng);
        insertMonumento(monumento_UBIFAL);
        insertMonumento(monumento_UBIFC);
        insertMonumento(monumento_UBIFCS);
        insertMonumento(monumento_Reitoria);
        insertMonumento(monumento_Fonte);
        insertMonumento(monumento_Rato);
        insertMonumento(monumento_Garagem);
        insertMonumento(monumento_Igreja1);
        insertMonumento(monumento_Igreja2);
        insertMonumento(monumento_Igreja3);
        insertMonumento(monumento_Igreja4);
        insertMonumento(monumento_Igreja5);
        insertMonumento(monumento_Capela1);
        insertMonumento(monumento_Capela2);

        insertComentario(comentario_sample);
        insertComentario(comentario_sample1);
        insertComentario(comentario_sample2);

        insertPublicidade(publicidade_sample);
    }
}

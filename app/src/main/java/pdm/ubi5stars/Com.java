package pdm.ubi5stars;

public class Com {

    public String data, username, texto;
    public int id, id_monumento;
    // a ratingBar devolve um float
    public float classificacao;

    //------------------------------------------------------------------------
    //++++                     Construtores                               ++++
    //------------------------------------------------------------------------

    public Com () {

    }

    public Com(String texto, int id_monumento, float classificacao) {
        this.texto = texto;
        this.id_monumento = id_monumento;
        this.classificacao = classificacao;
    }

    public Com(String data, String texto, int id_monumento, float classificacao) {
        this.data = data;
        this.texto = texto;
        this.id_monumento = id_monumento;
        this.classificacao = classificacao;
    }

    public Com(String data, String username, String texto, int id_monumento, float classificacao) {
        this.data = data;
        this.username = username;
        this.texto = texto;
        this.id_monumento = id_monumento;
        this.classificacao = classificacao;
    }

    //------------------------------------------------------------------------
    //++++                        GETTERS                                 ++++
    //------------------------------------------------------------------------

    public float getClassificacao() { return this.classificacao;
    }

    public String getData() {
        return this.data;
    }

    public int getMonumento() {
        return this.id_monumento;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() { return username; }

    public String getTexto() { return  texto; }

    //------------------------------------------------------------------------
    //++++                        SETTERS                                 ++++
    //------------------------------------------------------------------------

    public void setId(int id) {
        this.id = id;
    }

    public void setClassificacao(float classificacao) {
        this.classificacao = classificacao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMonumento(int id_monumento) {
        this.id_monumento = id_monumento;
    }

    public void setUsername(String username) { this.username = username; }

    public void setTexto(String texto) { this.texto = texto; }
}
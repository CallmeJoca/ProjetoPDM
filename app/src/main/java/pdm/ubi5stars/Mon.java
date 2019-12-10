package pdm.ubi5stars;

public class Mon {

    public String nome, info, categoria, localizacao;
    public int id;

    //------------------------------------------------------------------------
    //++++                     Construtores                               ++++
    //------------------------------------------------------------------------
    public Mon () {

    }

    public Mon (String nome) {
        setNome(nome);
    }

    public Mon(int id, String nome, String info, String categoria, String localizacao) {
        this.nome = nome;
        this.info = info;
        this.categoria = categoria;
        this.localizacao = localizacao;
        this.id = id;
    }

    public Mon (String nome, String info, String categoria, String localizacao) {
        setNome(nome);
        setInfo(info);
        setCategoria(categoria);
        setLocalizacao(localizacao);
    }

    //------------------------------------------------------------------------
    //++++                        GETTERS                                 ++++
    //------------------------------------------------------------------------

    public String getNome() {
        return this.nome;
    }
    public String getInfo() {
        return this.info;
    }

    // possíveis categorias:  Museus; Arte Urbana; Espaços de Lazer; Zonas comerciais; Zona Desportivas; Escolas; Transportes
    public String getCategoria() {
        return this.categoria;
    }

    public String getLocalizacao() {
        return this.localizacao;
    }

    public int getId() {
        return this.id;
    }

    //------------------------------------------------------------------------
    //++++                        SETTERS                                 ++++
    //------------------------------------------------------------------------

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}

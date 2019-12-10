package pdm.ubi5stars;

public class Pub {

    public int id;
    public String conteudo;

    //------------------------------------------------------------------------
    //++++                     Construtores                               ++++
    //------------------------------------------------------------------------

    public Pub () {

    }

    public Pub (String conteudo) {
        setConteudo(conteudo);
    }

    //------------------------------------------------------------------------
    //++++                        GETTERS                                 ++++
    //------------------------------------------------------------------------

    public String getConteudo() {
        return this.conteudo;
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

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}

package ramonbr14.example.com.bibliogroselha;

/**
 * Created by Aluno on 18/06/2018.
 */

public class Livro {
    private int codigo;
    private String titulo;
    private String autor;
    private String tema;
    private int pagAtual;
    private String dataValida;

    Livro(){}

    Livro(int codigo, String titulo, String autor,String tema,int pagAtual,String dataValida){
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.tema = tema;
        this.pagAtual = pagAtual;
        this.dataValida = dataValida;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getPagAtual() {
        return pagAtual;
    }

    public void setPagAtual(int pagAtual) {
        this.pagAtual = pagAtual;
    }

    public String getDataValida() {
        return dataValida;
    }

    public void setDataValida(String datavalida) {
        this.dataValida = dataValida;
    }

}
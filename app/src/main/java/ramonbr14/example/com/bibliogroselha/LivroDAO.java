package ramonbr14.example.com.bibliogroselha;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Aluno on 18/06/2018.
 */

public class LivroDAO {

    SQLiteDatabase db;

    LivroDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void criarTabelaLivro() {
        String sql = "CREATE TABLE LIVRO(" + "ID integer primary key autoincrement,"
                + "TITULO String,"
                + "AUTOR String,"
                + "TEMA String,"
                + "PAGATUAL integer,"
                + "DATAVALIDA String)";
        db.execSQL(sql);
    }

    public void insereLivro(Livro l) {
        String sql = "INSERT INTO LIVRO (TITULO, AUTOR, TEMA, PAGATUAL, DATAVALIDA) VALUES ('" +
                l.getTitulo() + "', '" +
                l.getAutor() + "', '" +
                l.getTema() + "', '" +
                l.getPagAtual() + "', '" +
                l.getDataValida() + "')";
        System.out.println(sql);
    }

    public void atualizalivro(Livro l) {
        String sql = "UPDATE LIVRO SET " +
                "TITULO = '" + l.getTitulo() + "', " +
                "AUTOR = '" + l.getAutor() + "', " +
                "TEMA = '" + l.getTema() + "', " +
                "PAGATUAL = '" + l.getPagAtual() + "', " +
                "DATAVALIDA) = '" + l.getDataValida();
        db.execSQL(sql);
    }

    public void excluiLivro(Livro l) {
        String sql = "DELETE FROM CONTATO WHERE ID = " + l.getCodigo();
        db.execSQL(sql);
    }

    public ArrayList<Livro> consultaLivro() {
        Cursor cursor;
        ArrayList<Livro> livros = new ArrayList<Livro>();
        cursor = db.query("livro", new String[]{"CODIGO", "TITULO", "AUTOR", "TEMA", "PAGATUAL", "DATAVALIDA"}, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Livro l = new Livro();
                    l.setCodigo(cursor.getInt(cursor.getColumnIndex("CODIGO")));
                    l.setTitulo(cursor.getString(cursor.getColumnIndex("TITULO")));
                    l.setAutor(cursor.getString(cursor.getColumnIndex("AUTOR")));
                    l.setTema(cursor.getString(cursor.getColumnIndex("TEMA")));
                    l.setPagAtual(cursor.getInt(cursor.getColumnIndex("PAGATUAL")));
                    l.setDataValida(cursor.getString(cursor.getColumnIndex("DATAVALIDA")));
                } while (cursor.moveToNext());
            }
        }
        return livros;
    }
}
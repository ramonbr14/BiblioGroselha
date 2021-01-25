package ramonbr14.example.com.bibliogroselha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aluno on 18/06/2018.
 */

public class GerarBanco  extends SQLiteOpenHelper {

    GerarBanco(Context context){
            super(context, "grBiblio.db", null, 2);
    }

    @Override
    public  void onCreate(SQLiteDatabase sqLiteDatabase){
            LivroDAO livroDao = new LivroDAO(sqLiteDatabase);
            livroDao.criarTabelaLivro();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS livro");
        onCreate(sqLiteDatabase);
    }

}

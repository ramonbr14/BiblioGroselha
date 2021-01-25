package ramonbr14.example.com.bibliogroselha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelainicioActivity extends AppCompatActivity {

    ListView listalivroView;

    //ArrayList<Livro> listalivros = new ArrayList<Livro>();
    ArrayList<Livro> listalivrosArray;
    TextView tvREGLIVRO;
    EditText edTITULO, edAUTOR, edTEMA, edPAGAATUAL, edDATAVALIDA;
    String aviso;

    Button btAddLivro, btAnular, btApagar, btSalvar;

    GerarBanco grBiblio;
    LivroDAO livroDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        grBiblio = new GerarBanco(this);
        livroDAO = new LivroDAO(grBiblio.getWritableDatabase());

        carregarListaLivros();
    }

    public void carregarListaLivros(){
        setContentView(R.layout.telainicio);
        listalivrosArray = livroDAO.consultaLivro();
        ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(this,R.layout.support_simple_spinner_dropdown_item, listalivrosArray);
        listalivroView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                aviso = "TESTANDO A FUNÇÃO CARREGAR LIVRO";
                //Toast.makeText(getBaseContext(), aviso, Toast.LENGTH_LONG).show();
                carregarLivro(i);
            }
        });

        btAddLivro = (Button) findViewById(R.id.btAddLivro);
        btAddLivro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                aviso = "BOTÃO ADICIONAR LIVRO ACIONADO";
                //Toast.makeText(getBaseContext(), aviso, Toast.LENGTH_LONG).show();
                carregarLivro(-1);
            }
        });
    }


    public void carregarLivro (final int item){
        setContentView(R.layout.livro);
        tvREGLIVRO = findViewById(R.id.tvREGLIVRO);
        edTITULO = findViewById(R.id.edTITULO);
        edAUTOR = findViewById(R.id.edAUTOR);
        edTEMA = findViewById(R.id.edTEMA);
        edPAGAATUAL = findViewById(R.id.edDATAVALIDA);
        btSalvar = findViewById(R.id.btSalvar);
        btAnular = findViewById(R.id.btCancelar);
        btApagar = findViewById(R.id.btExcluir);

        View.OnClickListener adicionandoNovoLivro = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btSalvar){
                    livroDAO.insereLivro(new Livro(0, edTITULO.getText().toString(),edAUTOR.getText().toString(),edTEMA.getText().toString(),Integer.parseInt(edPAGAATUAL.getText().toString()),edDATAVALIDA.getText().toString()));
            }
                carregarListaLivros();
            }
        };
        View.OnClickListener editandoLivro = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == btSalvar){
                listalivrosArray.get(item).setTitulo(edTITULO.getText().toString());
                listalivrosArray.get(item).setAutor(edAUTOR.getText().toString());
                listalivrosArray.get(item).setTema(edTEMA.getText().toString());
                listalivrosArray.get(item).setPagAtual(Integer.parseInt(edPAGAATUAL.getText().toString()));
                listalivrosArray.get(item).setDataValida(edDATAVALIDA.getText().toString());
                livroDAO.atualizalivro(listalivrosArray.get(item));
                }
                if(view==btApagar){
                    livroDAO.excluiLivro(listalivrosArray.get(item));
                }
                carregarListaLivros();
            }
        };
        if(item < 0){
            tvREGLIVRO.setText("Cadastrar Novo Livro");
            edTITULO.setText("");
            edAUTOR.setText("");
            edTEMA.setText("");
            edPAGAATUAL.setText("");
            edDATAVALIDA.setText("");
            btApagar.setVisibility(View.INVISIBLE);
            btSalvar.setOnClickListener(adicionandoNovoLivro);
            btAnular.setOnClickListener(adicionandoNovoLivro);
        }
        else{
            tvREGLIVRO.setText("EDITANDO O LIVRO");
            edTITULO.setText(listalivrosArray.get(item).getTitulo());
            edAUTOR.setText(listalivrosArray.get(item).getAutor());
            edTEMA.setText(listalivrosArray.get(item).getTema());
            edPAGAATUAL.setText(listalivrosArray.get(item).getPagAtual());
            edDATAVALIDA.setText(listalivrosArray.get(item).getDataValida());
            btSalvar.setOnClickListener(editandoLivro);
            btAnular.setOnClickListener(editandoLivro);
            btApagar.setOnClickListener(editandoLivro);
        }
    }
}
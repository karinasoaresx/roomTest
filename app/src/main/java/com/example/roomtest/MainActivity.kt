package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.roomtest.dao.AppDataBase
import com.example.roomtest.model.Contato
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAddContato: ImageButton
    private lateinit var editNome: EditText
    private lateinit var editTelefone: EditText
    private lateinit var buttonSalvar: Button
    private lateinit var buttonCancelar: Button

    private lateinit var dialog: AlertDialog

    private lateinit var txtLista: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAddContato = findViewById(R.id.button_add_contato)
        buttonAddContato.setOnClickListener(this)

        txtLista = findViewById(R.id.edit_text_lista)
        txtLista.setOnClickListener(this)


    }

    override fun onClick(v: View) {

        if(v.id == R.id.button_add_contato) {
            abrirCadastroContato()
        } else if (v.id == R.id.button_cancelar) {
            dialog.dismiss()
        } else if(v.id == R.id.button_salvar) {
            salvarContato()
        } else {
            exibirContatos()
        }


    }

    private fun exibirContatos() {
        //instancia o banco
        val db = Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "db_contato").allowMainThreadQueries().build()

        //criar instancia no dao
        val contatoDao = db.ContatoDao()

        //pedir para listar no banco
        val contatos = contatoDao.listarTodos()

        txtLista.text = ""

        for(contato in contatos) {
            txtLista.text = "${txtLista.text} - ${contato.nomeContato}"
        }
    }

    private fun salvarContato() {
        //cria o contato
        var contato = Contato(nomeContato = editNome.text.toString(), telefoneContato =  editTelefone.text.toString())

        //instancia o banco
        val db = Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "db_contato").allowMainThreadQueries().build()

        //criar instancia no dao
        val contatoDao = db.ContatoDao()

        //pedir para salvar no banco
        contatoDao.salvar(contato)
    }

    private fun abrirCadastroContato() {
        val alertDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater

        //não precisa criar o inflater, pode colocar o 'LayoutInflater' direto no lugar do 'inflater' na linha de baixo
        val view = inflater.inflate(R.layout.cadastro_contato_dialog, null)

        alertDialog.setView(view)

        editNome = view.findViewById(R.id.edit_text_nome)
        editTelefone = view.findViewById(R.id.edit_text_telefone)

        buttonSalvar = view.findViewById(R.id.button_salvar)
        buttonSalvar.setOnClickListener(this)

        buttonCancelar = view.findViewById(R.id.button_cancelar)
        buttonCancelar.setOnClickListener(this)

        //não fechar a modal quando clicar fora dela
        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

        buttonCancelar.setOnClickListener(this)


    }
}
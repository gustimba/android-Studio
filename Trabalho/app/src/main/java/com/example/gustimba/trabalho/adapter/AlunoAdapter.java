package com.example.gustimba.trabalho.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gustimba.trabalho.ListaAlunosActivity;
import com.example.gustimba.trabalho.R;
import com.example.gustimba.trabalho.modelo.Aluno;

import java.util.List;

/**
 * Created by Gustimba on 13/06/2017.
 */

public class AlunoAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    private final Context context;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return alunos.get(position).getId(); // pego a posicao d  aluno e pergunta qual id do  aluno.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Aluno aluno = alunos.get(position);
        LayoutInflater Inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null) {
            view = Inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView Camponome = (TextView) view.findViewById(R.id.item_nome);
        Camponome.setText(aluno.getNome());

        TextView Campotelefone = (TextView) view.findViewById(R.id.item_telefone);
        Campotelefone.setText(aluno.getTelefone());

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhofoto = aluno.getCaminhoFoto();
        if(caminhofoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhofoto);
            Bitmap bitmap_reduzir = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            //Porém, dependo da câmera usada, a imagem pode ter uma resolução muito grande e não ser suportada pelo
            // ImageView. Teremos que reduzir o tamanho do Bitmap. Também iremos especificar as dimensões e adicionar true, para que seja usado um filtro depois da redução da imagem.
            // Para dar diferenciação, chamaremos de bitmapReduzido.
            campoFoto.setImageBitmap(bitmap_reduzir);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            //Com o FIT_XY, estamos especificando que a imagem deve se
            // encaixar no espaço, ajustada tanto na largura como na altura disponível no ImageView.

        }
        return view;
    }
}

package com.example.gustimba.trabalho.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.gustimba.trabalho.Dao.AlunoDao;

/**
 * Created by Gustimba on 19/06/2017.
 */

public class SmsReceiver extends BroadcastReceiver{
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public void onReceive(Context context, Intent intent) {
         AlunoDao dao = new AlunoDao(context);
         Object[] pdus = (Object[]) intent.getSerializableExtra("pdus"); //pegando os pdus do  formulario
         byte[] pdu = (byte[]) pdus[0]; // array de byte na possicao 0
        String formato = (String) intent.getSerializableExtra("format");
        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);
        String telefone = sms.getDisplayOriginatingAddress();
        if(dao.ehAluno(telefone)){
            Toast.makeText(context,"mensagem de um aluno viado",Toast.LENGTH_SHORT).show(); //mostrar  a mensagem
            //MediaPlayer mediaPlayer = MediaPlayer.create(context, android.R.raw.musica.wamp); chamando um so para mensagem
            //mediaPlayer.start();
        }
       dao.close();

    }
}

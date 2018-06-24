package com.example.nobre.ntrack.asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.example.nobre.ntrack.DAO.MotoDAO;
import java.util.List;

public class MarcasTask extends AsyncTask<Void, Void, List<String>> {

    private Context context;
    ProgressDialog dialog;

    public MarcasTask(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Aguarde",
                "Fazendo download do JSON");

    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        // retorna a lista caso precise
        MotoDAO dao = new MotoDAO(context);
        List<String> resposta = WebClient.carregarMarcasJson();
        for (int x = 0; x < resposta.size(); x++) {
            dao.insertMarcas(resposta.get(x));
        }
        dao.close();
        return WebClient.carregarMarcasJson();
    }

    @Override
    protected void onPostExecute(List<String> resposta) {
        dialog.dismiss();
        System.out.println("Tamanho  ==========" + resposta.size());

    }
}

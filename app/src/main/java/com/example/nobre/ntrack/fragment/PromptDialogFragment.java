package com.example.nobre.ntrack.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.nobre.ntrack.R;


public class PromptDialogFragment extends DialogFragment implements OnClickListener{

    private Button botaoOk;
    private Button botaoCancela;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        botaoOk = view.findViewById(R.id.btnOk);
        botaoCancela = view.findViewById(R.id.btnCancela);

        botaoCancela.setOnClickListener(this);
        botaoOk.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_tarefa, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == botaoOk){
            Toast.makeText(getContext(), "clicou ok", Toast.LENGTH_SHORT).show();
        } else if(v == botaoCancela){
            Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
        }
        dismiss();
    }
}

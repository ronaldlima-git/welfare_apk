package com.welfare.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.welfare.R;

public class ConsultasExamesActivity extends AppCompatActivity {

    private Button btnConsultasExamesVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_exames);

        carregarComponentes();

        botaoVoltar(btnConsultasExamesVoltar);
    }

    public void carregarComponentes(){
        btnConsultasExamesVoltar = findViewById(R.id.btnConsultasExamesVoltar);
    }

    public void botaoVoltar(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

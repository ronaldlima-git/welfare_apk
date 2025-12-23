package com.welfare.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.welfare.R;

public class TratamentsActivity extends AppCompatActivity {

    private Button btnTratamentoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamentos);

        carregarComponentes();

        botaoVoltar(btnTratamentoVoltar);
    }

    public void carregarComponentes(){
        btnTratamentoVoltar = findViewById(R.id.btnTratamentoVoltar);
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

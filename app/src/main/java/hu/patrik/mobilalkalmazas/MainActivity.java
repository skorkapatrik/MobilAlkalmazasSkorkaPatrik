package hu.patrik.mobilalkalmazas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hu.patrik.mobilalkalmazas.feszekterkep.FeszekActivity;
import hu.patrik.mobilalkalmazas.webszolgaltatas.PostActivity;

public class MainActivity extends AppCompatActivity {
    Button webszolgaltatas,feszekterkep,jegyzet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webszolgaltatas=findViewById(R.id.btnWebszolgaltatas);
        webszolgaltatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openWebszolgaltatas();
            }
        });

        feszekterkep=findViewById(R.id.btnFeszekterkep);
        feszekterkep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeszekterkep();
            }
        });



    }

    public void openWebszolgaltatas() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    public void openFeszekterkep() {
        Intent intent = new Intent(this, FeszekActivity.class);
        startActivity(intent);
    }


}

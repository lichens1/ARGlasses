package lichens.licht.arglasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.cl_net).setOnClickListener(this);
        findViewById(R.id.cl_auto_connect).setOnClickListener(this);
        findViewById(R.id.cl_light).setOnClickListener(this);
        findViewById(R.id.cl_volume).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.cl_net:
                intent = new Intent(this, ConnectStateActivity.class);
                startActivity(intent);
                break;
            case R.id.cl_auto_connect:
                intent = new Intent(this, LightActivity.class);
                startActivity(intent);
                break;
            case R.id.cl_light:
                intent = new Intent(this, LightActivity.class);
                startActivity(intent);
                break;
            case R.id.cl_volume:
                intent = new Intent(this, LightActivity.class);
                startActivity(intent);
                break;
        }
    }
}
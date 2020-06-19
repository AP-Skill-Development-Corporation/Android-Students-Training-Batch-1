package com.muneiah.mydatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.muneiah.mydatabinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
/* Step 3 enable Databing in out Activty/class*/
    ActivityMainBinding binding;
    int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Step 3 A) Insilize the ActivityMainBinding */
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+binding.editText.getText().toString()+
                        "\n"+binding.editText2.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                binding.textView.setText(""+c);
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c==0 || c >=0) {
                    c--;
                    binding.textView.setText(""+c);

                }else {
                    Toast.makeText(MainActivity.this, "should grater zero", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}

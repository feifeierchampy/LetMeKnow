package com.feifeier.letmeknow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mButton = (Button) findViewById(R.id.btn_send_email);
//
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent data = new Intent(Intent.ACTION_SENDTO);
//
//                data.setData(Uri.parse("mailto:332560026@qq.com"));
//                data.putExtra(Intent.EXTRA_SUBJECT, "这是主题");
//                data.putExtra(Intent.EXTRA_TEXT, "这是内容");
//
//                startActivity(data);
//            }
//        });

    }
}

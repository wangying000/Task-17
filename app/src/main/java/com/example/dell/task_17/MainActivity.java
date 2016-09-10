package com.example.dell.task_17;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String a="";
    String b="";

    private final static String SharedPreferencesFileName = "config";

    private final static String Key_UserName = "UserName";
    private final static String Key_UserType = "UserType";
    private final static String Key_UserId = "UserId";
    private final static String Key_LoginDate="Login_Date";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(SharedPreferencesFileName,MODE_PRIVATE);
        editor = preferences.edit();

        Button btnWrite = (Button) findViewById(R.id.button);
        Button btnRead = (Button) findViewById(R.id.button2);
        Button button=(Button)findViewById(R.id.button3);

        final EditText editText=(EditText)findViewById(R.id.editText);
        final EditText editText1=(EditText)findViewById(R.id.editText2);
        final TextView textView=(TextView)findViewById(R.id.textView);
        final TextView textView1=(TextView)findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                editText1.setText("");
                textView.setText("");
                textView1.setText("");
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strLoginDate=simpleDateFormat.format(new Date());

                final String Key_Username=editText.getText().toString();
                final String Key_Userid=editText1.getText().toString();

                editor.putString(Key_UserName, Key_Username);
                editor.putInt(Key_UserType, 1);
                editor.putString(Key_UserId,Key_Userid);
                editor.putString(Key_LoginDate,strLoginDate);
                editor.commit();
                Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = preferences.getString(Key_UserName,null);
                String strUserId = preferences.getString(Key_UserId, null);
                int nUserType = preferences.getInt(Key_UserType, 0);
                String strLoginDate=preferences.getString(Key_LoginDate,null);

                a=editText.getText().toString();
                b=editText1.getText().toString();
                if(strUserName.equals(a)&&strUserId.equals(b)){

                if(strUserName.equals("")||strUserId.equals("")){
                    textView.setText("");
                    textView1.setText("");
                            Toast.makeText(MainActivity.this,"空数据",Toast.LENGTH_LONG).show();}

                    else {
                    textView.setText("姓名"+strUserName);
                    textView1.setText("学号"+strUserId);

                    Toast.makeText(MainActivity.this, "学生姓名：" + strUserName + "    学号：" + strUserId+ "    用户类型：" + nUserType+
                            "    登录时间"+strLoginDate, Toast.LENGTH_LONG).show();}}
                else
                    Toast.makeText(MainActivity.this,"数据已更改，请保存",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

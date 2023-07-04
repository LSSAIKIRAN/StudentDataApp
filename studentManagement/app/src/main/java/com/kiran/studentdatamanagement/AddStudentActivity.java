package com.kiran.studentdatamanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kiran.studentdatamanagement.todoClasses.MyDatabase;
import com.kiran.studentdatamanagement.todoClasses.MyRepository;
import com.kiran.studentdatamanagement.todoClasses.StudentData;

public class AddStudentActivity extends AppCompatActivity {

    EditText name, lastname, stuClass;
    Button save;
    MyRepository repository;
    StudentData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        init();
        setUp();


    }

    private void init() {
        name = findViewById(R.id.name);
        lastname = findViewById(R.id.lastName);
        stuClass = findViewById(R.id.StuClass);
        save = findViewById(R.id.save);

        repository = new MyRepository(MyDatabase.getInstance(getApplicationContext()).myDao());
        save.setOnClickListener(view -> {
            String Name = name.getText().toString();
            String lastName = lastname.getText().toString();
            String stuclass = stuClass.getText().toString();

            if (data == null) {

                if (Name.isEmpty() || lastName.isEmpty() || stuclass.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Error");
                    builder.setMessage("Please fill in all required fields.");
                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    data = new StudentData(Name, lastName, Integer.parseInt(stuclass));
                    repository.addStudent(data);
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

                    name.setText("");
                    lastname.setText("");
                    stuClass.setText("");
                }

            }

            else {
                updateData();
            }

        });

    }

    private void updateData() {
        String Name = name.getText().toString();
        String lastName = lastname.getText().toString();
        String stuclass = stuClass.getText().toString();

        data.setName(Name);
        data.setLastName(lastName);
        data.setStudentClas(Integer.parseInt(stuclass));

        repository.updateStudent(data);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setUp() {
        Intent intent = getIntent();
        data = intent.getParcelableExtra("myKey");
        if (data != null) {
            name.setText(data.getName());
            lastname.setText(data.getLastName());
            stuClass.setText(String.valueOf(data.getStudentClas()));

        }

    }

}
package com.kiran.studentdatamanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kiran.studentdatamanagement.todoClasses.MyDao;
import com.kiran.studentdatamanagement.todoClasses.MyDatabase;
import com.kiran.studentdatamanagement.todoClasses.MyRepository;
import com.kiran.studentdatamanagement.todoClasses.MyViewModel;
import com.kiran.studentdatamanagement.todoClasses.StudentData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.onStudentClickListener {

    private MyViewModel myViewModel;
    private MyRepository repository;

    private RecyclerView recyclerView;
    private ImageView imageView;

    private StudentAdapter adapter;
    private ArrayList<StudentData> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        myViewModel = new ViewModelProvider(MainActivity.this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MyViewModel.class);

        MyDao dao = MyDatabase.getInstance(getApplicationContext()).myDao();

        repository = new MyRepository(dao);

        myViewModel.allData.observe(this, new Observer<List<StudentData>>() {
            @Override
            public void onChanged(List<StudentData> studentData) {
                arrayList.clear();
                adapter.notifyDataSetChanged();

                arrayList.addAll(studentData);
                adapter.notifyDataSetChanged();
            }
        });




    }

    private  void init(){
        recyclerView = findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();
        adapter = new StudentAdapter(arrayList, this);
        imageView = findViewById(R.id.imageView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDeleteClickListener(int position) {
        StudentData data = arrayList.get(position);
        repository.deleteStudent(data);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditClickListener(int position) {
        StudentData data = arrayList.get(position);
        Intent intent = new Intent(this, AddStudentActivity.class);
        intent.putExtra("myKey", data);
        startActivity(intent);

    }
}
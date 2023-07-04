package com.kiran.studentdatamanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiran.studentdatamanagement.todoClasses.StudentData;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    ArrayList<StudentData> arrayList;
    onStudentClickListener clickListener;

    public StudentAdapter(ArrayList<StudentData> arrayList, onStudentClickListener clickListener) {
        this.arrayList = arrayList;
        this.clickListener = clickListener;
    }

    public interface onStudentClickListener {
        void onDeleteClickListener(int position);

        void onEditClickListener(int position);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentData data = arrayList.get(position);

        if (data == null) {
            return;
        }

        String fullname = data.getName() + " " + data.getLastName();
        holder.fullName.setText(fullname);
        holder.stuClass.setText(String.valueOf(data.getStudentClas()));

        holder.edit.setOnClickListener(view -> {
            clickListener.onEditClickListener(position);
        });

        holder.delete.setOnClickListener(view -> {
            clickListener.onDeleteClickListener(position);

        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView fullName, stuClass;
        ImageView edit, delete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullname);
            stuClass = itemView.findViewById(R.id.grade);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}

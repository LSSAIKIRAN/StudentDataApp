package com.kiran.studentdatamanagement.todoClasses;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_data")
public class StudentData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "student_name")
    private String name;

    private String lastName;

    private int studentClas;

    public StudentData(String name, String lastName, int studentClas) {
        this.name = name;
        this.lastName = lastName;
        this.studentClas = studentClas;
    }

    protected StudentData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        lastName = in.readString();
        studentClas = in.readInt();
    }

    public static final Creator<StudentData> CREATOR = new Creator<StudentData>() {
        @Override
        public StudentData createFromParcel(Parcel in) {
            return new StudentData(in);
        }

        @Override
        public StudentData[] newArray(int size) {
            return new StudentData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStudentClas() {
        return studentClas;
    }

    public void setStudentClas(int studentClas) {
        this.studentClas = studentClas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(lastName);
        parcel.writeInt(studentClas);
    }
}

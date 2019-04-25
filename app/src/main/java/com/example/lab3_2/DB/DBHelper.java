package com.example.lab3_2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lab3_2.Entity.Student;
import com.example.lab3_2.Entity.StudentOld;

import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public static int newVersion = 1;

    public DBHelper(Context context){
        super(context, "StudentsDB", null, newVersion);
    }

    public DBHelper(Context context, int newVersion){
        super(context, "StudentsDB", null, newVersion);
        this.newVersion = newVersion;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (newVersion == 1) {
            db.execSQL("CREATE TABLE IF NOT EXISTS Students" +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "FIO TEXT," +
                    "Time REAL  NOT NULL)"
            );
        }
        else
            db.execSQL("CREATE TABLE IF NOT EXISTS Students" +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "Surname TEXT," +
                    "Name TEXT," +
                    "Patronymic TEXT," +
                    "Time REAL NOT NULL)"
            );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion > oldVersion){
            Log.i("NEW_VERS", Integer.toString(newVersion));
            oldVersion++;
            db.execSQL("CREATE TABLE IF NOT EXISTS Students2" +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "Surname TEXT," +
                    "Name TEXT," +
                    "Patronymic TEXT," +
                    "Time REAL NOT NULL)"
            );
            Cursor c = db.rawQuery("SELECT id, FIO, Time FROM Students", null);
            ContentValues cv = new ContentValues();
            if (c.moveToFirst()){
                do {
                    cv.clear();
                    cv.put("id", c.getInt(0));
                    String FIO = c.getString(1);
                    cv.put("Surname", FIO.substring(0, FIO.indexOf(' ')));
                    cv.put("Name", FIO.substring(FIO.indexOf(' ')+1, FIO.substring(FIO.indexOf(' ')+1).indexOf(' ') + FIO.indexOf(' ')+1));
                    cv.put("Patronymic", FIO.substring(FIO.substring(FIO.indexOf(' ')+1).indexOf(' ') + FIO.indexOf(' ')+2));
                    cv.put("Time", c.getLong(2));
                    db.insert("Students2", null, cv);
                }while (c.moveToNext());
            }
            /*db.rawQuery("INSERT INTO Students2(id, Surname, Name, Patronymic, Time) SELECT id, " +
                    "SUBSTR(FIO, 0, CHARINDEX(' ', FIO)), " +
                    "SUBSTR(FIO, CHARINDEX(' ', FIO)+1, CHARINDEX(' ', SUBSTR(FIO, CHARINDEX(' ', FIO)+1))), " +
                    "SUBSTR(FIO, CHARINDEX(' ', SUBSTR(FIO, CHARINDEX(' ', FIO)+1))+1), " +
                    "Time FROM Students", null);*/
            db.execSQL("DROP TABLE IF EXISTS Students");
            db.execSQL("ALTER TABLE Students2 RENAME TO Students");
            c.close();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 2 && newVersion == 1){
            Log.i("NEW_VERS", Integer.toString(newVersion));
            oldVersion++;
            db.execSQL("CREATE TABLE IF NOT EXISTS Students2" +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "FIO TEXT," +
                    "Time REAL NOT NULL)"
            );
            Cursor c = db.rawQuery("SELECT id, Surname, Name, Patronymic, Time FROM Students", null);
            ContentValues cv = new ContentValues();
            if (c.moveToFirst()){
                do {
                    cv.clear();
                    cv.put("id", c.getInt(0));
                    String FIO = c.getString(1) + " " + c.getString(2)  + " " +  c.getString(3);
                    cv.put("FIO", FIO);
                    cv.put("Time", c.getLong(4));
                    db.insert("Students2", null, cv);
                }while (c.moveToNext());
            }
            /*db.rawQuery("INSERT INTO Students2(id, Surname, Name, Patronymic, Time) SELECT id, " +
                    "SUBSTR(FIO, 0, CHARINDEX(' ', FIO)), " +
                    "SUBSTR(FIO, CHARINDEX(' ', FIO)+1, CHARINDEX(' ', SUBSTR(FIO, CHARINDEX(' ', FIO)+1))), " +
                    "SUBSTR(FIO, CHARINDEX(' ', SUBSTR(FIO, CHARINDEX(' ', FIO)+1))+1), " +
                    "Time FROM Students", null);*/
            db.execSQL("DROP TABLE IF EXISTS Students");
            db.execSQL("ALTER TABLE Students2 RENAME TO Students");
            c.close();
        }
    }

    public void setVersionOfDB(int newVersion){
        SQLiteDatabase db = getWritableDatabase();
        this.newVersion = newVersion;
        db.rawQuery("UPDATE Version SET version = '"+ newVersion + "'", null).close();
    }

    public int getVersionOfDB(int newVersion){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = null;
        try {
            c = db.rawQuery("SELECT version FROM Version", null);
        }catch (SQLException ex){
            createVersionTable(newVersion, false);
            c = db.rawQuery("SELECT version FROM Version", null);
        }
        if (c.moveToFirst()){
            Log.i("YES", "");
            newVersion = c.getInt(0);
            this.newVersion = newVersion;
        }else {
            this.newVersion = newVersion;
            createVersionTable(newVersion, true);
        }
        c.close();
        return newVersion;
    }

    public void createVersionTable(int version, boolean needChange){
        newVersion = version;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS Version(version INTEGER NOT NULL PRIMARY KEY)");
        Cursor c= null;
        if (needChange) {
            c = db.rawQuery("SELECT COUNT(version) FROM Version", null);
            if (c.moveToFirst())
                if (c.getInt(0) > 0)
                    db.rawQuery("UPDATE Version SET version = '" + newVersion + "'", null).close();
                else
                    db.rawQuery("INSERT INTO Version VALUES(" + newVersion + ")", null).close();
        }
        if (c != null)
            c.close();
    }

    public void changeStudent(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT MAX(id) FROM Students", null);
        c.moveToFirst();
        int id = c.getInt(0);
        String surname = "Иванов", name = "Иван", patronymic = "Иванович";
        db.execSQL("UPDATE Students SET Surname = '" + surname + "', Name = '" + name + "', Patronymic = '" + patronymic + "'" + " WHERE id = " + id);
        db.close();
    }

    public void addStudent(){
        String arraySurname[] = {
                "Николаев", "Шатилов", "Пачкин", "Медянник", "Барчо", "Лаврентьев", "Русаков",
                "Имасс", "Фурсов", "Трофимов", "Ананьев", "Аракелян", "Комов", "Аджигитов", "Маилян",
                "Санников", "Никифоров", "Хитров", "Кузнецов", "Торхов", "Крючков", "Ким", "Винтер"
        };
        String arrayName[] = {
                "Никита", "Анатолий", "Игорь", "Алексадр", "Руслан", "Леонид", "Филипп",
                "Матвей", "Фёдор", "Григорий", "Андрей", "Сергей", "Даниил", "Дмитрий", "Эдуард",
                "Радмир", "Алекс", "Эльвек", "Ярослав", "Василий", "Михаил", "Евгений", "Виктор"
        };
        String arrayPatronymic [] = {
                "Никитович", "Анатольевич", "Игоревич", "Александрович", "Русланович", "Леонидович", "Филиппович",
                "Матвеевич", "Фёдорович", "Григорьевич", "Андреевич", "Сергеев", "Даниилович", "Дмитриевич", "Эдуардович",
                "Радмирович", "Алексеевич", "Эльвекович", "Ярославоввич", "Васильевич", "Михаилович", "Евгеньевич", "Викторович"
        };
        SQLiteDatabase db = getWritableDatabase();
        Date date = new Date();
        Cursor c = db.rawQuery("SELECT MAX(id) FROM Students", null);
        c.moveToFirst();
        int id = c.getInt(0);
        ContentValues cv = new ContentValues();
        int randSurname = (int)(Math.random() * arraySurname.length), randName = (int)(Math.random() * arrayName.length), randPatronymic = (int)(Math.random() * arrayPatronymic.length);
        cv.put("id", id + 1);
        cv.put("Surname", arraySurname[randSurname]);
        cv.put("Name", arrayName[randName]);
        cv.put("Patronymic", arrayPatronymic[randPatronymic]);
        cv.put("Time", date.getTime());
        db.insert("Students", null, cv);
        c.close();
        db.close();
    }

    public void addFiveStudents(){
        SQLiteDatabase db = getWritableDatabase();

        for (int i = 0; i < 5; i++)
            addStudent();
        db.close();
    }

    public ArrayList<Student> readAll(){
        ArrayList<Student> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT id, Surname, Name, Patronymic FROM Students ORDER BY Time", null);
        if (c.moveToFirst()){
            do{
                list.add(new Student(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public void changeStudentOld(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT MAX(id) FROM Students", null);
        c.moveToFirst();
        int id = c.getInt(0);
        String t = "Иванов Иван Иванович";
        db.execSQL("UPDATE Students SET FIO = '" + t + "' WHERE id = " + id);
    }

    public void addStudentOld(){
        String arraySurname[] = {
                "Николаев", "Шатилов", "Пачкин", "Медянник", "Барчо", "Лаврентьев", "Русаков",
                "Имасс", "Фурсов", "Трофимов", "Ананьев", "Аракелян", "Комов", "Аджигитов", "Маилян",
                "Санников", "Никифоров", "Хитров", "Кузнецов", "Торхов", "Крючков", "Ким", "Винтер"
        };
        String arrayName[] = {
                "Никита", "Анатолий", "Игорь", "Алексадр", "Руслан", "Леонид", "Филипп",
                "Матвей", "Фёдор", "Григорий", "Андрей", "Сергей", "Даниил", "Дмитрий", "Эдуард",
                "Радмир", "Алекс", "Эльвек", "Ярослав", "Василий", "Михаил", "Евгений", "Виктор"
        };
        String arrayPatronymic [] = {
                "Никитович", "Анатольевич", "Игоревич", "Александрович", "Русланович", "Леонидович", "Филиппович",
                "Матвеевич", "Фёдорович", "Григорьевич", "Андреевич", "Сергеев", "Даниилович", "Дмитриевич", "Эдуардович",
                "Радмирович", "Алексеевич", "Эльвекович", "Ярославоввич", "Васильевич", "Михаилович", "Евгеньевич", "Викторович"
        };
        SQLiteDatabase db = getWritableDatabase();
        Date date = new Date();
        Cursor c = db.rawQuery("SELECT MAX(id) FROM Students", null);
        c.moveToFirst();
        int id = c.getInt(0);
        ContentValues cv = new ContentValues();
        int randSurname = (int)(Math.random() * arraySurname.length), randName = (int)(Math.random() * arrayName.length), randPatronymic = (int)(Math.random() * arrayPatronymic.length);
        String FIO = arraySurname[randSurname] + " " + arrayName[randName] + " " + arrayPatronymic[randPatronymic];
        cv.put("id", id + 1);
        cv.put("FIO", FIO);
        cv.put("Time", date.getTime());
        db.insert("Students", null, cv);
        c.close();
        db.close();
    }

    public void addFiveStudentsOld(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Students");

        for (int i = 0; i < 5; i++)
            addStudentOld();
        db.close();
    }

    public ArrayList<StudentOld> readAllOld(){
        ArrayList<StudentOld> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Students ORDER BY Time", null);
        if (c.moveToFirst()){
            do{
                list.add(new StudentOld(c.getInt(0), c.getString(1)));
                Log.i("КОНТАКТЫ = ", list.toString());
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public int getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(int newVersion) {
        this.newVersion = newVersion;
    }
}

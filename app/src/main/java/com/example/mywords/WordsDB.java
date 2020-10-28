package com.example.mywords;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mywords.wordcontract.Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordsDB {
    private static final String TAG = "myTag";
    private static WordsDBHelper mDbHelper;
    //采用单例模式
    private static WordsDB instance=new WordsDB();
    public static WordsDB getWordsDB(){
        return WordsDB.instance;
    }
    private WordsDB() {
        if (mDbHelper == null) {
            mDbHelper = new WordsDBHelper(WordsApplication.getContext());
        }
    }
    public void close() {
        if (mDbHelper != null)
            mDbHelper.close();
    }
    //获得单个单词的全部信息
    public Words.WordDescription getSingleWord(String id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // 查询
        Cursor cursor = db.rawQuery("select Id from words",new String[]{id});
        Words.WordDescription ww=new Words.WordDescription(cursor.getString(cursor.getColumnIndex("Id")),cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("meaning")),cursor.getString(cursor.getColumnIndex("sample")));
        return ww;
    }
    //得到全部单词列表
    public ArrayList<Map<String, String>> getAllWords() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // 查询表中所有的数据
        Cursor cursor = db.query("words",null,null,null,null,null,null);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                Map<String, String> map=new HashMap<>();
                map.put(cursor.getString(cursor.getColumnIndex("Id")),cursor.getString(cursor.getColumnIndex("word")));
                list.add(map);
            }
            cursor.close();
            db.close();//关闭数据库
        }
        return list;
    }
    //将游标转化为单词列表
    private ArrayList<Map<String, String>> ConvertCursor2WordList(Cursor cursor) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // 查询表中所有的数据
        cursor = db.query("words",null,null,null,null,null,null);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                Map<String, String> map=new HashMap<>();
                map.put(cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("meaning")));
                list.add(map);
            }
            cursor.close();
            db.close();//关闭数据库
    }
        return list;
    }
    //增加单词
    public void InsertUserSql(String strWord, String strMeaning, String strSample) {
        ContentValues values = new ContentValues();
        values.put("word",strWord);
        values.put("meaning",strMeaning);
        values.put("sample",strSample);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.insert("words",null,values);
    }
    public void Insert(String strWord, String strMeaning, String strSample) {
        ContentValues values = new ContentValues();
        values.put("word",strWord);
        values.put("meaning",strMeaning);
        values.put("sample",strSample);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.insert(strWord,null,values);
    }
    //删除单词
    public void DeleteUseSql(String strId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete("words","Id=?",new String[]{strId});
    }
    public void Delete(String strId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete("words","Id=?",new String[]{strId});
    }
    //更新单词
    public void UpdateUseSql(String strId, String strWord, String strMeaning, String strSample) {
        ContentValues values = new ContentValues();
        values.put("Id",strId);
        values.put("word",strWord);
        values.put("meaning",strMeaning);
        values.put("sample",strSample);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.update("words",values,"word=?",new String[]{strWord});
    }
    public void Update(String strId, String strWord, String strMeaning, String strSample) {
        ContentValues values = new ContentValues();
        values.put("Id",strId);
        values.put("word",strWord);
        values.put("meaning",strMeaning);
        values.put("sample",strSample);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.update("words",values,"word=?",new String[]{strWord});
    }
    //查找
    public ArrayList<Map<String, String>> SearchUseSql(String strWordSearch) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // 查询
        Cursor cursor = db.rawQuery("select word from words",new String[]{strWordSearch});
        ArrayList<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map=new HashMap<>();
        map.put(cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("meaning")));
        list.add(map);
        return list;
    }
    public ArrayList<Map<String, String>> Search(String strWordSearch) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // 查询
        Cursor cursor = db.rawQuery("select word from words",new String[]{strWordSearch});
        ArrayList<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map=new HashMap<>();
        map.put(cursor.getString(cursor.getColumnIndex("word")),cursor.getString(cursor.getColumnIndex("meaning")));
        list.add(map);
        return list;
    }

}

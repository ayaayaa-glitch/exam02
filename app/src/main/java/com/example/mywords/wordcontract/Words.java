package com.example.mywords.wordcontract;

import android.provider.BaseColumns;

public class Words {
    //单词列表项
    public static class WordItem {
        public String id;
        public String word;
        public WordItem(String id, String word) {
            this.id = id;
            this.word = word;
        }
        @Override
        public String toString() {
            return word;
        }
    }
    //每个单词的描述
    public static class WordDescription {
        public String id;
        public String word;
        public String meaning;
        public String sample;
        public WordDescription(String id, String word,String meaning, String sample) {
            this.id = id;
            this.word = word;
            this.meaning = meaning;
            this.sample = sample;
        }
    }
    //单词
    public static abstract class Word implements BaseColumns {
        public static final String TABLE_NAME = "words";//表名
        // _ID字段：主键
        public static final String COLUMN_NAME_WORD ="word";//字段：单词
        public static final String COLUMN_NAME_MEANING ="meaning";//字段：单词含义
        public static final String COLUMN_NAME_SAMPLE ="sample";//字段：单词示例
    }
}

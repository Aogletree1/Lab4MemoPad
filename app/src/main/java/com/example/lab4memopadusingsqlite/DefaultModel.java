package com.example.lab4memopadusingsqlite;

import android.util.Log;

public class DefaultModel extends AbstractModel{
    public static final String TAG = "DefaultModel";
    private DatabaseHandler db;

    private String memo1;

    public DefaultModel(DatabaseHandler db) {
        super();
        this.db = db;
    }

    public void initDefault() {

        try{
        setMemoContents(db.getAllMemos());}
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setMemoContents(String newText) {
        String oldText = this.memo1;
        this.memo1 = newText;

        Log.i(TAG, "Memo Text change: From " + oldText + " to " + newText);

        firePropertyChange(DefaultController.DATABASE_TEXT, oldText, newText);

    }


}

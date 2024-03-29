package com.example.lab4memopadusingsqlite;

public class DefaultController extends AbstractController{

    /*
     * These static property names are used as the identifiers for the elements
     * of the Models and Views which may need to be updated.  These updates can
     * be a result of changes to the Model which must be reflected in the View,
     * or a result of changes to the View (in response to user input) which must
     * be reflected in the Model.
     */

    public static final String DATABASE_TEXT = "memoContents";
    private DatabaseHandler db;

    public DefaultController(DatabaseHandler db) {
        super();
        this.db = db;
    }

    public void processInput(String tag) {
        setModelProperty(DATABASE_TEXT, tag);

    }

    public void addMemo(String newText){
        Memo temp = new Memo(newText);
        db.addMemo(temp);
        setModelProperty(DATABASE_TEXT, newText);

    }


    public void deleteMemo(String newText){
        String temp = newText;

        db.memoToDelete(temp);
        setModelProperty(DATABASE_TEXT, newText);
    }

    public void printScreen(){

    }
        }

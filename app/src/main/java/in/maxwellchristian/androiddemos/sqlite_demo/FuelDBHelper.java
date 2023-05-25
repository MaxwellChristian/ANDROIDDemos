package in.maxwellchristian.androiddemos.sqlite_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FuelDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbFuel";
    private static final int DATABASE_VERSION = 1;

    // the table name
    private static final String TABLE_FUEL_PURCHASES = "tblFuelPurchases";

    // the field names
    private static final String ID = "_id";
    public static final String PURCHASE_DATE = "dPurchaseDate";
    private static final String PURCHASED_LITRES = "nPurchasedLitres";
    private static final String PURCHASED_AT_COST = "nPurchasedAtCost";

    public FuelDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sCreate = "create table "
                + TABLE_FUEL_PURCHASES
                + " ( "
                + ID + " integer primary key autoincrement, "
                + PURCHASE_DATE + " text not null, "
                + PURCHASED_LITRES + " double not null, "
                + PURCHASED_AT_COST + " double not null"
                + " ); ";

        //Execute the create table sql statement
        db.execSQL(sCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop the existing table(s) if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUEL_PURCHASES);

        //Use the existing onCreate code to recreate the table
        onCreate(db);
    }

    public int remove(FuelPurchase purchase) {

        SQLiteDatabase sqlDB = this.getWritableDatabase();

        //Delete will return the number of rows affected. Compare this to 0 and return the value.
        return sqlDB.delete(TABLE_FUEL_PURCHASES, ID + "=" + purchase.id, null);
    }

    public Cursor getAllFuelPurchases() {
        SQLiteDatabase sqlDB = this.getReadableDatabase();

        String[] sFields = new String[]{ID, PURCHASE_DATE, PURCHASED_LITRES,
                PURCHASED_AT_COST};

        return sqlDB.query(TABLE_FUEL_PURCHASES, sFields, null, null, null,
                null, null);
    }

    public int updateFuelPurchase(FuelPurchase purchase) {

        if (purchase.id >= 0) {

            //A container storing each column value for a row ContentValues
            ContentValues cvs = new ContentValues();

            //Add values for each column of the row
            cvs.put(PURCHASE_DATE, purchase.date);
            cvs.put(PURCHASED_LITRES, purchase.litres);
            cvs.put(PURCHASED_AT_COST, purchase.cost);

            SQLiteDatabase sqlDB = this.getWritableDatabase();

            return sqlDB.update(TABLE_FUEL_PURCHASES, cvs,
                    ID + " = " + purchase.id, null);
        }
        return 0;
    }

    public long addFuelPurchase(FuelPurchase purchase) {

        //A container storing each column value for a row
        ContentValues cvs = new ContentValues();

        //Add values for each column of the row
        cvs.put(PURCHASE_DATE, purchase.date);
        cvs.put(PURCHASED_LITRES, purchase.litres);
        cvs.put(PURCHASED_AT_COST, purchase.cost);

        //Execute the insert statement, which returns the autoincrement value
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        return sqlDB.insert(TABLE_FUEL_PURCHASES, null, cvs);

    }

    public Cursor getFuelPurchase(long id) {

        String[] sFields = new String[]{ID, PURCHASE_DATE, PURCHASED_LITRES,
                PURCHASED_AT_COST};

        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor fpCursor =
                sqlDB.query(true, TABLE_FUEL_PURCHASES, sFields, ID + " = "
                        + id, null, null, null, null, null);

        if (fpCursor != null) {
            fpCursor.moveToFirst();
        }

        return fpCursor;
    }

    public Cursor getAllFuelPurchaseUsingSQL() {

        String sSelect = "SELECT * FROM " + TABLE_FUEL_PURCHASES;

        return this.getReadableDatabase().rawQuery(sSelect, null);

    }
}

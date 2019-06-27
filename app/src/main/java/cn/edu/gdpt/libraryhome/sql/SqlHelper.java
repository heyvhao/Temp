package cn.edu.gdpt.libraryhome.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import cn.edu.gdpt.libraryhome.bean.Userinfo;

public class SqlHelper extends OrmLiteSqliteOpenHelper {
    public SqlHelper(Context context) {
        super(context, "yp", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Userinfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
    public boolean AddUserinfo(Userinfo userinfo) throws SQLException {
        boolean b = SelectUserinfo(userinfo);
        if(b==false){
            int i = getDao(Userinfo.class).create(userinfo);
            if(i>=1){
                return true;
            }else{
                return false;
            }
        }

        return false;
    }
    public boolean SelectUserinfo(Userinfo userinfo) throws SQLException {
        List<Userinfo> userinfos = getDao(Userinfo.class).queryForAll();
        if(userinfos!=null){
        for (int i = 0; i < userinfos.size(); i++) {
            String name = userinfos.get(i).getName();
            if(name.equals(userinfo.getName())){
                return true;
            }
        }}
        return false;
    }
}

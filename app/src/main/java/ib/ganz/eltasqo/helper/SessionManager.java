package ib.ganz.eltasqo.helper;

import android.content.Context;
import android.content.SharedPreferences;

import ib.ganz.eltasqo.dataclass.UserData;

/**
 * Created by limakali on 3/18/2018.
 */

public class SessionManager
{
    private static final String USER_DATA = "userdata";
    private static final String IS_LOGIN = "islogin";

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public static void init(Context c)
    {
        if (sp == null || editor == null)
        {
            sp = c.getSharedPreferences("eltasqow", 0);
            editor = sp.edit();
        }
    }

    public static void login(UserData u)
    {
        editor.putString(USER_DATA, Gxon.toJsonObject(u)).commit();
        editor.putBoolean(IS_LOGIN, true).commit();
    }

    public static boolean isLogin()
    {
        return sp.getBoolean(IS_LOGIN, false);
    }

    public static void logout()
    {
        editor.putBoolean(IS_LOGIN, false).commit();
    }

    public static UserData getUserData()
    {
        return Gxon.fromJsonObject(sp.getString(USER_DATA, ""), UserData.class);
    }

    public static void setUserData(UserData u)
    {
        editor.putString(USER_DATA, Gxon.toJsonObject(u)).commit();
    }

    public static String getIdUser()
    {
        return getUserData().getIdUser();
    }
}

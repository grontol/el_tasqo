package ib.ganz.eltasqo.dataclass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by limakali on 4/25/2018.
 */

public class UserData
{
    @SerializedName("id_user") String idUser;
    @SerializedName("nama_user") String namaUser;

    public String getIdUser()
    {
        return idUser;
    }

    public String getNamaUser()
    {
        return namaUser;
    }

    @Override
    public String toString()
    {
        return getNamaUser();
    }
}

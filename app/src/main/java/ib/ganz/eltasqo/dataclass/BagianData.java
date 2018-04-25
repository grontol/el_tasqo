package ib.ganz.eltasqo.dataclass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by limakali on 4/25/2018.
 */

public class BagianData
{
    @SerializedName("id_bagian") String idBagian;
    @SerializedName("nama_bagian") String namaBagian;
    @SerializedName("warna") String warna;

    public String getIdBagian()
    {
        return idBagian;
    }

    public String getNamaBagian()
    {
        return namaBagian;
    }

    public String getWarna()
    {
        return warna;
    }
}

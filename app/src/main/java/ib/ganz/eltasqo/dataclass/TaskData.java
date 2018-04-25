package ib.ganz.eltasqo.dataclass;

import com.google.gson.annotations.SerializedName;

/**
 * Created by limakali on 4/25/2018.
 */

public class TaskData
{
    public static final String PENDING = "Pending";
    public static final String PROSES = "Proses";
    public static final String SELESAI = "Selesai";

    @SerializedName("id_task")      String idTask;
    @SerializedName("judul")        String judul;
    @SerializedName("keterangan")   String keterangan;
    @SerializedName("prioritas")    float prioritas;
    @SerializedName("id_bagian")    String idBagian;
    @SerializedName("id_user")      String idUser;
    @SerializedName("status")       String status;

    @SerializedName("bagian")       BagianData bagianData;
    @SerializedName("user")         UserData userData;

    public String getIdTask()
    {
        return idTask;
    }

    public String getJudul()
    {
        return judul;
    }

    public String getKeterangan()
    {
        return keterangan;
    }

    public float getPrioritas()
    {
        return prioritas;
    }

    public String getIdBagian()
    {
        return idBagian;
    }

    public String getIdUser()
    {
        return idUser;
    }

    public String getStatus()
    {
        return status;
    }

    public BagianData getBagianData()
    {
        return bagianData;
    }

    public UserData getUserData()
    {
        return userData;
    }
}

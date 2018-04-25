package ib.ganz.eltasqo.service;

import java.util.List;

import ib.ganz.eltasqo.dataclass.BagianData;
import ib.ganz.eltasqo.dataclass.TaskData;
import ib.ganz.eltasqo.dataclass.UserData;
import ib.ganz.eltasqo.helper.Develop;
import ib.ganz.eltasqo.service.client.ApiClient;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by limakali on 3/17/2018.
 */

public class Servize
{
    private static ElTasqoService service;

    private static ElTasqoService getService()
    {
        if (service == null || Develop.isIpChange)
        {
            service = ApiClient.getRetrofit().create(ElTasqoService.class);

            if (Develop.isIpChange)
            {
                Develop.isIpChange = false;
            }
        }

        return service;
    }

    public static Observable<List<UserData>> getUser()
    {
        return getService().getUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    
    public static Observable<List<BagianData>> getBagian()
    {
        return getService().getBagian().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    
    public static Observable<List<TaskData>> getTask()
    {
        return getService().getTask().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    
    public static Observable<TaskData> addTask(String judul, String keterangan, float prioritas, String idBagian)
    {
        return getService().addTask(judul, keterangan, prioritas, idBagian)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    
    public static Observable<TaskData> editTask(String idTask, String judul, String keterangan, float prioritas, String idBagian)
    {
        return getService().editTask(idTask, judul, keterangan, prioritas, idBagian)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    
    public static Observable<Object> deleteTask(String idTask)
    {
        return getService().deleteTask(idTask)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    
    public static Observable<TaskData> updateTaskStatus(String idTask, String status)
    {
        return getService().updateTastStatus(idTask, status)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    
    public static Observable<TaskData> assignTask(String idTask, String idUser)
    {
        return getService().assignTask(idTask, idUser)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}

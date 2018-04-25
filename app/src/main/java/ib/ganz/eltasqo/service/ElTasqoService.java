package ib.ganz.eltasqo.service;

import java.util.List;

import ib.ganz.eltasqo.dataclass.BagianData;
import ib.ganz.eltasqo.dataclass.BaseResponse;
import ib.ganz.eltasqo.dataclass.TaskData;
import ib.ganz.eltasqo.dataclass.UserData;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by limakali on 3/17/2018.
 */

public interface ElTasqoService
{
    @GET("get_all_user.php")
    Observable<List<UserData>> getUser();
    
    @GET("get_all_bagian.php")
    Observable<List<BagianData>> getBagian();
    
    @GET("get_all_task.php")
    Observable<List<TaskData>> getTask();
    
    @POST("add_task.php")
    @FormUrlEncoded
    Observable<TaskData> addTask
            (
                    @Field("judul") String judul,
                    @Field("keterangan") String keterangan,
                    @Field("prioritas") float prioritas,
                    @Field("id_bagian") String idBagian
            );
    
    @POST("edit_task.php")
    @FormUrlEncoded
    Observable<TaskData> editTask
            (
                    @Field("id_task") String idTask,
                    @Field("judul") String judul,
                    @Field("keterangan") String keterangan,
                    @Field("prioritas") float prioritas,
                    @Field("id_bagian") String idBagian
            );
    
    @POST("delete_task.php")
    @FormUrlEncoded
    Observable<BaseResponse> deleteTask(@Field("id_task") String idTask);
    
    @POST("update_task_status.php")
    @FormUrlEncoded
    Observable<TaskData> updateTastStatus
            (
                    @Field("id_task") String idTask,
                    @Field("status") String status
            );
    
    @POST("assign_task.php")
    @FormUrlEncoded
    Observable<TaskData> assignTask
            (
                    @Field("id_task") String idTask,
                    @Field("id_user") String idUser
            );
}

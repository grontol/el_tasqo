package ib.ganz.eltasqo.service;

import java.util.List;

import ib.ganz.eltasqo.dataclass.UserData;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by limakali on 3/17/2018.
 */

public interface ElTasqoService
{
    @GET("user.php")
    Observable<List<UserData>> getUser();
}

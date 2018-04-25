package ib.ganz.eltasqo.service;

import java.util.List;

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
}

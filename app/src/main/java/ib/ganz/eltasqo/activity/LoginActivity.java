package ib.ganz.eltasqo.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ib.ganz.eltasqo.R;
import ib.ganz.eltasqo.activity.baseactivity.BaseActivity;
import ib.ganz.eltasqo.dataclass.UserData;
import ib.ganz.eltasqo.helper.DialogManager;
import ib.ganz.eltasqo.helper.ListUserDialog;
import ib.ganz.eltasqo.helper.SessionManager;
import ib.ganz.eltasqo.service.Servize;

public class LoginActivity extends BaseActivity
{
    @BindView(R.id.btnLogin)    Button btnLogin;
    @BindView(R.id.edtNama)     EditText edtNama;

    List<UserData> lUser;
    UserData selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (SessionManager.isLogin())
        {
            MainActivity.go(this);
            return;
        }

        lUser = new ArrayList<>();

        edtNama.setOnClickListener(x -> ListUserDialog.create(lUser).show(getSupportFragmentManager(), u -> selectedUser = u));
        btnLogin.setOnClickListener(x -> DialogManager.confirmLogin(this, selectedUser.getNamaUser(), () -> MainActivity.go(this)));

        getData();
    }

    private void getData()
    {
        getCompositeDisposablePD().add(Servize.getUser().subscribe(r ->
        {
            closeProgressDialog();

            lUser.clear();
            lUser.addAll(r);
            if (lUser.size() > 0) selectedUser = lUser.get(0);
        }, e ->
        {
            closeProgressDialog();
            makeToastPeriksaKoneksi();
        }));
    }
}

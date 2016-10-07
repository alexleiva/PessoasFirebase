package br.com.bleiva.alex.pessoasfirebase;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.bleiva.alex.pessoasfirebase.databinding.ActivityCadastroBinding;

public class CadastroActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference pessoasRef;
    private ActivityCadastroBinding mBinding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cadastro);
        mBinding.setPessoa(new Pessoa());

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        pessoasRef = database.getReference("pessoa").child(auth.getCurrentUser().getUid());
    }

    public void clickSalvar(View view) {
        pessoasRef.push().setValue(mBinding.getPessoa());
    }
}

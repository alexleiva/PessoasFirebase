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

    public static final String EXTRA_PESSOA = "pessoa";
    private FirebaseDatabase database;
    private DatabaseReference pessoasRef;
    private ActivityCadastroBinding mBinding;
    private FirebaseAuth auth;
    private boolean isNewPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cadastro);

        Pessoa pessoa = (Pessoa) getIntent().getSerializableExtra(EXTRA_PESSOA);
        isNewPessoa = pessoa == null;
        if (isNewPessoa) {
            mBinding.setPessoa(new Pessoa());
        } else {
            mBinding.setPessoa(pessoa);
        }

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        pessoasRef = database.getReference("pessoa").child(auth.getCurrentUser().getUid());
    }

    public void clickSalvar(View view) {
        Pessoa pessoa = mBinding.getPessoa();
        if (isNewPessoa) {
            pessoasRef.push().setValue(pessoa);
        } else {
            pessoasRef.child(pessoa.getId()).setValue(pessoa);
        }
        finish();
    }
}

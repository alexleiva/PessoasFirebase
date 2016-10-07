package br.com.bleiva.alex.pessoasfirebase;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.bleiva.alex.pessoasfirebase.databinding.ActivityMainBinding;
import br.com.bleiva.alex.pessoasfirebase.databinding.ItemPessoaBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database;
    private DatabaseReference pessoasRef;
    private FirebaseRecyclerAdapter<Pessoa, PessoaHolder> mAdapter;

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    initFirebase();
                    initUI();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    finish();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };
    }

    private void initUI() {
        mainBinding.recyclerView.setHasFixedSize(true);
        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Pessoa, PessoaHolder>(Pessoa.class, R.layout.item_pessoa, PessoaHolder.class, pessoasRef) {
            @Override
            public void populateViewHolder(PessoaHolder pessoaHolder, Pessoa pessoa, int position) {
                pessoaHolder.setPessoa(pessoa);
            }
        };
        mainBinding.recyclerView.setAdapter(mAdapter);
    }

    private void initFirebase() {
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        pessoasRef = database.getReference("pessoa").child(mAuth.getCurrentUser().getUid());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sign_out) {
            FirebaseAuth.getInstance().signOut();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void clickNovo(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public static class PessoaHolder extends RecyclerView.ViewHolder {
        ItemPessoaBinding mBinding;

        public PessoaHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }

        public void setPessoa(Pessoa pessoa) {
            mBinding.setPessoa(pessoa);
        }
    }
}

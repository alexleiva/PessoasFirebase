package br.com.bleiva.alex.pessoasfirebase;

import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import static br.com.bleiva.alex.pessoasfirebase.BR.pessoa;

/**
 * Created by Desenvolvedor on 10/10/2016.
 */

public class PessoaAdapter extends FirebaseRecyclerAdapter<Pessoa, PessoaHolder> {

    private PessoaListener mListener;

    @Override
    public PessoaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final PessoaHolder holder = super.onCreateViewHolder(parent, viewType);

        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onPessoaClicked(getItem(holder.getAdapterPosition()));
                }
            }
        });

        return holder;
    }

    public PessoaAdapter(Query ref, PessoaListener listener) {
        super(Pessoa.class, R.layout.item_pessoa, PessoaHolder.class, ref);

        this.mListener = listener;
    }

    @Override
    protected void populateViewHolder(PessoaHolder viewHolder, Pessoa model, int position) {
        viewHolder.setPessoa(model);
    }

    public interface PessoaListener {
        void onPessoaClicked(Pessoa pessoa);
    }
}

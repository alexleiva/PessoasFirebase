package br.com.bleiva.alex.pessoasfirebase;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.bleiva.alex.pessoasfirebase.databinding.ItemPessoaBinding;

/**
 * Created by Desenvolvedor on 10/10/2016.
 */

public class PessoaHolder extends RecyclerView.ViewHolder {

    ItemPessoaBinding mBinding;

    public PessoaHolder(View view) {
        super(view);
        mBinding = DataBindingUtil.bind(view);
    }

    public void setPessoa(Pessoa pessoa) {
        mBinding.setPessoa(pessoa);
    }
}

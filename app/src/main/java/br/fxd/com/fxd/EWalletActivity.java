package br.fxd.com.fxd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import br.fxd.com.fxd.adapter.EWalletCustomAdapter;
import br.fxd.com.fxd.model.EWallet;

public class EWalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewallet);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView listViewProduto;
        listViewProduto = (ListView) findViewById(R.id.listEWallet);

        ArrayList<EWallet> eWallets = new ArrayList<EWallet>();

        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "24/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Volta para casa", "R$6,80 economizado (Metrô + integração)", "25/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "26/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Volta para casa", "R$6,80 economizado (Metrô + integração)", "27/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "28/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "24/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Volta para casa", "R$6,80 economizado (Metrô + integração)", "25/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "26/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Volta para casa", "R$6,80 economizado (Metrô + integração)", "27/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "28/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "24/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Volta para casa", "R$6,80 economizado (Metrô + integração)", "25/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "26/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Volta para casa", "R$6,80 economizado (Metrô + integração)", "27/09/2017"));
        eWallets.add(new EWallet(1, R.drawable.maps, "Ida para o trabalho", "R$6,80 economizado (Metrô + integração)", "28/09/2017"));

        EWalletCustomAdapter eWalletCustomAdapter;
        eWalletCustomAdapter = new EWalletCustomAdapter(eWallets, this);

        listViewProduto.setAdapter(eWalletCustomAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}

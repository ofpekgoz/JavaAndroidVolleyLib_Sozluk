package  com.omerfpekgoz.udemy_61_uygulama_sozluk_volleykutuphanesi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KelimelerAdapter extends RecyclerView.Adapter<KelimelerAdapter.cardTasarimTutucu> {

    private Context mContext;
    private List<Kelimeler> kelimelerList;
    LayoutInflater layoutInflater;

    public KelimelerAdapter(Context mContext, List<Kelimeler> kelimelerList) {
        this.mContext = mContext;
        this.kelimelerList = kelimelerList;
        layoutInflater=LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public cardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= layoutInflater.inflate(R.layout.card_tasarim,parent,false);  //CardTasarim tasarımına eriştik ve tanımladık

        return new cardTasarimTutucu(view);   //aşağıdaki sınıfıa bunu göncerdik
    }

    @Override
    public void onBindViewHolder(@NonNull cardTasarimTutucu holder, int position) {  //Satır satır verileri yerleştirecek ve tıklamalar

        final Kelimeler kelime=kelimelerList.get(position);  //Kelimeler listesindeki position ile birer birer eriştik
        holder.txtKelimeIngilizce.setText(kelime.getIngilizce());
        holder.txtKelimeTurkce.setText(kelime.getTurkce());

        holder.kelimeCard.setOnClickListener(new View.OnClickListener() {   //Bir kelime kartına tıklanıldıpında bura çalışacak
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext,DetayActivity.class);
                intent.putExtra("kelime",kelime);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {  //Recycler içinde kaç tane veri gözükecek
        return kelimelerList.size();
    }



    public class cardTasarimTutucu extends RecyclerView.ViewHolder {   //Card tasarımı göstermek için sınıf tanımladık

        private TextView txtKelimeIngilizce;
        private TextView txtKelimeTurkce;
        private CardView kelimeCard;

        public cardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            txtKelimeIngilizce=itemView.findViewById(R.id.txtKelimeIngilizce);
            txtKelimeTurkce=itemView.findViewById(R.id.txtKelilmeTurkce);
            kelimeCard=itemView.findViewById(R.id.kelimeCard);


        }
    }
}

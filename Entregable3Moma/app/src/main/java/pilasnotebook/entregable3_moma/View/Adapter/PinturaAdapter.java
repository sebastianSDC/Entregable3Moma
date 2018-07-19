package pilasnotebook.entregable3_moma.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import pilasnotebook.entregable3_moma.Model.Pintura;
import pilasnotebook.entregable3_moma.R;
import pilasnotebook.entregable3_moma.View.Aplication.MyAplicattion;

public class PinturaAdapter extends RecyclerView.Adapter {

    private List<Pintura> listadepinturas;
    NotificadorPinturaCelda notificadorPinturaCelda;

    public PinturaAdapter(List<Pintura> pinturas, NotificadorPinturaCelda notificadorPinturaCelda) {
        listadepinturas = pinturas;
        this.notificadorPinturaCelda = notificadorPinturaCelda;
        if (pinturas == null) {
            listadepinturas = new ArrayList<>();

        }
    }

    public void setNotificadorPinturaCelda(NotificadorPinturaCelda notificadorPinturaCelda) {
        this.notificadorPinturaCelda = notificadorPinturaCelda;
    }

    public PinturaAdapter() {
        listadepinturas = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View view = inflador.inflate(R.layout.celda_recycler_pinturas, parent, false);
        ViewHolderPintura viewHolderPintura = new ViewHolderPintura(view);
        return viewHolderPintura;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pintura pinturaQuePasoAlViewHolder = listadepinturas.get(position);
        ViewHolderPintura viewHolderPintura = (ViewHolderPintura) holder;
        viewHolderPintura.cargarPintura(pinturaQuePasoAlViewHolder);

    }

    @Override
    public int getItemCount() {
        return listadepinturas.size();
    }

    public void agregarPinturas(List<Pintura> pinturas) {
        listadepinturas.addAll(pinturas);

        notifyDataSetChanged();
    }


    public class ViewHolderPintura extends RecyclerView.ViewHolder {

        private TextView titulo;
        private CardView celda;
        private ImageView foto_pintura;


        public ViewHolderPintura(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.nombre_de_obra);
            foto_pintura = itemView.findViewById(R.id.foto_pintura);
            celda = itemView.findViewById(R.id.container_card);
            celda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pintura pintura = listadepinturas.get(getAdapterPosition());
                    notificadorPinturaCelda.notificarPinturaClickeada(pintura);

                }
            });

        }


        public void cargarPintura(Pintura pintura) {
            titulo.setText(pintura.getName());
            //para cargar la imagen de la pintura/obra instancio el firebasestorage para pedirla a traves de glide
            //como me di cuenta que repito codigo lo transformo en un metodo para usarlo en otras activity.
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference storageReference = firebaseStorage.getReference();
            // le indico la ruta de donde buscar le imagen
            storageReference = storageReference.child(pintura.getImage());

            Glide.with(itemView.getContext())
                    .using(new FirebaseImageLoader())
                    .load(storageReference)
                    .into(foto_pintura);
        }

    }

    public interface NotificadorPinturaCelda {
        void notificarPinturaClickeada(Pintura pintura);
    }

}

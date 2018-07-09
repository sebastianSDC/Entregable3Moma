package pilasnotebook.entregable3_moma.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pilasnotebook.entregable3_moma.Model.Pintura;
import pilasnotebook.entregable3_moma.R;

public class PinturaAdapter extends RecyclerView.Adapter {

    private List<Pintura> listadepinturas;

    public PinturaAdapter(List<Pintura> pinturas) {
        listadepinturas = pinturas;
        if (pinturas == null) {
            listadepinturas = new ArrayList<>();
        }
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

    public void agregarPinturas(List<Pintura> pinturas){
        listadepinturas.addAll(pinturas);

        notifyDataSetChanged();
    }




    public class ViewHolderPintura extends RecyclerView.ViewHolder {

        private TextView titulo;

        private ImageView foto_pintura;


        public ViewHolderPintura(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.nombre_de_obra);

            foto_pintura = itemView.findViewById(R.id.foto_pintura);
        }

        public void cargarPintura(Pintura pintura) {
            titulo.setText(pintura.getName());


        }
    }
}

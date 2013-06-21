package dom.autos;


import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.apache.isis.applib.AbstractContainedObject;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.filter.Filter;


import com.google.common.base.Objects;


import dom.autos.Autos;
import dom.autos.Autos.Estado;
import dom.autos.Autos.Marca;

import dom.autos.Autos.Seguro;
import dom.autos.Autos.TipoCombustible;
import dom.todo.ToDoItem;
 

@Named("Autos")
public class AutosServicio extends AbstractContainedObject
{

	 @MemberOrder(sequence = "2")
	 public List<Autos> ListarAutos() 
	 {

		 final List<Autos> autos= allInstances(Autos.class);
 
		 return autos;
		}
	
	 
	@MemberOrder(sequence = "1")
	public Autos CargarAuto(
			@Named("Patente") String patente,
			@Named("Marca") Marca marca, 
			@Named("Modelo") String modelo, 
			@Named("Año") int ano,
			@Named("Color") String color,
			@Named("Kilometraje") int kms,
			@Named("Capacidad Baul (lt)") int baul,
			@Named("Tipo de Combustible") TipoCombustible combustible,
			@Named("Estado de Alquiler") Estado estado,
			@Named("Fecha de Compra") Date fechaCompra,
			@Named("Compañía de Seguro")Seguro seguro,
			@Named("Categoria")Autos categoria)
	       
	   { final String ownedBy = currentUserName();
	     return elAuto(patente,marca,modelo,ano,color,kms,baul,combustible,estado,fechaCompra,seguro,ownedBy,categoria);
	   }
		 
	@Hidden // for use by fixtures
	public Autos elAuto(
		   String patente,
		   Marca marca, 
		   String modelo,
		   int ano,
		   String color,
		   int kms, 
		   int baul,
		   TipoCombustible combustible,
		   Estado estado,
		   Date fechaCompra,
		   Seguro seguro,
		   String userName,
		   Autos categoria) 
	{
		 final Autos auto = newTransientInstance(Autos.class);
		   auto.setPatente(patente);
		   auto.setMarca(marca);
		   auto.setModelo(modelo);
		   auto.setAno(ano);
		   auto.setColor(color);
		   auto.setKilometraje(kms);
		   auto.setCapacidadBaul(baul);
		   auto.setTipoCombustible(combustible);
		   auto.setEstado(estado);
		   auto.setFechaCompra(fechaCompra);
		   auto.setSeguro(seguro);
		   auto.setOwnedBy(userName);
 
      
		        
       persist(auto);
       return auto;
    }
	
	
	@Hidden
    public List<Categoria> completaComboCategoria(final String categoria)
    {
        return allMatches(Categoria.class, new Filter<Categoria>() 
        {
            @Override
            public boolean accept(final Categoria t) 
            {
                return t.getCategorias().contains(categoria);

            }

        });
    }
	
	@MemberOrder(sequence = "3")
	public Marcas CargarMarca(@Named("Marca") String marcas)
	   { 
	     return laMarca(marcas);
	   }
	  @Hidden 
	 public Marcas laMarca(
		   String marcas)  
	{
		 final Marcas aux = newTransientInstance(Marcas.class);
		   aux.setMarcas(marcas);
		   persist(aux);
		   return aux;
	}
	
	//------------------------------------------------------------  
	  @MemberOrder(sequence = "5")
		public Categoria CargarCategoria(@Named("Categoria") String categoria,@Named("cantidad de puertas") int cantPuertas)
		   { 
		     return laCategoria(categoria,cantPuertas);
		   }
	  @Hidden 
	  public Categoria laCategoria(String categoria,int cantPuertas)  
		{
			 final Categoria auxCate = newTransientInstance(Categoria.class);
			   auxCate.setCategorias(categoria);
			   auxCate.setCantPlazas(cantPuertas);
			   persist(auxCate);
			   return auxCate;
		}
	
	@MemberOrder(sequence = "4")
	public List<Marcas> ListarMarcas() 
	{

		 final List<Marcas> aux= allInstances(Marcas.class);
		 
		 return aux;
    }


	protected boolean ownedByCurrentUser(final Autos t) 
	{
	    return Objects.equal(t.getOwnedBy(), currentUserName());
	}
	

	    protected String currentUserName() 
	    {
	        return getContainer().getUser().getName();
	    }


}
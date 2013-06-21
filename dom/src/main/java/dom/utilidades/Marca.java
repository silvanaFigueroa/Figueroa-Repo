package dom.utilidades;


import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import org.apache.isis.applib.Identifier.Type;
import org.apache.isis.applib.DomainObjectContainer;


import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NotPersisted;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.Resolve;
import org.apache.isis.applib.filter.Filter;
import org.apache.isis.applib.util.TitleBuffer;
import org.apache.isis.core.objectstore.jdo.applib.annotations.Auditable;

import com.google.common.base.Objects;

import dom.autos.Auto;
import dom.autos.AutoServicio;

import javax.jdo.annotations.VersionStrategy;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
@javax.jdo.annotations.Query(name="listado_marcas", language="JDQL",
							value="SELECT FROM dom.utilidades.Marca WHERE ownedBy == :ownedBy")})
@javax.jdo.annotations.Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSION")
@ObjectType("MARCA")
@Auditable
@AutoComplete(repository=UtilidadesServicio.class, action="autoComplete")


public class Marca
{
	
	// {{ Identification on the UI	
	public String title() 
	{
		final TitleBuffer buf = new TitleBuffer();		
		buf.append(getNombre());		       
		return buf.toString(); 
	}
	// }}
	
	// {{ OwnedBy (property)
	private String ownedBy;	
	@Hidden
	// not shown in the UI
	public String getOwnedBy() 
	{
	    return ownedBy;	
	}
	public void setOwnedBy(final String ownedBy) 
	
	{
	    this.ownedBy = ownedBy; 
	}	    
	// }}	  
	
	//{{ Marca
	private String nombre;
	@DescribedAs("La marca del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="1")
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	
	//METODO RECOLECTA DATOS DEL SERVICIO
	
//	private AutoServicio toDoItems;
//	
//	@MemberOrder(sequence = "5")
//	 @NotPersisted
//	 
//	 
//	 public List<Auto> getAutos() 
//	 {
//	  
//	
//	  
//	  }
//
//	public void setToDoItems(final AutoServicio toDoItems) 
//    {
//        this.toDoItems = toDoItems;
//    }

	
	
	

	
	public static Filter <Marca> thoseOwnedBy(final String currentUser) 
	{
        	return new Filter<Marca>() 
        			{
            @Override
            public boolean accept(final Marca marca) 
            {
                return Objects.equal(marca.getOwnedBy(), currentUser);
            }
        };
    }
	// }}
	
	// {{ injected: DomainObjectContainer
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    public void setDomainObjectContainer(final DomainObjectContainer container) 
    {
        this.container = container;
    }
    // }}
  	
}

package dom.autos;


import java.util.Date;
<<<<<<< HEAD
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
=======
>>>>>>> 167463b172ee3990894d536d2f9b4a1cecf60d2e

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.Resolve;
import org.apache.isis.applib.annotation.Resolve.Type;
import org.apache.isis.applib.util.TitleBuffer;
import org.apache.isis.core.objectstore.jdo.applib.annotations.Auditable;



@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Query(name="listado", language="JDQL",value="SELECT * FROM dom.autos.Autos")
@javax.jdo.annotations.Version(strategy=VersionStrategy.VERSION_NUMBER, column="VERSION")



@Auditable
@AutoComplete(repository=AutosServicio.class, action="completaComboCategoria")
public class Autos 
{
	
	public static enum Marca 
	{
        CHEVROLET, CITROEN, FIAT, FORD, RENAULT, TOYOTA, VOLKSWAGEN ;
    }
	public static enum TipoCombustible
	{
		NAFTA, DIESEL;
	}
	public static enum Estado{
		ALQUILADO, LIBRE, AVERIADO;
	}
	public static enum Seguro{
		LA_SEGUNDA, MAPFRE, LA_PATRONAL, LA_CAJA, ZURICH;		
	}
	
	// {{ Identification on the UI
	public String title() {
	final TitleBuffer buf = new TitleBuffer();
	
	buf.append(getPatente());	       
	       
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
	  
	// {{ Patente
	
	private String patente;
	@DescribedAs("El dominio del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="1")
	public String getPatente()
	{
		return patente;
	}
	
	public void setPatente(String patente)
	{
		this.patente=patente;
	}
	
	// {{ Marca
	private Marca marca;
	//private List<Marcas> marcas=new ArrayList<Marcas>();
	@DescribedAs("La marca del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="2")
	
	public Marca getMarca(){
		return marca;
	}
	
	public void setMarca(Marca marca){
		this.marca=marca;
	}
	
	
	
    private String modelo;
    @DescribedAs("El modelo del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
    // words, spaces and selected punctuation
    @MemberOrder(sequence = "3")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(final String modelo)
    {
        this.modelo = modelo;
    }
    private int ano;
    @DescribedAs("El año del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")

    @MemberOrder(sequence = "4")
    public int getAno() {
        return ano;
    }

    public void setAno(final int ano) {
        this.ano = ano;
    }


    private String color;
    @DescribedAs("El color del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
    
    @MemberOrder(sequence = "5")
    public String getColor() 
    {
        return color;
    }

    public void setColor(final String color) 
    {
        this.color = color;
    }
    private int kms;
    @DescribedAs("El kilometraje del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")

    @MemberOrder(sequence = "6")
    public int getKilometraje() 
    {
        return kms;
    }

    public void setKilometraje(final int kms) 
    {
        this.kms = kms;
    }
    private int baul;
    @DescribedAs("La capacidad del baul del vehiculo.")
    @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")

    @MemberOrder(sequence = "7")
    public int getCapacidadBaul() 
    {
        return baul;
    }

    public void setCapacidadBaul(final int baul) 
    {
        this.baul = baul;
    }
 	private TipoCombustible combustible;
 	@DescribedAs("El tipo de combustible del vehiculo.")
 	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
 	@MemberOrder(sequence="8")
 	public TipoCombustible getTipoCombustible()
 	{
 		return combustible;
 	}
 	
 	public void setTipoCombustible(TipoCombustible combustible)
 	{
 		this.combustible=combustible;
 	}	
 	

  	private Estado estado;
  	@DescribedAs("Señala el estado actual del vehiculo.")
  	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
  	@MemberOrder(sequence="9")
  	public Estado getEstado()
  	{
  		return estado;
  	}
  	
  	public void setEstado(Estado estado)
  	{
  		this.estado=estado;
  	}	
  	

    private Date fechaCompra;
    @DescribedAs("Señala la fecha de compra del vehiculo.")
    @MemberOrder(sequence="10")
    public Date getFechaCompra() 
    {
        return fechaCompra;
    }

    public void setFechaCompra(final Date fechaCompra)
    {
        this.fechaCompra= fechaCompra;
    }

    private Autos categoria;
    @MemberOrder(sequence="15")
    public Autos getCategoria(Categoria toDoItem) 
    {
    	toDoItem.getDependencies().add(toDoItem);
        return this;
    }

    public void setCategoria(final Autos categoria)
    {
        this.categoria=categoria;
    }

    
    
    
    public void clearFechaCompra()
    {
        setFechaCompra(null);
    }
 
 // {{ Seguro del vehiculo
   	private Seguro seguro;
   	@DescribedAs("Señala el seguro del vehiculo.")
   	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
   	@MemberOrder(sequence="11")
   	public Seguro getSeguro()
   	{
   		return seguro;
   	}
   	
   	public void setSeguro(Seguro seguro)
   	{
   		this.seguro=seguro;
   	}	
       
  
    
    // {{ injected: DomainObjectContainer
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    public void setDomainObjectContainer(final DomainObjectContainer container)
    {
        this.container = container;
    }
    // }}
    
 
    public Autos Add(Categoria toDoItem) 
    {
       toDoItem.getDependencies().add(toDoItem);
        return this;
    }
	
}

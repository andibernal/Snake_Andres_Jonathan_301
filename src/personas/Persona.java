package personas;

public class Persona {
	
    private int id = 0 , documento;
    private String   
                    nombre, 
                    telefono, 
                    email, 
                    lugar_nacimiento,
                    foto;

    public Persona(int id, int documento, String nombre,  String email) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        //this.telefono = telefono;
        this.email = email;
       // this.foto = foto;
     //   this.lugar_nacimiento = lugar_nacimiento;
    }

    public static Persona crear(int id, int documento, String nombre,  String email) {
        return new Persona(id, documento, nombre,  email );
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setLugar_nacimiento(String lugar_nacimiento) {
        this.lugar_nacimiento = lugar_nacimiento;
    }

    public int getDocumento(){
        return this.documento;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFoto() {
        return this.foto;
    }

    public int getId() {
        return this.id;
    }

    public String getLugar_nacimiento() {
        return this.lugar_nacimiento;
    }

    
    
}

package Integrador1.DTO;

public class ProductoDTO {
    private int idProducto;
    private String nombre;
    private float valor;
    private int cantidad;
    private int recaudacionTotal;

    public ProductoDTO(int idProducto, String nombre, float valor, int cantidad, int recaudacionTotal) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
        this.cantidad = cantidad;
        this.recaudacionTotal = recaudacionTotal;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getRecaudacionTotal() {
        return recaudacionTotal;
    }

    public void setRecaudacionTotal(int recaudacionTotal) {
        this.recaudacionTotal = recaudacionTotal;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", cantidad=" + cantidad +
                ", recaudacionTotal=" + recaudacionTotal +
                '}';
    }
}

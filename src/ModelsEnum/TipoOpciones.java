package ModelsEnum;

public enum TipoOpciones {
    INICIARSESION("Iniciar sesion"), REGISTRARSE("Registrarse"), CATALOGO("Catalogo"),
    VOLVER("Volver"), CARRITO("Carrito"), MISDATOS("Mis Datos"), MODIFICARPRODUCTO("Modificar Producto"),
    COMPRAR("Comprar"), CATALGOCOMPLETO("Catalogo Completo"), TV("Smart TVs"), CELULARES("Celulares"),
    COMPUTADORAS("Computadoras"), REMOVERDELCARRITO("Remover del Carrito"), LIMPIARCARRITO("Limpiar Carrito"),
    VERPRODUCTO("Ver producto"), VERCARRITO("Ver Carrito"), DEJARCOMENTARIO("Dejar Comentario"),

    AGREGARALCARRITO("Agregar al carrito"), MEDIOPAGO("Medio de Pago"), AGREGARPRODUCTO("Agrega nuevo producto"),
    VERFACTURAS("Ver Facturas"),MODIFICARPRECIO("Modificar Precio"),CAMBIARSTOCK("Aumentar-Disminuit Stock"),
    GESTIONARINVENTARIO("Gestionar Inventario");

    private String nombre;

    private TipoOpciones (String nombre){
        this.nombre= nombre;
    }

    public String getNombre() {
        return nombre;
    }

}

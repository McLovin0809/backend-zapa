package com.ecomerce.zapa.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecomerce.zapa.model.*;
import com.ecomerce.zapa.repository.*;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ColoresRepository coloresRepository;

    @Autowired
    private EcofriendlyRepository ecofriendlyRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired
    private ImagenesRepository imagenesRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ComunaRepository comunaRepository;
    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TallaRepository tallaRepository;
    @Autowired
    private TallasRepository tallasRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public void run(String... args) throws Exception {

        System.setProperty("hibernate.jdbc.batch_size", "0");
        System.setProperty("hibernate.order_inserts", "false");
        System.setProperty("hibernate.order_updates", "false");

        Thread.sleep(3000);

        if (usuarioRepository.count() > 0) {
            System.out.println("BD ya inicializada. Omitiendo DataLoader.");
            return;
        }

        Faker faker = new Faker();
        Random random = new Random();

        Region region = new Region();
        region.setNombre("Región Metropolitana");
        region = regionRepository.save(region);

        Comuna comuna = new Comuna();
        comuna.setNombre("Santiago Centro");
        comuna.setRegion(region);
        comuna = comunaRepository.save(comuna);

        Direccion direccion = new Direccion();
        direccion.setCalle("Av. Alameda");
        direccion.setNumero("1234");
        direccion.setComuna(comuna);
        direccion = direccionRepository.save(direccion);

        Rol admin = new Rol();
        admin.setNombre("ADMIN");
        admin = rolRepository.save(admin);

        Rol cliente = new Rol();
        cliente.setNombre("CLIENTE");
        cliente = rolRepository.save(cliente);

        List<Usuario> usuarios = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Usuario u = new Usuario();
            u.setNombre(faker.name().firstName());
            u.setEmail("user" + i + "@correo.cl");
            u.setClave("123456");
            u.setTelefono("98765432");
            u.setRol(cliente);
            u.setDireccion(direccion);
            usuarios.add(usuarioRepository.save(u));
        }

        usuarioRepository
                .save(new Usuario(null, "Administrador", "admin@zapa.cl", "admin123", "99999999", admin, direccion));

        Categoria cat1 = categoriaRepository.save(new Categoria(null, "Zapatillas"));
        Categoria cat2 = categoriaRepository.save(new Categoria(null, "Botines"));

        Marca marca1 = marcaRepository.save(new Marca(null, "Nike"));
        Marca marca2 = marcaRepository.save(new Marca(null, "Adidas"));

        Genero g1 = generoRepository.save(new Genero(null, "Hombre"));
        Genero g2 = generoRepository.save(new Genero(null, "Mujer"));

        Material mat1 = materialRepository.save(new Material(null, "Cuero"));
        Material mat2 = materialRepository.save(new Material(null, "Sintético"));

        Ecofriendly eco = ecofriendlyRepository.save(new Ecofriendly(null, true));

        Color colNegro = colorRepository.save(new Color(null, "Negro"));
        Color colBlanco = colorRepository.save(new Color(null, "Blanco"));

        Talla t38 = tallaRepository.save(new Talla(null, "38", null));
        Talla t40 = tallaRepository.save(new Talla(null, "40", null));

        List<Producto> productos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Producto p = new Producto();
            p.setNombre("Zapatilla Modelo " + (i + 1));
            p.setDescripcion("Zapatilla deportiva modelo " + (i + 1));
            p.setPrecio(faker.number().randomDouble(2, 25000, 80000));
            p.setStock(faker.number().numberBetween(5, 50));
            p.setDescuento(faker.number().randomDouble(2, 0, 40));
            p.setImgPrincipal("https://picsum.photos/400?random=" + i);

            p.setMarca(random.nextBoolean() ? marca1 : marca2);
            p.setGenero(random.nextBoolean() ? g1 : g2);
            p.setMaterial(random.nextBoolean() ? mat1 : mat2);
            p.setEcofriendly(eco);

            productos.add(productoRepository.save(p));
        }

        for (Producto p : productos) {
            categoriasRepository.save(new Categorias(null, p, random.nextBoolean() ? cat1 : cat2));
        }

        List<Imagen> imagenes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            imagenes.add(imagenRepository.save(new Imagen(null, "Imagen " + i, "https://picsum.photos/500?img=" + i)));
        }

        for (Producto p : productos) {
            imagenesRepository.save(new Imagenes(null, p, imagenes.get(random.nextInt(imagenes.size()))));
        }

        for (Producto p : productos) {
            coloresRepository.save(new Colores(null, p, random.nextBoolean() ? colNegro : colBlanco));
        }
        for (Producto p : productos) {
            tallasRepository.save(new Tallas(null, p, random.nextBoolean() ? t38 : t40, random.nextInt(10) + 1));
        }

        Estado pendiente = estadoRepository.save(new Estado(null, "PENDIENTE"));
        Estado pagado = estadoRepository.save(new Estado(null, "PAGADO"));

        MetodoPago debito = metodoPagoRepository.save(new MetodoPago(null, "Tarjeta Débito"));
        MetodoPago credito = metodoPagoRepository.save(new MetodoPago(null, "Tarjeta Crédito"));

        for (int i = 0; i < 5; i++) {
            Venta v = new Venta();
            v.setFechaVenta(LocalDateTime.now().minusDays(random.nextInt(10)));
            v.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            v.setEstado(random.nextBoolean() ? pendiente : pagado);
            v.setMetodoPago(random.nextBoolean() ? debito : credito);

            v = ventaRepository.save(v);

            int cantidad = random.nextInt(3) + 1;

            for (int j = 0; j < cantidad; j++) {
                Producto seleccionado = productos.get(random.nextInt(productos.size()));

                ProductosVenta pv = new ProductosVenta();
                pv.setVenta(v);
                pv.setProducto(seleccionado);
                pv.setCantidad(random.nextInt(3) + 1);
                pv.setSubtotal(seleccionado.getPrecio() * pv.getCantidad());

                productosVentaRepository.save(pv);
            }
        }

        System.out.println("✔✔ DataLoader ZAPA ejecutado sin errores ✔✔");
    }
}

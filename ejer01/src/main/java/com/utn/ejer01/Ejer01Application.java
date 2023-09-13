package com.utn.ejer01;

import com.utn.ejer01.entidades.*;
import com.utn.ejer01.repositorios.ClienteRepository;
import com.utn.ejer01.repositorios.PedidoRepository;
import com.utn.ejer01.repositorios.RubroRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class Ejer01Application {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	RubroRepository rubroRepository;
	public static void main(String[] args) {
		SpringApplication.run(Ejer01Application.class, args);

	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository) {
		return args -> {
			System.out.println("ESTOY FUNCIONANDO");
		//carga de datos
			Cliente cliente1 = Cliente.builder()
					.nombre("Osvaldo")
					.apellido("Beto")
					.telefono("2613615007")
					.email("OsvaldoBeto@gmail.com")
					.build();
			Cliente cliente2=Cliente.builder()
					.nombre("Juan")
					.apellido("Perez")
					.telefono("3613615007")
					.email("JuanPerez@gmail.com")
					.build();

			Domicilio domicilio1 = Domicilio.builder()
					.calle("Bamdera de los andes")
					.numero("2337")
					.localidad("Rodeo")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Carril godoy cruz")
					.numero("1211")
					.localidad("Buena nueva")
					.build();
			Domicilio domicilio3= Domicilio.builder()
					.calle("Sobremonte")
					.numero("418")
					.localidad("Ciudad")
					.build();
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			cliente2.agregarDomicilio(domicilio3);


			Pedido pedido1= Pedido.builder()
					.fecha(LocalDate.now())
					.estado("En preparacion")
					.tipoEnvio("A domicilio")
					.total(2000)
					.build();
			Pedido pedido2= Pedido.builder()
					.fecha(LocalDate.now())
					.estado("Recibido")
					.tipoEnvio("Retiro en local")
					.total(1000)
					.build();
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);


			DetallePedido detalle1= DetallePedido.builder()
					.cantidad(1000)
					.subtotal(200)
					.build();
			DetallePedido detalle2= DetallePedido.builder()
					.cantidad(50000)
					.subtotal(10000)
					.build();
			pedido1.agregarDetalle(detalle1);
			pedido1.agregarDetalle(detalle2);
			Factura factura1=Factura.builder()
					.numero(1213)
					.fecha(LocalDate.now())
					.descuento(10)
					.total(100)
					.build();
			pedido1.setFactura(factura1);
			Factura factura2=Factura.builder()
					.numero(2113)
					.fecha(LocalDate.now())
					.descuento(20)
					.total(200)
					.build();
			pedido2.setFactura(factura2);



			Rubro rubro1=Rubro.builder()
					.denominacion("RUBRO 1")
					.build();
			Producto producto1= Producto.builder()
					.tipo("Insumo")
					.tiempoEstimadoCocina(1)
					.denominacion("PAN")
					.precioCompra(100)
					.precioVenta(200)
					.stockActual(12)
					.stockMinimo(2)
					.unidadMedida("KG")
					.receta("Harina y huevos")
					.build();
			detalle1.setProducto(producto1);
			detalle2.setProducto(producto1);
			rubro1.agregarProductos(producto1);
			rubroRepository.save(rubro1);
			clienteRepository.save(cliente1);
			clienteRepository.save(cliente2);

		//consultas
			System.out.println("Cliente 1");
		Cliente clienterecuperado=clienteRepository.findById(cliente1.getId()).orElse(null);
		if(clienterecuperado!=null){
			System.out.println(("Nombre: "+clienterecuperado.getNombre()));
			System.out.println(("Apellido: "+clienterecuperado.getApellido()));
			System.out.println(("Telefono: "+clienterecuperado.getTelefono()));
			System.out.println(("Email: "+clienterecuperado.getEmail()));
			clienterecuperado.mostrarDomicilios();
		}
			System.out.println("Cliente 2 ");
			Cliente clienterecuperado1=clienteRepository.findById(cliente2.getId()).orElse(null);
			if(clienterecuperado!=null){
				System.out.println(("Nombre: "+clienterecuperado1.getNombre()));
				System.out.println(("Apellido: "+clienterecuperado1.getApellido()));
				System.out.println(("Telefono: "+clienterecuperado1.getTelefono()));
				System.out.println(("Email: "+clienterecuperado1.getEmail()));
				clienterecuperado1.mostrarDomicilios();
			}


		};

	}



}
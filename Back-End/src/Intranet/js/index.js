import { CarService } from './service/carService.js';
import { ReservationService } from './service/reservationService.js';



// Anadir coche
document.getElementById('add-button-car').addEventListener('click', function(event) {
  event.preventDefault();
  
  const modelo = document.getElementById('model-add-car').value;
  const detalles = document.getElementById('details-add-car').value;
  const precio = document.getElementById('price-add-car').value;
  const url = document.getElementById('photo-add-car').value;
  const descripcion = document.getElementById('description-add-car').value;

  CarService.añadirCoche(modelo, detalles, precio, url, descripcion)
    .then(response => {
      console.log(response);
      alert('Coche añadido exitosamente'); 
      // Se resetean las cajas de input si ha tenido éxito
      document.getElementById('model-add-car').value = '';
      document.getElementById('details-add-car').value = '';
      document.getElementById('price-add-car').value = '';
      document.getElementById('photo-add-car').value = '';
      document.getElementById('description-add-car').value = '';
    })
    .catch(error => {
      console.error(error);
      alert('Ocurrió un error al añadir el coche');
    });
});

//añadir reserva

document.getElementById('add-button-reservation').addEventListener('click', function(event) {
  event.preventDefault();

  const userId = document.getElementById('iduser-add-reservation').value;
  const carId = document.getElementById('idvehicle-add-reservation').value;
  const startDate = document.getElementById('startdate-add-reservation').value;
  const endDate = document.getElementById('finishtdate-add-reservation').value;


  ReservationService.añadirReserva(userId, carId, startDate, endDate)
  .then(response => {
    console.log(response);
    alert('Reserva anadida exitosamente');
    //resetear los valores si todo va bien
    document.getElementById('iduser-add-reservation').value = '';
    document.getElementById('idvehicle-add-reservation').value = '';
    document.getElementById('startdate-add-reservation').value = '';
    document.getElementById('finishtdate-add-reservation').value = '';
  }).catch(error =>{
    console.error(error);
    alert('Ocurrio un error al anadir la reserva');
  });
});



// Modificar coche
document.getElementById('modify-button-car').addEventListener('click', function(event) {
  event.preventDefault();

  const id = document.getElementById('id-modify-car').value;
  // Si el input está vacío se le asigna valor null
  const modelo = document.getElementById('model-modify-car').value || null;
  const detalles = document.getElementById('details-modify-car').value || null;
  const precio = document.getElementById('price-modify-car').value || null;
  const disponibilidad = document.getElementById('availability-modify-car').value || null;
  const url = document.getElementById('photo-modify-car').value || null;
  const descripcion = document.getElementById('description-modify-car').value || null;

  CarService.modificarCoche(id, modelo, detalles, precio, disponibilidad, url, descripcion)
    .then(response => {
      console.log(response);
      alert('Coche actualizado exitosamente');
      // Se resetean las cajas de input si ha tenido éxito
      document.getElementById('id-modify-car').value = '';
      document.getElementById('model-modify-car').value = '';
      document.getElementById('details-modify-car').value = '';
      document.getElementById('price-modify-car').value = '';
      document.getElementById('availability-modify-car').value = '';
      document.getElementById('photo-modify-car').value = '';
      document.getElementById('description-modify-car').value = '';
    })
    .catch(error => {
      console.error(error);
      alert('Ocurrió un error al actualizar el coche');
    });
});

//modificar reserva

document.getElementById('modify-button-reservation').addEventListener('click', function(event) {
  event.preventDefault();

  const id = document.getElementById('idreservation-modify-reservation').value;
  const endDate = document.getElementById('finishtdate-modify-reservation').value;

  ReservationService.modificarReserva(id, endDate)
  .then(response => {
    console.log(response);
    alert('Reserva modificada correctamente');

    document.getElementById('idreservation-modify-reservation').value = '';
    document.getElementById('finishtdate-modify-reservation').value = '';
  })
  .catch(error => {
    console.error(error);
    alert('Ocurrio un error al modificar la reserva');
  });

});


// Borrar coche
document.getElementById('delete-button-car').addEventListener('click', function(event) {
  event.preventDefault();
  
  const id_coche = document.getElementById('id-delete-car').value;

  CarService.borrarCoche(id_coche)
    .then(response => {
      console.log(response);
      alert('Coche eliminado exitosamente');
      // Se resetea la caja de input si ha tenido éxito
      document.getElementById('id-delete-car').value = '';
    })
    .catch(error => {
      console.error(error);
      alert('Ocurrió un error al eliminar el coche');
    });
});

//borrar reserva
document.getElementById('delete-button-reservation').addEventListener('click', function(event) {
  event.preventDefault();
  
  const idReserva = document.getElementById('idreservation-delete-reservation').value;

  ReservationService.borrarReserva(idReserva)
    .then(response => {
      console.log(response);
      alert('Reserva eliminada exitosamente');
      // Se resetea la caja de input si ha tenido éxito
      document.getElementById('idreservation-delete-reservation').value = '';
    })
    .catch(error => {
      console.error(error);
      alert('Ocurrió un error al eliminar la reserva');
    });
});



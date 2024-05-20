import { CarService } from './service/carService.js';
// import { ReservationService } from './service/reservationService.js';


<<<<<<< HEAD

=======
>>>>>>> 077e9371d2acea3423a5922d92d194cb0dfb27f0
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

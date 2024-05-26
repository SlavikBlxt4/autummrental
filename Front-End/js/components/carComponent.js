// cocheComponent.js
import { ReservationService } from "../service/reservationService.js";
export class CarComponent {
    constructor(cocheData) {
        this.cocheData = cocheData;
    }

    render() {
        const cocheContainer = document.createElement('div');
        cocheContainer.classList.add('coche-container');

        const cocheImage = document.createElement('img');
        cocheImage.src = this.cocheData.imagen;
        cocheImage.alt = this.cocheData.modelo;

        const cocheName = document.createElement('h3');
        cocheName.textContent = this.cocheData.modelo;

        const cochePrice = document.createElement('h5');
        cochePrice.textContent = `${this.cocheData.precio.toFixed(2)}€`; // el .toFixed(2) es un método para formatear el precio como un string con 2 decimales. Además le añadimos el símbolo € al final

        const cocheDetails = document.createElement('p');
        cocheDetails.textContent = this.cocheData.detalles;

        const cocheId = document.createElement('p');
        cocheId.classList.add('id-coche');
        cocheId.textContent = this.cocheData.id_coche;


        const reserveButton = document.createElement('button');
        reserveButton.classList.add('reserve-button');
        reserveButton.onclick = function(event) {
            event.preventDefault();
            const cocheContainer = this.closest('.coche-container');

            const userId = localStorage.getItem('usuarioId');
            const carId = cocheContainer.querySelector('.id-coche').textContent;
            const startDate = document.querySelector('#fecha-inicio').value;
            const endDate = document.querySelector('#fecha-fin').value;


            ReservationService.añadirReserva(userId, carId, startDate, endDate)
            .then(response => {
                console.log(response);
                Swal.fire({
                    icon: 'success',
                    title: 'Reservation  made',
                    text: 'Reservation made successfully!.'
                  });
                
            }).catch(error =>{
                console.error(error);
                alert('Ocurrio un error al anadir la reserva');
            });
          };
        reserveButton.textContent = 'Reserve';

        cocheContainer.appendChild(cocheImage);
        cocheContainer.appendChild(cocheName);
        cocheContainer.appendChild(cochePrice);
        cocheContainer.appendChild(cocheDetails);
        cocheContainer.appendChild(cocheId);
        cocheContainer.appendChild(reserveButton);

        return cocheContainer;
    }
}

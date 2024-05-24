
export class reservationComponent {
    constructor(reservaData) {
        this.reservaData = reservaData;
    }

    render() {
        const reservaContainer = document.createElement('div');
        reservaContainer.classList.add('reserva-container'); 

        const reservaImage = document.createElement('img');
        reservaImage.classList.add('reserva-image');
        reservaImage.src = this.reservaData.imagen_coche;
        reservaImage.alt = this.reservaData.modelo_coche;

        const reservaName = document.createElement('h3');
        reservaName.textContent = this.reservaData.modelo_coche;

        const reservaPrice = document.createElement('h5');
        reservaPrice.textContent = `${this.reservaData.precio.toFixed(2)}€`; // el .toFixed(2) es un método para formatear el precio como un string con 2 decimales. Además le añadimos el símbolo € al final

        const reservaFechaInicio = document.createElement('p');
        reservaFechaInicio.textContent = this.reservaData.fecha_inicio;
        const reservaFechaFinal = document.createElement('p');
        reservaFechaFinal.textContent = this.reservaData.fecha_fin;

        reservaContainer.appendChild(reservaImage);
        reservaContainer.appendChild(reservaName);
        reservaContainer.appendChild(reservaPrice);
        reservaContainer.appendChild(reservaFechaInicio);
        reservaContainer.appendChild(reservaFechaFinal);

        return reservaContainer;
    }
}


export class reservationComponent {
    constructor(reservaData) {
        this.reservaData = reservaData;
    }

    render() {
        const reservaContainer = document.createElement('div');
        reservaContainer.classList.add('reserva-container'); 

        const reservaImage = document.createElement('img');
        reservaImage.src = this.reservaData.imagen;
        reservaImage.alt = this.reservaData.modelo;

        const reservaName = document.createElement('h3');
        reservaName.textContent = this.reservaData.modelo;

        const reservaPrice = document.createElement('h5');
        reservaPrice.textContent = `${this.reservaData.precio.toFixed(2)}€`; // el .toFixed(2) es un método para formatear el precio como un string con 2 decimales. Además le añadimos el símbolo € al final

        const reservaDetails = document.createElement('p');
        reservaDetails.textContent = this.reservaData.detalles;

        reservaContainer.appendChild(reservaImage);
        reservaContainer.appendChild(reservaName);
        reservaContainer.appendChild(reservaPrice);
        reservaContainer.appendChild(reservaDetails);

        return reservaContainer;
    }
}

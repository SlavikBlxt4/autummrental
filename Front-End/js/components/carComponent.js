// cocheComponent.js

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


        const reserveButton = document.createElement('button');
        reserveButton.textContent = 'Reservar';

        cocheContainer.appendChild(cocheImage);
        cocheContainer.appendChild(cocheName);
        cocheContainer.appendChild(cochePrice);
        cocheContainer.appendChild(cocheDetails);
        cocheContainer.appendChild(reserveButton);

        return cocheContainer;
    }
}

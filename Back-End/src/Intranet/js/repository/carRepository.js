export class CarRepository {
  
    // Función para anadir un coche a la API
    static async addCar(modelo, detalles, precio, url, descripcion) {

        const urlApi = `http://localhost:8080/AutumnRental/Controller?ACTION=COCHE.NEW_CAR&MODELO=${modelo}&DETALLES=${detalles}&PRECIO=${precio}&IMAGEN=${url}&DESCRIPCION=${descripcion}`;

        try {
            const response = await fetch(urlApi);
            return await response.json();
        } catch (error) {
            console.error(error);
        }
            
    }

    // Función para actualizar un coche a través de la API
    static async updateCar(id_coche, modelo, detalles, precio, disponibilidad, url, descripcion) {

        const urlApi = `http://localhost:8080/AutumnRental/Controller?ACTION=COCHE.UPDATE_CAR&ID_COCHE=${id_coche}&MODELO=${modelo}&DETALLES=${detalles}&PRECIO=${precio}&DISPONIBILIDAD=${disponibilidad}&IMAGEN=${url}&DESCRIPCION=${descripcion}`;

        try {
            const response = await fetch(urlApi);
            return await response.json();
        } catch (error) {
            console.error(error);
        }
    }


    // Función para borrar un coche a través de la API
    static async deleteCar(id_coche) {

        const urlApi = `http://localhost:8080/AutumnRental/Controller?ACTION=COCHE.DELETE_CAR&ID_COCHE=${id_coche}`;
        
        try {
            const response = await fetch(urlApi);
            return await response.json();
        } catch (error) {
            console.error(error);
        }
            
    }
    
}
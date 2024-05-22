export class CarRepository {
  

    // Función para obtener todos los coches de la API
    static async getCars() {
        const urlApi = `http://localhost:8080/AutumnRental/Controller?ACTION=COCHE.FINDALL`;
        try {
            const response = await fetch(urlApi);
            return await response.json();
        } catch (error) {
            console.error(error);
        }
    }

    static async getCarsBetweenDates(startDate, endDate){
        const urlApi = `http://localhost:8080/AutumnRental/Controller?ACTION=COCHE.FINDBYDATE&FECHA_INICIO=${startDate}&FECHA_FIN=${endDate}`;
        try {
            const response = await fetch(urlApi);
            return await response.json();
        } catch (error) {
            console.error(error);
        }
    }

    
}
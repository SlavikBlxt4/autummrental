export class ReservationRepository{

    static async addReservation(userId, carId, startDate, endDate){
        const urlApi = `http://localhost:8080/AutumnRental/Controller?ACTION=RESERVA.NEW_RESERVA&ID_USUARIO=${userId}&COCHE=${carId}&FECHA_INICIO=${startDate}&FECHA_FIN=${endDate}`;

        try{
            const response = await fetch(urlApi);
            return await response.json();
        }catch (error) {
            console.error(error);
        }
    }

    static async updateReservation(reservationId, endDate) {

        //const urlApi = `http://localhost:8080/AutumnRental/Controller?ACTION=COCHE.UPDATE_CAR&ID_COCHE=${id_coche}&MODELO=${modelo}&DETALLES=${detalles}&PRECIO=${precio}&DISPONIBILIDAD=${disponibilidad}&IMAGEN=${url}&DESCRIPCION=${descripcion}`;

        try {
            const response = await fetch(urlApi);
            return await response.json();
        } catch (error) {
            console.error(error);
        }
    }
}
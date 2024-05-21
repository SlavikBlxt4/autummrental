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

        const urlApi = `http://localhost:8080/AutumnRental/Controller?ACTION=RESERVA.UPDATE_RESERVA&ID_RESERVA=${reservationId}&FECHA_FIN=${endDate}`;

        try {
            const response = await fetch(urlApi);
            return await response.json();
        } catch (error) {
            console.error(error);
        }
    }
}
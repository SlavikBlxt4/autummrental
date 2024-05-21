import { ReservationRepository } from "../repository/reservationRepository.js"


export class ReservationService {
    static async añadirReserva(userId, carId, startDate, endDate) {
        console.log(`Añadiendo coche: ${userId} - ${carId} - ${startDate} - ${endDate}`);
        return await ReservationRepository.addReservation(userId, carId, startDate, endDate);
    }

    static async modificarReserva(reservationId, endDate) {
        console.log(`Modificando coche: ${reservationId} - ${endDate}`);
        return await ReservationRepository.updateReservation(reservationId, endDate);
    }
}
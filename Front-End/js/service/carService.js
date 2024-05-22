import { CarRepository } from '../repository/carRepository.js';

export class CarService {

    static async obtenerCoches() {
        return await CarRepository.getCars();
    }
    static async obtenerCochesPorFecha(fechaInicio, fechaFinal){
        return await CarRepository.getCarsBetweenDates(fechaInicio, fechaFinal);
    }
    
}
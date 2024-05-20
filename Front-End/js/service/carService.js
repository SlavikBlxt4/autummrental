import { CarRepository } from '../repository/carRepository.js';

export class CarService {

    static async obtenerCoches() {
        return await CarRepository.getCars();
    }
    
}
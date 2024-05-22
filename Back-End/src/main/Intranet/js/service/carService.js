import { CarRepository } from '../repository/carRepository.js';

export class CarService {
    static async añadirCoche(modelo, detalles, precio, url, descripcion) {
        console.log(`Añadiendo coche: ${modelo} - ${detalles} - ${precio} - ${url} - ${descripcion}`);
        return await CarRepository.addCar(modelo, detalles, precio, url, descripcion);
    }

    static async modificarCoche(id_coche, modelo, detalles, precio, disponibilidad, url, descripcion) {
        console.log(`Modificando coche: ${id_coche} - ${modelo} - ${detalles} - ${precio} - ${disponibilidad} - ${url} - ${descripcion}`);
        return await CarRepository.updateCar(id_coche, modelo, detalles, precio, disponibilidad, url, descripcion);
    }

    static async borrarCoche(id_coche) {
        console.log(`Borrando coche: ${id_coche}`);
        return await CarRepository.deleteCar(id_coche);
    }
    
}
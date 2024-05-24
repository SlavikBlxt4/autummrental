import { CarService } from './service/carService.js';
import { CarComponent } from './components/carComponent.js';
import { ReservationRepository } from './repository/reservationRepository.js';
import { ReservationService } from './service/reservationService.js';
import { reservationComponent } from './components/reservationComponent.js';

let products = [];
let cart = [];
let usuarioId;

/* ZONA DE AUTUMN RENTAL */

const appContainer = document.getElementById('car-render');
const reservaContainer = document.getElementById('reservation-render');

async function cargarCoches() {
    try {
        // Obtener coches del servicio
        const coches = await CarService.obtenerCoches();
        
        // Limpiar contenedor
        appContainer.innerHTML = '';

        // Renderizar coches
        coches.forEach(cocheData => {
            const cocheComponent = new CarComponent(cocheData);
            const cocheElement = cocheComponent.render();
            appContainer.appendChild(cocheElement);
        });
    } catch (error) {
        console.error('Error al cargar los coches:', error);
    }
}

async function cargarCochesEntreFechas(fechaInicio, fechaFin){
    try {
        // Obtener coches del servicio
        const coches = await CarService.obtenerCochesPorFecha(fechaInicio, fechaFin);

        // Limpiar contenedor
        appContainer.innerHTML = '';

        // Renderizar coches
        coches.forEach(cocheData => {
            const cocheComponent = new CarComponent(cocheData);
            const cocheElement = cocheComponent.render();
            appContainer.appendChild(cocheElement);
        });
    } catch (error) {
        console.error('Error al cargar los coches:', error);
    }
}


async function cargarReservas(idUsuario){
  
    try {
        // Obtener coches del servicio
        const reservas = await ReservationService.obtenerReservas(idUsuario);

        // Limpiar contenedor
        reservaContainer.innerHTML = '';

        // Renderizar coches
        reservas.forEach(reservaData => {
            const reservaComponent = new reservationComponent(reservaData);
            const reservaElement = reservaComponent.render();
            reservaContainer.appendChild(reservaElement);
        });
    } catch (error) {
        console.error('Error al cargar las reservas:', error);
    }
    
}

document.getElementById('show-reservations').addEventListener('click', () => {
  const idUsuario = localStorage.getItem('usuarioId');
  cargarReservas(idUsuario);
});


document.getElementById('load-available-cars').addEventListener('click', function(event){
  event.preventDefault();
  const fechaInicio = document.getElementById('fecha-inicio').value;
  const fechaFin = document.getElementById('fecha-fin').value;
  cargarCochesEntreFechas(fechaInicio, fechaFin);

})

document.addEventListener('DOMContentLoaded', (event) => {
  event.preventDefault();
  cargarCoches();

});

//funcion para recoger los datos del coche al cual realizaremos la reserva
/*document.getElementById('reserve-button').addEventListener('click', (event) => {
  event.preventDefault();

  const userId = localStorage.getItem('usuarioId');
  const carId = cocheContainer.querySelector('#id-coche').textContent;
  const startDate = document.querySelector('#fecha-inicio').value;
  const endDate = document.querySelector('#fecha-inicio').value;


  ReservationService.añadirReserva(userId, carId, startDate, endDate)
  .then(response => {
    console.log(response);
    alert('Reserva anadida exitosamente');
    
  }).catch(error =>{
    console.error(error);
    alert('Ocurrio un error al anadir la reserva');
  });
});*/



//scripts para la seleccion de fechas de reserva de coches
document.getElementById('load-available-cars').addEventListener('click', function(event) {
  event.preventDefault(); // Prevenir el envío del formulario para procesamiento en JS
  const fechaInicio = document.getElementById('fecha-inicio').value;
  const fechaFin = document.getElementById('fecha-fin').value;

  if (new Date(fechaInicio) > new Date(fechaFin)) {
      alert('La fecha de inicio no puede ser mayor que la fecha de fin.');
  } else {
      // Lógica para consultar coches disponibles
      console.log(`Consultar coches disponibles del ${fechaInicio} al ${fechaFin}`);
  }
});


document.getElementById('load-available-cars').addEventListener('click', function(){
  event.preventDefault(); // Prevenir el envío del formulario para procesamiento en JS
  const fechaInicio = document.getElementById('fecha-inicio').value;
  const fechaFin = document.getElementById('fecha-fin').value;
})



/* ZONA DE AVANCES DE XAVI */

// Guardar usuarioId en localStorage


// Obtener usuarioId desde localStorage al cargar la página









// Agregar un evento de clic al enlace para manejarlo con la función enlaceClicado
  
    document.getElementById('employee').addEventListener('click', function(){
      const usuarioId = localStorage.getItem('usuarioIdRol');
      if (usuarioId === '2') {
        window.location.href = "Intranet/index.html";
      }
    });
    document.getElementById('manager').addEventListener('click', function(){
      const usuarioId = localStorage.getItem('usuarioIdRol');
      if (usuarioId === '3') {
        window.location.href = "Intranet/index.html";
      }
    });

  




document.getElementById('login-form').addEventListener('submit',  (event) => {
  event.preventDefault(); // Evitar la recarga de la página

  const email = document.getElementById('login-email').value;
  const password = document.getElementById('login-password').value;


  fetch(`http://localhost:8080/AutumnRental/Controller?ACTION=USUARIO.LOGIN&EMAIL=${email}&PASSWORD=${password}`, {
    method: 'GET',
  })
  .then(response => {
    if (response.ok) {
      return response.json(); 
    }
    throw new Error('La solicitud no fue exitosa.');
  })
  .then(data => {
    console.log(data); // Aquí procesarías tu token
        localStorage.setItem('usuarioIdRol', data[0].id_rol);
        localStorage.setItem('usuarioId', data[0].id_usuario);
        Swal.fire({
          icon: 'success',
          title: '¡Sesión iniciada!',
          text: 'Sesión iniciada correctamente.'
        });
       /*usuarioId=data.usuarioId;
        console.log(usuarioId);
        localStorage.setItem('usuarioId', usuarioId);*/

        cerrar('.login');
  })
  .catch(error => {
    console.error('Hubo un problema con la solicitud Fetch:', error);
  });

});

//   try {
//       const response =  fetch(`http://localhost:8080/AutumnRental/Controller?ACTION=USUARIO.LOGIN&EMAIL=${email}&PASSWORD=${password}`, {
//           method: 'GET',
          
//       });

//       if (!response.ok) {
//           throw new Error('Error al iniciar sesión');
//       }

//       const data =  response.text();

//       if (data.error) {
//         Swal.fire({
//           icon: 'error',
//           title: 'Error al iniciar sesión',
//           text: data.error === 'User not found' ? 'Esta cuenta no está registrada.' : data.error
//         });

//     } else {
//         console.log(data); // Manejar la respuesta del servidor según lo necesario
//         localStorage.setItem('token', data.token);
//         Swal.fire({
//           icon: 'success',
//           title: '¡Sesión iniciada!',
//           text: 'Sesión iniciada correctamente.'
//         });
//         usuarioId=data.usuarioId;
//         console.log(usuarioId);
//         localStorage.setItem('usuarioId', usuarioId);

//         cerrar('.login');
//     }


//   } catch (error) {
//       console.error('Error al iniciar sesión:', error);
//       Swal.fire({
//         icon: 'error',
//         title: 'No se pudo iniciar sesión',
//         text: 'Esta cuenta no está registrada, verifique las credenciales.'
//       });
//   }
// });

// Manejo del formulario de registro
document.getElementById('register-form').addEventListener('submit', async (event) => {
  event.preventDefault(); // Evitar la recarga de la página

  const email = document.getElementById('register-email').value;
  const password = document.getElementById('register-password').value;

  try {
      const response = await fetch(`http://localhost:8080/AutumnRental/Controller?ACTION=USUARIO.REGISTER&EMAIL=${email}&PASSWORD=${password}`, {
          method: 'GET',
          headers: {
              'Content-Type': 'application/json'
          }
      });

      if(response.status === 200) {
        Swal.fire({
          icon: 'success',
          title: '¡Registro exitoso!',
          text: 'Usuario registrado correctamente.'
          
        });

        cerrar('.register');
        const loginResponse = await fetch(`http://localhost:8080/AutumnRental/Controller?ACTION=USUARIO.LOGIN&EMAIL=${email}&PASSWORD=${password}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if(loginResponse.status === 200) {
        const loginData = await loginResponse.json();
        // Guardar los datos de autenticación en tu aplicación (por ejemplo, en el localStorage)
        localStorage.setItem('usuarioIdRol', loginData[0].id_rol);
        localStorage.setItem('usuarioId', loginData[0].id_usuario);
      }
    }


     /* console.log(data); // Manejar la respuesta del servidor según lo necesario
          Swal.fire({
            icon: 'success',
            title: '¡Registro exitoso!',
            text: 'Usuario registrado correctamente.'
          });
          cerrar('.register');*/


  } catch (error) {
      console.error('Error al registrar usuario:', error);
      Swal.fire({
        icon: 'error',
        title: 'No se pudo registrar usuario',
        text: 'Ya existe una cuenta con este correo.'
      });
  }
});

function createProductCards(products) {
  products.forEach(product => {
    createProductCard(product);
  });
}

function updateDisplay(priceElement, disponibilidad) {
  if (disponibilidad === false) {
    priceElement.textContent = 'Sold out';
  } else {
    priceElement.textContent = '';
  }
}




// Función para renderizar productos
function renderProducts(products) {
  // Selecciona el contenedor donde se agregarán las tarjetas de productos
  const containerProducts = document.querySelector('.container-products');

  // Limpia el contenedor de productos existentes
  containerProducts.innerHTML = '';

  // Crea y agrega cada tarjeta de producto al contenedor
  products.forEach(product => {
      const productCard = createProductCard(product);
      containerProducts.appendChild(productCard);
  });
}




/*function showDescription() {
  document.getElementById('product').style.display = 'block';
  document.getElementById('description').style.display = 'block';
}

function hideDescription() {
  document.getElementById('product').style.display = 'none';
  document.getElementById('description').style.display = 'none';
}

document.getElementById('product').addEventListener('click', showDescription);
document.getElementById('description').addEventListener('click', hideDescription);*/





function fetchCategories(){
  fetch('http://localhost:3000/category')
  .then(response => response.json())
  .then(data => {
      console.log(data);
  });
}


//para que se quede verde el boton clickado

function removeActiveClassFromSpans() {
  document.querySelectorAll('.container-options span').forEach(span => {
    span.classList.remove('active');
  });
}

function setActiveClass(event) {
  // Remove 'active' class from all spans
  removeActiveClassFromSpans();
  
  // Add 'active' class to the clicked span
  event.currentTarget.classList.add('active');
}

document.querySelectorAll('.container-options span').forEach(span => {
  span.addEventListener('click', setActiveClass);
});

function renderCoffeesPerCategory(idCategory){
  fetch(`http://localhost:3000/coffee/${idCategory}`)
    .then(response => response.json())
    .then(data => {
        // Suponiendo que 'createProductCards' y/o 'renderProducts' son las funciones que ya tienes para renderizar los cafés
        createProductCards(data);
        renderProducts(data);
        
    })
          
    .catch(error => console.error('Error al obtener los cafés por categoría:', error));
}


document.getElementById('capsules').addEventListener('click', () => renderCoffeesPerCategory(1));
document.getElementById('boxes').addEventListener('click', () => renderCoffeesPerCategory(2));
document.getElementById('mix').addEventListener('click', () => renderCoffeesPerCategory(3));
document.getElementById('all').addEventListener('click', () => fetchCoffees());


/*function fetchFavouriteCoffees(idUser){
  fetch(`http://localhost:3000/favourites/${idUser}`)
  .then(response => response.json())
  .then(data => {
      console.log(data);
  });
}*/


async function addFavouriteCoffee(idUser, idCoffee){
  const response = await fetch('http://localhost:3000/favourites', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify({id_user: idUser, id_coffee: idCoffee})
  });
  const data = await response.json();
  console.log(data);
}


async function deleteFavouriteCoffee(idUser, idCoffee){
  const response = await fetch(`http://localhost:3000/favourites/${idUser}/${idCoffee}`, {
      method: 'DELETE',
  });
  const data = await response.json();
  console.log(data);
} 












/* ZONA DE AVANCES DE CRESPÁN */

//* selectors

const selectors = {
  /*products: document.querySelector(".products"), //* el único que habría que modificar*/
  cartBtn: document.querySelector(".cart-btn"),
  cartQty: document.querySelector(".cart-qty"),
  cartClose: document.querySelector(".cart-close"),
  cart: document.querySelector(".cart"),
  cartOverlay: document.querySelector(".cart-overlay"),
  cartClear: document.querySelector(".cart-clear"),
  cartBody: document.querySelector(".cart-body"),
  cartTotal: document.querySelector(".cart-total"),
};

//* event listeners

const setupListeners = () => {
  document.addEventListener("DOMContentLoaded", initStore);

  // product event
  /*selectors.products.addEventListener("click", addToCart);*/

  // cart events
  selectors.cartBtn.addEventListener("click", showCart);
  selectors.cartOverlay.addEventListener("click", hideCart);
  selectors.cartClose.addEventListener("click", hideCart);
  selectors.cartBody.addEventListener("click", updateCart);
  selectors.cartClear.addEventListener("click", clearCart);
};

//* event handlers

const initStore = () => {
  /*loadCart();
  
  loadProducts("https://fakestoreapi.com/products")
    .then(renderProducts)
    .finally(renderCart);*/
};

const showCart = () => {
  selectors.cart.classList.add("show");
  selectors.cartOverlay.classList.add("show");
};

const hideCart = () => {
  selectors.cart.classList.remove("show");
  selectors.cartOverlay.classList.remove("show");
};

const clearCart = () => {
  cart = [];
  saveCart();
  renderCart();
  renderProducts();
  setTimeout(hideCart, 500);
};

/*
const addToCart = (e) => {
  if (e.target.hasAttribute("data-id")) {
    const id = parseInt(e.target.dataset.id);
    const inCart = cart.find((x) => x.id === id);

    if (inCart) {
      alert("Item is already in cart.");
      return;
    }

    cart.push({ id, qty: 1 });
    saveCart();
    renderProducts();
    renderCart();
    showCart();
  }
};

const removeFromCart = (id) => {
  cart = cart.filter((x) => x.id !== id);

  // if the last item is remove, close the cart
  cart.length === 0 && setTimeout(hideCart, 500);

  renderProducts();
};

const increaseQty = (id) => {
  const item = cart.find((x) => x.id === id);
  if (!item) return;

  item.qty++;
};

const decreaseQty = (id) => {
  const item = cart.find((x) => x.id === id);
  if (!item) return;

  item.qty--;

  if (item.qty === 0) removeFromCart(id);
};

const updateCart = (e) => {
  if (e.target.hasAttribute("data-btn")) {
    const cartItem = e.target.closest(".cart-item");
    const id = parseInt(cartItem.dataset.id);
    const btn = e.target.dataset.btn;

    btn === "incr" && increaseQty(id);
    btn === "decr" && decreaseQty(id);

    saveCart();
    renderCart();
  }
};

const saveCart = () => {
  localStorage.setItem("online-store", JSON.stringify(cart));
};

const loadCart = () => {
  cart = JSON.parse(localStorage.getItem("online-store")) || [];
};

//* render functions

const renderCart = () => {
  // show cart qty in navbar
  const cartQty = cart.reduce((sum, item) => {
    return sum + item.qty;
  }, 0);

  selectors.cartQty.textContent = cartQty;
  selectors.cartQty.classList.toggle("visible", cartQty);

  // show cart total
  selectors.cartTotal.textContent = calculateTotal().format();

  // show empty cart
  if (cart.length === 0) {
    selectors.cartBody.innerHTML =
      '<div class="cart-empty">Your cart is empty.</div>';
    return;
  }

  // show cart items
  selectors.cartBody.innerHTML = cart
    .map(({ id, qty }) => {
      // get product info of each cart item
      const product = products.find((x) => x.id === id);

      const { title, image, price } = product;

      const amount = price * qty;

      return `
        <div class="cart-item" data-id="${id}">
          <img src="${image}" alt="${title}" />
          <div class="cart-item-detail">
            <h3>${title}</h3>
            <h5>${price.format()}</h5>
            <div class="cart-item-amount">
              <i class="bi bi-dash-lg" data-btn="decr"></i>
              <span class="qty">${qty}</span>
              <i class="bi bi-plus-lg" data-btn="incr"></i>

              <span class="cart-item-price">
                ${amount.format()}
              </span>
            </div>
          </div>
        </div>`;
    })
    .join("");
};

const renderProducts = () => {
  selectors.products.innerHTML = products
    .map((product) => {
      const { id, title, image, price } = product;

      // check if product is already in cart
      const inCart = cart.find((x) => x.id === id);

      // make the add to cart button disabled if already in cart
      const disabled = inCart ? "disabled" : "";

      // change the text if already in cart
      const text = inCart ? "Added in Cart" : "Add to Cart";

      return `
    <div class="product">
      <img src="${image}" alt="${title}" />
      <h3>${title}</h3>
      <h5>${price.format()}</h5>
      <button ${disabled} data-id=${id}>${text}</button>
    </div>
    `;
    })
    .join("");
};

//* api functions

const loadProducts = async (apiURL) => {
  try {
    const response = await fetch(apiURL);
    if (!response.ok) {
      throw new Error(`http error! status=${response.status}`);
    }
    products = await response.json();
    console.log(products);
  } catch (error) {
    console.error("fetch error:", error);
  }
};

//* helper functions

const calculateTotal = () => {
  return cart
    .map(({ id, qty }) => {
      const { price } = products.find((x) => x.id === id);

      return qty * price;
    })
    .reduce((sum, number) => {
      return sum + number;
    }, 0);
};

Number.prototype.format = function () {
  return this.toLocaleString("en-US", {
    style: "currency",
    currency: "USD",
  });
};
*/

document.getElementById('cerrar-btn-login').addEventListener('click', function(){
  cerrar('.login');
})
document.getElementById('cerrar-btn-register').addEventListener('click', function(){
  cerrar('.register');
})





// Función para cerrar las cartas de login y registro
function cerrar(selector) {
  // Verificar si el argumento pasado es un selector válido
  if (typeof selector === 'string') {
    // Buscar el elemento correspondiente al selector
    const elemento = document.querySelector(selector);
    if (elemento) {
      elemento.style.zIndex = '-1';
    } else {
      console.error(`No se encontró ningún elemento con el selector ${selector}.`);
    }
  } else {
    console.error('El argumento pasado no es un selector válido.');
  }
}

// Función para abrir la carta de login

document.getElementById('user-btn').addEventListener('click', function(){
  abrir();
})
function abrir() {
  const loginElement = document.querySelector('.login');
  if (loginElement) {
    loginElement.style.zIndex = '1000';
  } else {
    console.error('No se encontró ningún elemento con la clase .login.');
  }
}

document.getElementById('registro-enlace').addEventListener('click', function(){
  abrirRegistro();
})

// Función para abrir la carta de registro
function abrirRegistro() {
  const registerElement = document.querySelector('.register');
  const loginElement = document.querySelector('.login');
  if (registerElement) {
    loginElement.style.zIndex = '-1';  // Cerrar la carta de login
    registerElement.style.zIndex = '1000';  // Abrir la carta de registro
  } else {
    console.error('No se encontró ningún elemento con la clase .register.');
  }
}


// Código para que funcione el slider de las reviews (viene de una biblioteca)
var swiper = new Swiper(".reviews-slider", {
  slidesPerView: 1,
  spaceBetween: 20,
  loop: true,
  grabCursor: true,
  centeredSlides: true,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  breakpoints: {
    0: {
      slidesPerView: 1,
    },
    768: {
      slidesPerView: 2,
    },
    991: {
      slidesPerView: 3,
    },
  },
});

//* initialize

setupListeners();




//funcion fetch reservas


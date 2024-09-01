<template>
    <div class="container main">
        <header>
            <section class="header-section">
                <img class="main-logo" src="./media/logo.png">
                <div class="button-container1" v-if="isLogged">
                    <router-link to="/publish">
                        <div class="btn btn-secondary">
                            <i class="fi fi-rr-home"></i>Publicar
                        </div>
                    </router-link>
                    <router-link to="/account">
                        <div class="btn btn-secondary">
                            <i class="fi fi-rr-user"></i>Mi cuenta
                        </div>
                    </router-link>
                    <div class="btn btn-secondary" @click="toggleIsLogged">
                        <i class="fi fi-rr-user"></i>Cerrar sesión
                    </div>
                </div>
                <div class="button-container1" v-if="!isLogged">
                    <router-link to="/login">
                        <div class="btn btn-secondary">
                            <i class="fi fi-rr-user"></i>Ingreso
                        </div>
                    </router-link>
                </div>
            </section>
        </header>

        <section>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="locationDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Ubicacion
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="locationDropdown">
                                    <li><a class="dropdown-item" href="#">RM</a></li>
                                    <li><a class="dropdown-item" href="#">Valparaiso</a></li>
                                    <li><a class="dropdown-item" href="#">Viña del mar</a></li>
                                    <li><a class="dropdown-item" href="#">Rancagua</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="propertyTypeDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Tipo de propiedad
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="propertyTypeDropdown">
                                    <li><a class="dropdown-item" href="#">Casa</a></li>
                                    <li><a class="dropdown-item" href="#">Terreno</a></li>
                                    <li><a class="dropdown-item" href="#">Departamento</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="associationDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Asociacion
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="associationDropdown">
                                    <li><a class="dropdown-item" href="#">Viel.cl</a></li>
                                    <li><a class="dropdown-item" href="#">Target</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <div class="priceFilter" data-role="rangeslider">
                                    <label for="price-min">Precio:</label>
                                    <input type="range" name="price-min" id="price-min" value="200" min="0" max="10000000000">
                                    <input type="number" id="price-display" value="200" min="0" max="10000000000">
                                </div>
                            </li>
                        </ul>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-primary" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </section>

        <section id="properties">
            <h1 class="main-title">Mejores 10 publicaciones</h1>
            <h class="main-title">Semana: {{ weekRange }}</h>
            <!-- Mostrar un mensaje si no hay inmuebles -->
            <div v-if="inmuebles.length === 0" class="alert alert-warning">No se encontraron inmuebles.</div>
            <div class="card-container">
                <div v-for="inmueble in inmuebles" :key="inmueble.id" class="card">
                    <img :src="inmueble.fotoUrl || './media/default_image.jpg'" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 class="letter">{{ inmueble.name }}</h3>
                        <p class="letter">{{ inmueble.description }}</p>
                        <p class="letter">Valor: {{ inmueble.precio }}</p>
                        <button class="btn btn-primary" @click="verMas(inmueble)">Ver más</button>
                    </div>
                </div>
            </div>
        </section>

        <div class="modal fade" id="propertyModal" tabindex="-1" aria-labelledby="propertyModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">{{ inmuebleSeleccionado.name }}</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <img :src="inmuebleSeleccionado.fotoUrl || './media/default_image.jpg'" alt="Imagen de la propiedad">
                        <h3>{{ inmuebleSeleccionado.name }}</h3>
                        <p>{{ inmuebleSeleccionado.description }}</p>
                        <p>Valor: {{ inmuebleSeleccionado.precio }}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary">Comprar</button>
                    </div>
                </div>
            </div>
        </div>

        <nav class="nav-bar1" aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item" :class="{ disabled: currentPage === 0 }">
                    <a class="page-link" href="#" aria-label="Previous" @click.prevent="cargarPagina(currentPage - 1)">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item" v-for="n in totalPages" :key="n">
                    <a class="page-link" href="#" @click.prevent="cargarPagina(n - 1)">{{ n + 1 }}</a>
                </li>
                <li class="page-item" :class="{ disabled: currentPage === totalPages - 1 }">
                    <a class="page-link" href="#" aria-label="Next" @click.prevent="cargarPagina(currentPage + 1)">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>

        <div id="app">
            <mainComponent/>
        </div>
    </div>
</template>

<script>
import mainComponent from '../components/mainComponent.vue';
import axios from 'axios';

// Importar imágenes directamente para asegurar las rutas
import casaStock from '../views/media/casa_stock.jpg';
import dptoStock from '../views/media/dpto_stock.jpg';
import terrenoStock from '../views/media/terreno_stock.jpg';

export default {
    components: {
        mainComponent
    },
    data() {
        return {
            isLogged: false,
            isDropdownVisible: false,
            locationFilter: false,
            weekRange: '',
            inmuebles: [
                { id: 1, name: "Inmueble de Ejemplo 1", description: "Descripción de ejemplo 1", fotoUrl: casaStock, precio: 100000 },
                { id: 2, name: "Inmueble de Ejemplo 2", description: "Descripción de ejemplo 2", fotoUrl: dptoStock, precio: 150000 },
                { id: 3, name: "Inmueble de Ejemplo 3", description: "Descripción de ejemplo 3", fotoUrl: terrenoStock, precio: 200000 }
            ],
            inmuebleSeleccionado: {},
            currentPage: 0,
            totalPages: 1,
            pageSize: 10,
            searchQuery: '' // Agregar esta línea para el término de búsqueda
        };
    },
    created() {
        this.calculateWeekRange();
        this.obtenerInmuebles(); // Llamar a la función para obtener inmuebles
    },
    mounted() {
        const sessionLog = JSON.parse(sessionStorage.getItem('isLogged') || 'false');
        this.isLogged = sessionLog;
    },
    methods: {
        toggleIsLogged() {
            this.isLogged = false;
            sessionStorage.setItem('isLogged', JSON.stringify(false));
        },
        calculateWeekRange() {
            const today = new Date();
            const dayOfWeek = today.getDay();
            const firstDay = new Date(today);
            firstDay.setDate(today.getDate() - dayOfWeek + 1);
            const lastDay = new Date(firstDay);
            lastDay.setDate(firstDay.getDate() + 6);

            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            const start = firstDay.toLocaleDateString('es-ES', options);
            const end = lastDay.toLocaleDateString('es-ES', options);

            this.weekRange = `${start} - ${end}`;
        },
        async obtenerInmuebles(pageNumber = 0, pageSize = this.pageSize) {
            try {
                const response = await axios.get(`/api/inmueble/page`, {
                    params: {
                        pageNumber: pageNumber,
                        pageSize: pageSize
                    }
                });
                console.log('Datos de inmuebles recibidos:', response.data); // Mostrar los datos recibidos en la consola
                // Asegurarse de que la lista de inmuebles incluya tanto los ejemplos como los obtenidos del backend
                this.inmuebles = this.inmuebles.concat(response.data.content || []);
                this.totalPages = response.data.totalPages || 1; // Valor por defecto en caso de error
            } catch (error) {
                console.error('Error al obtener los inmuebles:', error);
                // Mostrar un mensaje al usuario sobre el error (opcional)
                alert('Error al obtener los inmuebles, revisa la consola para más detalles.');
            }
        },
        async cargarPagina(pagina) {
            if (pagina >= 0 && pagina < this.totalPages) {
                this.currentPage = pagina;
                await this.obtenerInmuebles(this.currentPage, this.pageSize);
            }
        },
        verMas(inmueble) {
            this.inmuebleSeleccionado = inmueble;
            const modalElement = document.getElementById('propertyModal');
            if (modalElement) {
                const modal = new bootstrap.Modal(modalElement);
                modal.show();
            }
        }
    }
};
</script>

<style scoped>
.main {
    background: linear-gradient(45deg, #ded1b6, #ded1b6, #6ca19e, #6d997a);
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    padding: 20px;
}

.main-logo {
    margin-bottom: 10px;
    width: 250px;
    height: 80px;
}

.header-section {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.page-title {
    text-align: left;
    color: black;
    margin: 0;
}

.main-title {
    padding-top: 15px;
    color: black;
}

.letter {
    padding-top: 5px;
    color: rgb(0, 0, 0);
}

.card-container {
    display: flex;
    justify-content: space-between;
    margin: 20px;
    flex-wrap: wrap;
}

.card {
    background-color: #f8f6f691;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    margin: 20px;
    max-width: 450px;
    overflow: hidden;
    transition: transform 0.3s;
}

.card:hover {
    transform: scale(1.1);
}

.card img {
    border-radius: 8px 8px 0 0;
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.card-content {
    color: black;
    padding: 15px;
}

.card-content h3 {
    font-size: 1.5em;
    margin: 0.5em 0;
}

.card-content button {
    background-color: #6d997a;
    border: none;
    border-radius: 4px;
    color: white;
    cursor: pointer;
    padding: 10px;
    width: 100%;
}

.card-content button:hover {
    background-color: #42282c;
}

.navbar {
    padding-top: 5px;
    padding-bottom: 5px;
}

.navbar-brand h1 {
    font-size: 1.5rem;
    margin: 0;
}

.navbar-nav .nav-item {
    margin-bottom: 0;
}

.form-control {
    height: 38px;
    padding: 5px 10px;
    font-size: 1rem;
    border-radius: 5px;
}

.button-container1 {
    text-align: right;
    margin: 20px;
}

.footer1 {
    color: black;
    text-align: center;
    padding: 10px;
    border-top: 1px solid #ddd;
    width: 100%;
    position: relative;
    bottom: 0;
}

.priceFilter {
    padding-left: 3px;
    padding-top: 6px;
    color: rgba(0,0,0,.55);
    display: flex;
    align-items: center;
    gap: 10px;
}

.priceFilter label {
    margin: 0;
}

.priceFilter input[type="range"] {
    flex: 1;
}

.nav-bar1 {
    display: flex;
    justify-content: center;
    padding: 20px;
}

.pagination {
    display: flex;
    gap: 10px;
    padding: 0;
    margin: 0;
    list-style: none;
}

.page-link {
    display: block;
    padding: 10px 15px;
    border: 1px solid rgba(0, 0, 0, 0.125);
    border-radius: 4px;
    background-color: transparent;
    color: #42282c;
    text-decoration: none;
    transition: background-color 0.3s;
}

.page-link:hover {
    background-color: rgba(0, 0, 0, 0.1);
}

.modal-title {
    color: black;
}

.modal-body {
    color: black;
}

.btn-primary {
    background-color: #6d997a;
    border-color: #6d997a;
    color: white;
}

.btn-primary:hover,
.btn:focus,
.btn:active {
    background-color: #42282c;
}

.btn-secondary {
    background-color: #42282c;
    border-color: #42282c;
    color: white;
    display: inline-flex;
    align-items: center;
    padding: 10px 20px;
    margin-left: 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    text-decoration: none;
}

.btn-secondary:hover {
    background-color: #50373b;
}

.btn-secondary i {
    margin-right: 8px;
}

.menu-list {
    list-style: disc;
    padding-left: 20px;
}

.menu-list li {
    display: flex;
    align-items: center;
}

.alsoButton {
    margin-left: 8px;
}
</style>

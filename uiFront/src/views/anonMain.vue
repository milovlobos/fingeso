<template>
    
    <div class="container main">
        <header>
            <section class="header-section">
                <img class="main-logo" src="./media/logo.png" @click="handleClick()">
                <div class="button-container1" v-if="isLogged"> <!--Si el usuario se encuentra logeado se mostraran los botones de publicar, cuenta y cerrar sesion-->
                        <router-link to = "/publish">
                            <div type="button" class="btn btn-secondary">
                                <i class="fi fi-rr-home"></i>Publicar
                            </div>
                        </router-link>
                        <router-link to ="/account">
                            <div type="button" class="btn btn-secondary">
                                <i class="fi fi-rr-user"></i>Mi cuenta
                            </div>
                        </router-link>
                        <div type="button" class="btn btn-secondary" @click="toggleIsLoged">
                                <i class="fi fi-rr-user"></i>Cerrar sesion
                        </div>
                </div>
                <div class="button-container1" v-if="!isLogged"> <!--Sino se mostrara el boton de ingreso-->
                    <router-link to = "/login">
                        <div type="button" class="btn btn-secondary">
                            <i class="fi fi-rr-user"></i>Ingreso
                        </div>
                    </router-link>
                </div>
            </section>
        </header>

        <section> <!--Seccion dedicada a la barra de filtro y busqueda de propiedades-->
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Tipo de propiedad
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><button class="dropdown-item" @click="aplicateTypeFilter('Casa')" id="location1">Casa</button></li>
                                    <li><button class="dropdown-item" @click="aplicateTypeFilter('Terreno')" id="location2">Terreno</button></li>
                                    <li><button class="dropdown-item" @click="aplicateTypeFilter('Departamento')" id="location3">Departamento</button></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Asociacion
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><button class="dropdown-item" @click="aplicateAsosiationFilter(70)" id="location1">Viel.cl</button></li>
                                    <li><button class="dropdown-item" @click="aplicateAsosiationFilter(69)" id="location2">Target</button></li>
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

        <section id="properties"v-if="!selectedCategory"> <!--Seccion de propiedades destacadas-->
            <h1 class="main-title">Mejores 10 publicaciones</h1>
            <h class="main-title">Semana: {{ weekRange }}</h>
            <section>
            <div class="account-propieties">
                <div class="card-container-account">
                    <div class="card-account" v-for="(property, index) in top10" :key="index">
                        <img :src="property.propertyPhotoURL ? property.propertyPhotoURL : 'https://www.webempresa.com/foro/wp-content/uploads/wpforo/attachments/3200/318277=80538-Sin_imagen_disponible.jpg'" alt="Imagen de la propiedad">
                        <div class="card-content-account">
                            <h3 class="letter">{{ property.propertyName }}</h3>
                            <p class="letter">{{ property.propertyDescription }}</p>
                            <p class="letter">${{ property.propertyPrice }}</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" @click="openModal(property)">Ver más</button>
                        </div>
                    </div>
                </div>
            </div>
            </section>

            <div class="modal fade" id="propertyModal" tabindex="-1" aria-labelledby="propertyModalLabel" aria-hidden="true"><!--Componente de despliegue de los detalles de la propiedad-->
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="propertyModalLabel">Detalles de la Propiedad</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <img :src="propertySelected.propertyPhotoURL ? propertySelected.propertyPhotoURL : 'https://www.webempresa.com/foro/wp-content/uploads/wpforo/attachments/3200/318277=80538-Sin_imagen_disponible.jpg'" alt="Imagen de la propiedad">
                            <h3 id="modalTitle">{{ propertySelected.propertyName }}</h3>
                            <p id="modalDescription">{{ propertySelected.propertyDescription }}</p>
                            <p id="modalValue">Precio: ${{ propertySelected.propertyPrice }}</p>
                            <p id="modalValue">Metros cuadrados: {{ propertySelected.propertyMeter2 }}</p>
                            <p id="modalValue">Direccion: {{ propertySelected.propertyDress }}</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="button" class="btn btn-primary">Comprar</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="filtered properties" v-if="selectedCategory">
            <div class="grid-container">
                <div v-for="property in paginatedProperties" :key="property.id" class="card">
                    <img :src="property.propertyPhotoURL ? property.propertyPhotoURL : './media/terreno_stock.jpg'" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 >{{ property.propertyName }}</h3>
                        <p><strong>Descripción:</strong> {{ property.propertyDescription }}</p>
                        <p><strong>Precio:</strong> ${{ property.propertyPrice }}</p>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                    </div>
                </div>    
            </div>

        </section>

        <nav class="nav-bar1" aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item" :class="{ disabled: currentPage === 1 }">
                <a class="page-link" href="#" @click.prevent="changePage(currentPage - 1)" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li v-for="page in totalPages" :key="page" class="page-item" 
            :class="{ active: currentPage === page }"
            >
            <a class="page-link" href="#" @click.prevent="changePage(page)">{{ page }}</a>
            </li>
            <li class="page-item" :class="{ disabled: currentPage === totalPages }">
            <a class="page-link" href="#" @click.prevent="changePage(currentPage + 1)" aria-label="Next">
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

    import axios from 'axios';
    import mainComponent from '../components/mainComponent.vue';
    import { ref , onMounted } from 'vue';

    export default{
        beforeRouteEnter (to, from, next) {
            const hasReloaded = sessionStorage.getItem('hasReloaded');

            if (!hasReloaded) {
                sessionStorage.setItem('hasReloaded', 'true');
                next(vm => {
                    // Recargar la página solo la primera vez que se entra en la vista
                    window.location.reload();
                });
            } else {
                next();
            }
        },
        beforeRouteLeave(to, from, next) {
            // Limpiar el estado al salir de la vista para permitir futuros refrescos
            sessionStorage.removeItem('hasReloaded');
            next();
        },
        components: {
            mainComponent
        },
        setup(){

            const dateTop = new Date().toISOString().split('T')[0];
            
            const fetchData = async () =>{

                const param ={

                    "date" : dateTop,
                };
                try{

                    const respuesta = await axios.get(import.meta.env.VITE_BASE_URL + "api/promoted/getTop10",{params:param});
                    sessionStorage.setItem('top10',JSON.stringify(respuesta.data));
                    console.log(respuesta.data);

                }catch(error){

                    console.log("Error en axios: Top 10",error);

                }
            }

            onMounted(() => {
                fetchData();
            });

        },
        data(){

            return{ //Se inicializan las variables de la vista

                propertiesSelected: [],
                selectedCategory: false,
                isLogged:false,
                isDropdownVisible: false, //Variable para el despliegue del dropdown
                locationFilter: false, //Variable para el filtro de ubicacion
                weekRange: '', //Variable para el rango de la semana
                currentPage: 1,
                propertiesPerPage: 20,
                top10: [],
                propertySelected:[],
                
            }
        },
        created(){

            this.calculateWeekRange(); //Se calcula el rango de la semana

        },
        mounted(){

            const sessionLog = JSON.parse(sessionStorage.getItem('isLogged'));
            this.isLogged = sessionLog;

            const top = JSON.parse(sessionStorage.getItem('top10'));
            this.top10 = top;

        },
        computed: {
            totalPages() {
            return Math.ceil(this.propertiesSelected.length / this.propertiesPerPage);
            },
            paginatedProperties() {
            const start = (this.currentPage - 1) * this.propertiesPerPage;
            const end = start + this.propertiesPerPage;
            return this.propertiesSelected.slice(start, end);
            }
        },
        methods:{//Se mapean las acciones de la store para su uso en la vista

            toggleIsLoged(){
                
                this.isLogged = false;
                sessionStorage.setItem('isLogged',JSON.stringify(false));

            },
            toggleDropdown() {
                this.isDropdownVisible = !this.isDropdownVisible;
            },
            calculateWeekRange() {
                const today = new Date();
                const dayOfWeek = today.getDay(); // Día de la semana (0 - domingo, 6 - sábado)
                const firstDay = new Date(today);
                firstDay.setDate(today.getDate() - dayOfWeek + 1); // Lunes
                const lastDay = new Date(firstDay);
                lastDay.setDate(firstDay.getDate() + 6); // Domingo

                const options = { year: 'numeric', month: 'long', day: 'numeric' };
                const start = firstDay.toLocaleDateString('es-ES', options);
                const end = lastDay.toLocaleDateString('es-ES', options);

                this.weekRange = `${start} - ${end}`;
            },
            async aplicateTypeFilter(type) { 
                // Pedir lista de publicaciones
                this.propertiesSelected = []
                this.selectedCategory = true;
                sessionStorage.setItem('selectedCategory', JSON.stringify(true));

                try {
                    const answer = await axios.get(import.meta.env.VITE_BASE_URL + "api/property/search", {
                        params: { "type": type }
                    });
                    
                    // Almacenar las propiedades recibidas
                    this.propertiesSelected = answer.data;

                } catch (error) {
                    console.log("Error en axios: Búsqueda de propiedades", error);
                }
            },
            
            async aplicateAsosiationFilter(byId) { 
                // Pedir lista de publicaciones
                this.propertiesSelected = []
                this.selectedCategory = true;
                sessionStorage.setItem('selectedCategory', JSON.stringify(true));

                try {
                    const answer = await axios.get(import.meta.env.VITE_BASE_URL + "api/property/user/" + byId);
                    
                    // Almacenar las propiedades recibidas
                    this.propertiesSelected = answer.data;

                } catch (error) {
                    console.log("Error en axios: Búsqueda de propiedades", error);
                }
            },

            handleClick(){
                this.propertiesSelected = []
                this.selectedCategory = false;
                sessionStorage.setItem('selectedCategory', JSON.stringify(false));
            },
            
            changePage(page) {
                if (page > 0 && page <= this.totalPages) {
                    this.currentPage = page;
                }
            },
            openModal(property){

                this.propertySelected = property;

            },
        },

    }


</script>

<style scoped>

.main{

    background: linear-gradient(45deg, #ded1b6, #ded1b6, #6ca19e, #6d997a);
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    padding: 20px;
}

.main-logo{
    margin-bottom: 10px;
    width: 250px;
    height: 80px;
}
.header-section {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.main-title{

    padding-top: 15px;
    color: black;
}
.letter{

    padding-top: 5px;
    color: rgb(0, 0, 0);
}

.card-container{

    display: flex;
    justify-content: space-between;
    margin: 20px;
}

.card{

    background-color: #f8f6f691;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    margin: 20px;
    max-width: 450px;
    overflow: hidden;
    transition: transform 0.3s;

}

.card:hover{
    transform: scale(1.1);
}

.card img{

    border-radius: 8px 8px 0 0;
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.card-content{

    color: black;
    padding: 15px;
}

.card-content h3{

    font-size: 1.5em;
    margin: 0.5em 0;
}

.card-content button{

    background-color: #6d997a;
    border: none;
    border-radius: 4px;
    color: white;
    cursor: pointer;
    padding: 10px;
    width: 100%;
}

.card-content button:hover{

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
    height: 38px; /* Asegúrate de que la altura sea la misma para el input */
    padding: 5px 10px;
    font-size: 1rem;
    border-radius: 5px; /* Si quieres que los bordes sean redondeados, iguala este valor */
}

.button-container1 {
    text-align: right; /* Alinea los botones a la derecha */
    margin: 20px; /* Espaciado alrededor del contenedor */
}

.footer1{

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
    align-items: center; /* Alinea verticalmente los elementos en el centro */
    gap: 10px; /* Espacio entre el texto y el slider */
}

.priceFilter label {
    margin: 0; /* Elimina el margen por defecto del label si es necesario */
}

.priceFilter input[type="range"] {
    flex: 1; /* Hace que el slider ocupe el espacio restante */
}

.nav-bar1{

    display: flex;
    justify-content: center; /* Centra horizontalmente el contenedor */
    padding: 20px; /* Espacio alrededor del contenedor (opcional) */
}

.pagination {
    display: flex;
    gap: 10px; /* Espacio entre los elementos de paginación */
    padding: 0;
    margin: 0;
    list-style: none; /* Elimina los puntos de lista */
}

.page-link {
    display: block;
    padding: 10px 15px; /* Espaciado interno del enlace */
    border: 1px solid rgba(0, 0, 0, 0.125); /* Borde translúcido */
    border-radius: 4px; /* Bordes redondeados */
    background-color: transparent; /* Fondo translúcido */
    color: #42282c; /* Color del texto */
    text-decoration: none; /* Quita el subrayado */
    transition: background-color 0.3s; /* Transición para el cambio de color */
}

.page-link:hover {
    background-color: rgba(0, 0, 0, 0.1); /* Fondo al pasar el ratón sobre el enlace */
}

.modal-title{

    color: black;
}

.modal-body{

    color:black;
}

.btn-primary {
  background-color: #6d997a; /* Color de fondo del botón */
  border-color: #6d997a;
  color: white; /* Color del texto del botón */
}

/* Cambiar el color del botón al pasar el mouse */
.btn-primary:hover, .btn:focus, .btn:active {
  background-color: #42282c; /* Color de fondo al pasar el mouse */
}

.btn-secondary {
    background-color: #42282c; /* Color de fondo del botón */
    border-color: #42282c;
    color: white; /* Color del texto del botón */
    display: inline-flex; /* Usa Flexbox para alinear elementos en línea */
    align-items: center; /* Alinea verticalmente el contenido en el centro */
    padding: 10px 20px; /* Ajusta el tamaño y el espaciado del botón */
    margin-left: 10px; /* Espacio entre los botones */
    border: none; /* Elimina el borde del botón */
    border-radius: 4px; /* Opcional: redondea las esquinas del botón */
    cursor: pointer; /* Cambia el cursor a una mano al pasar sobre el botón */
    font-size: 16px; /* Tamaño del texto del botón */
    text-decoration: none; /* Elimina el subrayado en el texto */
}

/* Cambiar el color del botón al pasar el mouse */
.btn-secondary:hover {
  background-color: #50373b; /* Color de fondo al pasar el mouse */
}

.btn-secondary i {
    margin-right: 8px; /* Espacio entre el ícono y el texto */
}

.menu-list {
    list-style: disc; /* Muestra los puntos de lista predeterminados */
    padding-left: 20px; /* Ajusta el espacio a la izquierda para alinear con los puntos */
}

.menu-list li {
    display: flex;
    align-items: center; /* Alinea verticalmente el contenido */
}

.alsoButton {
    margin-left: 8px; /* Espacio entre el punto y el contenido */
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 16px;
}

.page-item.active .page-link {
  background-color: #007bff;
  border-color: #007bff;
  color: white;
}

.page-item.disabled .page-link {
  pointer-events: none;
  color: #6c757d;
}

.card-account:hover{
        transform: scale(1.05);
    }

    .card-account img{

        width: 100%;
        height: 200px;
        object-fit: cover;
    }

    .card-content-account h3{

        font-size: 1.5em;
        margin: 0.5em 0;
    }

    .card-content-account button{

        background-color: #6d997a;
        border: none;
        border-radius: 4px;
        color: white;
        cursor: pointer;
        padding: 10px;
        width: 100%;
    }

    .card-content-account button:hover{

        background-color: #42282c;
    }

    .card-container-account {
        margin-top: 30px;
        display: flex;
        justify-content: space-between; 
        flex-wrap: wrap; 
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
        gap: 20px;
    }

    .card-account {
        
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        transition: transform 0.3s;
        color: black;
        flex: 1 1 30%; 
        margin: 10px; 
        border: 1px solid #ddd;
        border-radius: 8px; 
        overflow: hidden;
    }

    .card-content-account {
        color: black;
        padding: 15px; 
    }

    .button-container-account {
        text-align: right; 
    }
</style>

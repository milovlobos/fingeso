<template>
    
    <div class="container main">
        <header>
        <!-- Componente que muestra las principales interacciones mediante botones interactivos -->
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

        <!--Seccion dedicada a la barra de filtro y busqueda de propiedades-->
        <section> 
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

        <!--Seccion de propiedades destacadas o top 10 diario-->
        <section id="properties"v-if="!selectedCategory"> 
            <h1 class="main-title">Mejores 10 publicaciones</h1>
            <h class="main-title">Semana: {{ weekRange }}</h>
            <section>
            <div class="account-propieties">
                <div class="card-container-account">
                    <div class="card" v-for="(property, index) in top10" :key="index">
                        <img :src="property.propertyPhotoURL ? property.propertyPhotoURL : 'https://www.webempresa.com/foro/wp-content/uploads/wpforo/attachments/3200/318277=80538-Sin_imagen_disponible.jpg'" alt="Imagen de la propiedad">
                        <div class="card-content">
                            <h3 class="letter">{{ property.propertyName }}</h3>
                            <p class="letter">Direccion: {{ property.propertyDress }}</p>
                            <p class="letter">Precio: ${{ property.propertyPrice }}</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" @click="openModal(property)">Ver más</button>
                        </div>
                    </div>
                </div>
            </div>
            </section>  

            <!--Componente de despliegue de los detalles de la propiedad-->
            <div class="modal fade" id="propertyModal" tabindex="-1" aria-labelledby="propertyModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="propertyModalLabel">Detalles de la Propiedad</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <img :src="propertySelected.propertyPhotoURL ? propertySelected.propertyPhotoURL : 'https://www.webempresa.com/foro/wp-content/uploads/wpforo/attachments/3200/318277=80538-Sin_imagen_disponible.jpg'" alt="Imagen de la propiedad">
                        <h2 class="modal-property" id="modalTitle">{{ propertySelected.propertyName }}</h2>
                        <h4 class="title-modal-description" id="modalDescription">Descripcion:</h4>
                        <p id="modalDescription">{{ propertySelected.propertyDescription }}</p>
                        <h4 class="title-modal-description" id="modalDescription">Caracteristicas:</h4>
                        <p id="modalValue">Precio: ${{ propertySelected.propertyPrice }}</p>
                        <p id="modalValue">Metros cuadrados: {{ propertySelected.propertyMeter2 }}</p>
                        <p id="modalValue">Direccion: {{ propertySelected.propertyDress }}</p>
                        <p id="modalValue">Disponible hasta: {{ propertySelected.propertyEnd_Date }}</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="button" class="btn btn-primary">Comprar</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--Componente de despliegue de propiedades segun determinado filtro-->
        <section id="filtered properties" v-if="selectedCategory">
            <div class="grid-container">
                <div v-for="property in paginatedProperties" :key="property.id" class="card">
                    <img :src="property.propertyPhotoURL ? property.propertyPhotoURL : './media/terreno_stock.jpg'" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 class="letter">{{ property.propertyName }}</h3>
                        <p class="letter">Direccion: {{ property.propertyDress }}</p>
                        <p class="letter">Precio: ${{ property.propertyPrice }}</p>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                    </div>
                </div>    
            </div>

        </section>

        <!--Componente de navegacion entre propiedades filtradas-->
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

        <!--Componente exportado para finalizar el contenido con un pie de pagina-->
        <div id="app">
            <mainComponent/>
        </div> 
    </div>
</template>

<script>

    //Importaciones para la logica de la vista
    import axios from 'axios';
    import mainComponent from '../components/mainComponent.vue';
    import { onMounted } from 'vue';

    export default{
        //Antes de entrar a la vista esta se recarga con tal de generar los estilos correctos
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
        // Limpiar el estado al salir de la vista para permitir futuros refrescos
        beforeRouteLeave(to, from, next) {
            sessionStorage.removeItem('hasReloaded');
            next();
        },
        //Componentes importados
        components: {
            mainComponent
        },
        //Realizar una vez cargada la vista para la visualizacion de los elementos mas recientes del top 10 
        setup(){ 

            const dateTop = new Date().toISOString().split('T')[0];
            
            const fetchData = async () =>{

                const param ={

                    "date" : dateTop,
                };
                try{

                    const respuesta = await axios.get(import.meta.env.VITE_BASE_URL + "api/promoted/getTop10",{params:param});
                    sessionStorage.setItem('top10',JSON.stringify(respuesta.data));

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
        mounted(){ //Guardado de variables globales en variables locales para su utilizacion

            const sessionLog = JSON.parse(sessionStorage.getItem('isLogged'));
            this.isLogged = sessionLog;

            const top = JSON.parse(sessionStorage.getItem('top10'));
            this.top10 = top;

        },
        computed: {
            totalPages() { //Se calcula el total de paginas para la paginacion de las propiedades
            return Math.ceil(this.propertiesSelected.length / this.propertiesPerPage);
            },
            paginatedProperties() {//Se calculan las propiedades a mostrar en la pagina actual
            const start = (this.currentPage - 1) * this.propertiesPerPage;
            const end = start + this.propertiesPerPage;
            return this.propertiesSelected.slice(start, end);
            }
        },
        methods:{//Se mapean las acciones de la store para su uso en la vista

            toggleIsLoged(){//Funcion para cerrar sesion
                
                this.isLogged = false;
                sessionStorage.setItem('isLogged',JSON.stringify(false));

            },
            toggleDropdown() {//Funcion para desplegar el dropdown
                this.isDropdownVisible = !this.isDropdownVisible;
            },
            calculateWeekRange() {//Funcion para calcular el rango de la semana
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
            async aplicateTypeFilter(type) { //Funcion para filtrar las propiedades segun el tipo de propiedad
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
            
            async aplicateAsosiationFilter(byId) { //Funcion para filtrar las propiedades segun la asociacion
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

            handleClick(){//Funcion para redirigir a la pagina principal
                this.propertiesSelected = []
                this.selectedCategory = false;
                sessionStorage.setItem('selectedCategory', JSON.stringify(false));
            },
            
            changePage(page) {//Funcion para cambiar de pagina en la paginacion
                if (page > 0 && page <= this.totalPages) {
                    this.currentPage = page;
                }
            },
            openModal(property){//Funcion para abrir el modal de detalles de la propiedad

                this.propertySelected = property;

            },
        },

    }


</script>

<style scoped>

    .main{ /*Estilos de la vista principal*/

        background: linear-gradient(45deg, #ded1b6, #ded1b6, #6ca19e, #6d997a);
        background-size: cover;
        background-position: center;
        min-height: 1080px;
        padding: 20px;
    }

    .main-logo{/*Estilos del logo principal*/
        margin-bottom: 10px;
        width: 250px;
        height: 80px;
    }

    .header-section {/*Estilos de la seccion de cabecera*/
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .main-title{/*Estilos del titulo principal*/

        padding-top: 15px;
        color: black;
    }

    .letter{/*Estilos de la letra del modal*/

        padding-top: 5px;
        color: rgb(0, 0, 0);
    }

    .card-container{/*Estilos del contenedor de las tarjetas*/

        display: flex;
        justify-content: space-between;
        margin: 20px;
    }

    .card{/*Estilos de las tarjetas*/

        background-color: #f8f6f691;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        margin: 20px;
        max-width: 450px;
        overflow: hidden;
        transition: transform 0.3s;

    }

    .card:hover{/*Estilos de las tarjetas al pasar el mouse*/
        transform: scale(1.1);
    }

    .card img{/*Estilos de las imagenes de las tarjetas*/

        border-radius: 8px 8px 0 0;
        width: 100%;
        height: 200px;
        object-fit: cover;
    }

    .card-content{/*Estilos del contenido de las tarjetas*/

        color: black;
        padding: 15px;
    }

    .card-content h3{/*Estilos del titulo de las tarjetas*/

        font-size: 1.5em;
        margin: 0.5em 0;
    }

    .card-content button{/*Estilos de los botones de las tarjetas*/

        background-color: #6d997a;
        border: none;
        border-radius: 4px;
        color: white;
        cursor: pointer;
        padding: 10px;
        width: 100%;
    }

    .card-content button:hover{/*Estilos de los botones de las tarjetas al pasar el mouse*/

        background-color: #42282c;
    }

    .navbar {/*Estilos de la barra de navegacion*/
        padding-top: 5px;
        padding-bottom: 5px;
    }

    .navbar-brand h1 {/*Estilos del titulo de la barra de navegacion*/
        font-size: 1.5rem;
        margin: 0;
    }

    .navbar-nav .nav-item {/*Estilos de los items de la barra de navegacion*/
        margin-bottom: 0;
    }

    .form-control {/*Estilos del formulario de busqueda*/
        height: 38px; 
        padding: 5px 10px;
        font-size: 1rem;
        border-radius: 5px; 
    }

    .button-container1 {/*Estilos del contenedor de botones*/
        text-align: right; 
        margin: 20px; 
    }

    .footer1{/*Estilos del pie de pagina*/

        color: black;
        text-align: center;
        padding: 10px;
        border-top: 1px solid #ddd;
        width: 100%;
        position: relative;
        bottom: 0;
    }

    .priceFilter {/*Estilos del filtro de precios*/
        padding-left: 3px;
        padding-top: 6px;
        color: rgba(0,0,0,.55);
        display: flex;
        align-items: center; 
        gap: 10px; 
    }

    .priceFilter label {/*Estilos de las etiquetas del filtro de precios*/
        margin: 0; 
    }

    .priceFilter input[type="range"] {/*Estilos del rango del filtro de precios*/
        flex: 1;
    }

    .nav-bar1{/*Estilos de la barra de navegacion de paginacion*/

        display: flex;
        justify-content: center; 
        padding: 20px; 
    }

    .pagination {/*Estilos de la paginacion*/
        display: flex;
        gap: 10px; 
        padding: 0;
        margin: 0;
        list-style: none; 
    }

    .page-link {/*Estilos de los enlaces de paginacion*/
        display: block;
        padding: 10px 15px; 
        border: 1px solid rgba(0, 0, 0, 0.125); 
        border-radius: 4px; 
        background-color: transparent; 
        color: #42282c;
        text-decoration: none; 
        transition: background-color 0.3s; 
    }

    .page-link:hover {/*Estilos de los enlaces de paginacion al pasar el mouse*/
        background-color: rgba(0, 0, 0, 0.1); 
    }

    .modal-title{/*Estilos del titulo del modal*/

        color: black;
    }

    .modal-body{/*Estilos del cuerpo del modal*/

        color:black;
    }

    .btn-primary {/*Estilos de los botones primarios*/
        background-color: #6d997a; 
        border-color: #6d997a;
        color: white; 
    }

    .btn-primary:hover, .btn:focus, .btn:active {/*Estilos de los botones primarios al pasar el mouse*/
        background-color: #42282c; 
    }

    .btn-secondary {/*Estilos de los botones secundarios*/
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

    .btn-secondary:hover {/*Estilos de los botones secundarios al pasar el mouse*/
        background-color: #50373b; 
    }

    .btn-secondary i {/*Estilos de los iconos de los botones secundarios*/
        margin-right: 8px;
    }

    .menu-list {/*Estilos de la lista de menu*/
        list-style: disc; 
        padding-left: 20px; 
    }

    .menu-list li {/*Estilos de los items de la lista de menu*/
        display: flex;
        align-items: center; 
    }

    .alsoButton {/*Estilos del boton tambien*/
        margin-left: 8px; 
    }

    .grid-container {/*Estilos del contenedor de la grilla*/
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        gap: 16px;
    }

    .page-item.active .page-link {/*Estilos de los enlaces activos de paginacion*/
        background-color: #007bff;
        border-color: #007bff;
        color: white;
    }

    .page-item.disabled .page-link {/*Estilos de los enlaces deshabilitados de paginacion*/
        pointer-events: none;
        color: #6c757d;
    }

    .card-account:hover{/*Estilos de las tarjetas al pasar el mouse*/
        transform: scale(1.05);
    }

    .card-account img{/*Estilos de las imagenes de las tarjetas*/

        width: 100%;
        height: 200px;
        object-fit: cover;
    }

    .card-content-account h3{/*Estilos del titulo de las tarjetas*/

        font-size: 1.5em;
        margin: 0.5em 0;
    }

    .card-content-account button{/*Estilos de los botones de las tarjetas*/

        background-color: #6d997a;
        border: none;
        border-radius: 4px;
        color: white;
        cursor: pointer;
        padding: 10px;
        width: 100%;
    }

    .card-content-account button:hover{/*Estilos de los botones de las tarjetas al pasar el mouse*/

        background-color: #42282c;
    }

    .card-container-account {/*Estilos del contenedor de las tarjetas*/
        margin-top: 30px;
        display: flex;
        justify-content: space-between; 
        flex-wrap: wrap; 
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
        gap: 20px;
    }

    .card-account {/*Estilos de las tarjetas*/
        
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

    .card-content-account {/*Estilos del contenido de las tarjetas*/
        color: black;
        padding: 15px; 
    }

    .button-container-account {/*Estilos del contenedor de botones*/
        text-align: right; 
    }
    .modal-body img{/*Estilos de las imagenes del modal*/

        border-radius: 5%;
    }
    .modal-property{/*Estilos del titulo del modal*/
        padding-top: 3px;
    }
    .title-modal-description{/*Estilos del titulo de la descripcion del modal*/

        margin-bottom: 8px;
        border-bottom: 1px solid #ddd;

    }
</style>

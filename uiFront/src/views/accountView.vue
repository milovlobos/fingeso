<template>

    <div class="container main">

        <header>
            <router-link to ="/">
                <img class="main-logo-account" src="./media/logo.png">
            </router-link>
            <div class="button-container-acount">
                <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#premiumModal" v-if="!userLogged?.premium"> Hazte Premium </button> <!--Boton que proporcionara direccion a la vista de opciones premium-->
            </div>

            <div class="modal fade" id="premiumModal" tabindex="-1" aria-labelledby="premiumModalLabel" aria-hidden="true"><!--Componente de despliegue de los detalles de la propiedad-->
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="premiumModalLabel">Hazte premium y adquiere todos sus beneficios</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h3 id="modalTitle"></h3>
                            <p id="modalDescription">
                                <ul>
                                    <li>Publicaciones ilimitadas</li>
                                    <li>Acceso a las estadisticas de tus publicaciones</li>
                                    <li>Acceso al top semanal</li>
                                    <li>Distintos metodos de pago</li>
                                </ul>
                            </p>
                            <p id="modalValue"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="button" class="btn btn-primary" @click="setPremium">Suscribirte</button>
                        </div>
                    </div>
                </div>
            </div>

        </header>

        <div class="profile-main"> <!--Seccion de perfil de usuario-->
            <img src="./media/profile.jpg" alt="Foto de perfil" class="profile-picture">
            <div class="profile-info">
                <h2>{{ userLogged?.name }}</h2>
                <p>{{ userLogged?.email }}</p>
            </div>
        </div>

        <section> <!--Seccion de propiedades del usuario-->
            <div class="account-propieties">
                <h1 class="main-title-account">Tus propiedades</h1>
                <div class="card-container-account">
                    <div class="card-account" v-for="(property,index) in userProperties" :key="index">
                        <img :src="getImageByType(property.type)" alt="Imagen de la propiedad">
                        <div class="card-content-account">
                            <h3 class="letter">{{ property.name }}</h3>
                            <p class="letter">{{ property.description }}</p>
                            <p class="letter">${{ property.precio }}</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" @click="openModal(property)" >Ver mas</button>
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
                        <img id="modalImage" :src="getImageByType(propertySelected.type)" alt="Imagen de la propiedad">
                        <h3 id="modalTitle">{{ propertySelected.name }}</h3>
                        <p id="modalDescription">{{ propertySelected.description }}</p>
                        <p id="modalValue">Precio: ${{ propertySelected.precio }}</p>
                        <p id="modalValue">Metros cuadrados: {{ propertySelected.metros2 }}</p>
                        <p id="modalValue">Direccion: {{ propertySelected.direccion }}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary">Promocionar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <section><!--Seccion de propiedades favoritas del usuario-->
            <div class="account-propieties">
                <h1 class="main-title-account">Tus favoritos</h1>
                <div class="card-container-account">
                    <div class="card-account">
                        <img src="./media/terreno_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content-account">
                            <h3 class="letter">Propiedad 1</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                    <div class="card-account">
                        <img src="./media/casa_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content-account">
                            <h3 class="letter">Propiedad 2</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                    <div class="card-account">
                        <img src="./media/casa_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content-account">
                            <h3 class="letter">Propiedad 3</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div id="app">
            <mainComponent/>
        </div> 

    </div>
</template>

<script>

    import mainComponent from '../components/mainComponent.vue'
    import axios from 'axios'

    export default {

        components: {
            mainComponent
        },
        data() {
            return {
                userLogged:null,
                userProperties:null,
                propertySelected:[],
            }
        },
        mounted(){

            const user = JSON.parse(sessionStorage.getItem('userLogged'));
            this.userLogged = user;

            const properties = JSON.parse(sessionStorage.getItem('userProperties'));
            this.userProperties = properties;

        },
        methods: {

            async setPremium(){

                const param ={

                    "Id":this.userLogged.id,
                    "metodoPago":'Automatico',

                }
                try{

                    const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + "api/usuario/premium",param);
                    if(respuesta.data == 1){

                        try{
                            const respuesta = await axios.get(import.meta.env.VITE_BASE_URL + "api/usuario/getusuario",{params:{"email":this.userLogged.email}});
                            sessionStorage.setItem('userLogged',JSON.stringify(respuesta.data));
                        } catch(error){

                            console.log("Error en axios: Busqueda del usuario");
                        }
                        alert("Tu membresia ha sido activada con exito!");
                    }
                    if(respuesta.data == 0) {

                        alert("Error en la asignacion de tu membresia")
                    }

                }catch(error){

                    console.log("Error en axios: Premium");
                }

            },
            openModal(property){

                this.propertySelected = property;

            },
            submitForm() {
                alert('Formulario enviado');
            },
            getImageByType(type){
                if(type == 'Departamento'){

                    return './media/dpto_stock.jpg';
                } else if(type == 'Casa'){

                    return './media/casa_stock.jpg';
                } else if(type == 'Terreno'){

                    return './media/terreno_stock.jpg';
                } else {

                    return './media/fail.jpg';
                }
            }
        }
    }

</script>

<style>

    .main{ 

        background: linear-gradient(45deg, #ded1b6, #ded1b6, #6ca19e, #6d997a);
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        padding: 20px;
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }
    
    header {
        display: flex;
        justify-content: space-between; 
        align-items: center; 
        padding: 10px; 
    }

    .main-title-account{

        margin-left: 785px;
        align-items: center;
        padding-top: 15px;
        color: black;
    }

    .profile-header {
        padding: 10px 20px;
        background-color: #f5f5f5;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .profile-main {
        display: flex;
        align-items: center;
        flex-direction: column; 
    }

    .profile-picture {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        object-fit: cover;
        border: 2px solid #ccc;
    }

    .profile-info {
        text-align: center;
    }

    .profile-info h2 {
        margin: 0;
        font-size: 20px;
        color: #333
    }

    .profile-info p {
        margin: 0;
        font-size: 16px;
        color: #777;
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

    .main-logo-account{
        margin-bottom: 10px;
        width: 250px;
        height: 80px;
    }

    .fade{

        color: black;
    }

    .navbar {
    padding-top: 5px;
    padding-bottom: 5px;
    width: 100%; /* Asegura que la barra ocupe todo el ancho del contenedor */
    max-width: 1200px; /* Ajusta según tu diseño para limitar el tamaño máximo */
    margin: 0 auto; /* Centra el navbar dentro de su contenedor */
    }

    .navbar .container-fluid {
        display: flex;
        flex-wrap: wrap; /* Permite que los elementos se ajusten y no se desborden */
        align-items: center;
        justify-content: space-between; /* Espacio entre elementos */
    }

    .nav-item {
        margin-right: 20px; /* Espaciado entre los elementos del menú */
    }

    .priceFilter {
        display: flex;
        align-items: center; /* Alinea verticalmente los elementos en el centro */
        gap: 10px; /* Espacio entre el texto y el slider */
    }

    .form-control {
        width: 150px; /* Ajusta el ancho del input de búsqueda */
        margin-right: 10px; /* Espacio entre el input y el botón */
    }

    .btn-primary {
        background-color: #6d997a; /* Color de fondo del botón */
        border-color: #6d997a;
        color: white; /* Color del texto del botón */
        padding: 5px 15px; /* Ajusta el tamaño y el espaciado del botón */
    }

    /* Cambiar el color del botón al pasar el mouse */
    .btn-primary:hover, .btn:focus, .btn:active {
        background-color: #42282c; /* Color de fondo al pasar el mouse */
    }


</style>
<template>

    <div class="container main">

        <header>
            <!--Componente cabecera de la vista con el logo de la web y los datos del usuario-->
            <router-link to ="/">
                <img class="main-logo-account" src="./media/logo.png">
            </router-link>
            <div class="profile-content">
                <div class="profile-main"> <!--Seccion de perfil de usuario-->
                    <img src="./media/profile.jpg" alt="Foto de perfil" class="profile-picture">
                    <div class="profile-info">
                        <h2>{{ userLogged?.username }}</h2>
                        <p>{{ userLogged?.userEmail }}</p>
                    </div>
                </div>

            <!--Boton de suscripcion premium-->
                <div class="button-container-acount">
                    <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#premiumModal" v-if="!userLogged?.userPremium"> Hazte Premium </button> <!--Boton que proporcionara direccion a la vista de opciones premium-->
                </div>

            </div>

            <!--Modal de suscripcion premium-->
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

        <!--Seccion de propiedades del usuario-->
        <section>
            <div class="account-propieties">
                <h1 class="main-title-account">Tus propiedades</h1>
                <div class="card-container-account">
                    <div class="card-account" v-for="(property, index) in userProperties" :key="index">
                        <img :src="property.propertyPhotoURL ? property.propertyPhotoURL : 'https://www.webempresa.com/foro/wp-content/uploads/wpforo/attachments/3200/318277=80538-Sin_imagen_disponible.jpg'" alt="Imagen de la propiedad">
                        <div class="card-content-account">
                            <h3 class="letter">{{ property.propertyName }}</h3>
                            <p class="letter">Direccion: {{ property.propertyDress }}</p>
                            <p class="letter">Precio: ${{ property.propertyPrice }}</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" @click="openModal(property)">Ver más</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--Modal de propiedad seleccionada que despliega los detalles de la propiedad-->
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
                        <button type="button" class="btn btn-primary" @click="toPromote()" :disabled="!userLogged?.userPremium">Promocionar</button>
                    </div>
                </div>
            </div>
        </div>
        <!--Componente exportado para finalizar el contenido con un pie de pagina-->
        <div id="app">
            <mainComponent/>
        </div> 

    </div>
</template>

<script>

    //Importaciones para la logica de la vista
    import mainComponent from '../components/mainComponent.vue'
    import axios from 'axios'

    function redirectPromo(){//Funcion para redirigir a la vista de promocion de propiedades

        window.location.href = '/top';
    }   

    export default {

        components: {//Componente principal de la vista
            mainComponent
        },
        data() {
            return {//Datos de la vista
                userLogged:null,
                userProperties:[],
                propertySelected:[],
            }
        },
        mounted(){//Metodo de carga de datos de la vista

            const user = JSON.parse(sessionStorage.getItem('userLogged'));
            console.log('Datos del usuario cargados:', user); // Depuración
            this.userLogged = user;

            const properties = JSON.parse(sessionStorage.getItem('userProperties'));
            console.log('Propiedades del usuario cargadas:', properties); // Depuración
            this.userProperties = properties;

        },
        methods: {//Metodos de la vista

            async setPremium(){//Metodo para activar la suscripcion premium del usuario

                const param ={

                    "Id":this.userLogged.id,
                    "metodoPago":'Automatico',

                }
                try{

                    const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + "api/user/premium",param);
                    if(respuesta.data == 1){

                        try{//Busqueda del usuario para actualizar la sesion
                            const respuesta = await axios.get(import.meta.env.VITE_BASE_URL + "api/user/getuser",{params:{"UserEmail":this.userLogged.userEmail}});
                            sessionStorage.setItem('userLogged',JSON.stringify(respuesta.data));
                            this.userLogged = respuesta.data;
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
            openModal(property){//Metodo para abrir el modal de la propiedad seleccionada

                this.propertySelected = property;

            },
            submitForm() {//Metodo para enviar el formulario de la vista
                alert('Formulario enviado');
            },
            getImageByType(type){//Metodo para obtener la imagen de la propiedad segun su tipo
                if(type == 'Departamento'){

                    return './media/dpto_stock.jpg';
                } else if(type == 'Casa'){

                    return './media/casa_stock.jpg';
                } else if(type == 'Terreno'){

                    return './media/terreno_stock.jpg';
                } else {

                    return './media/fail.jpg';
                }
            },
            async toPromote(){//Metodo para redirigir a la vista de promocion de propiedades

                sessionStorage.setItem('propertyToPromote',JSON.stringify(this.propertySelected));
                try{

                    const respuesta = await axios.get(import.meta.env.VITE_BASE_URL + "api/promoted/no-availability-dates");
                    sessionStorage.setItem('dateNoDispo',JSON.stringify(respuesta.data));

                }catch(error){

                    console.log("Error en axios: Busqueda de fechas");
                }
                redirectPromo();
            }
        }
    }

</script>

<style>

    .main{/*Estilos de la vista*/

        background: linear-gradient(45deg, #ded1b6, #ded1b6, #6ca19e, #6d997a);
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        padding: 20px;
        min-height: 1080px;
        margin: 0 auto;
        padding: 20px;
    }
    
    header {/*Estilos de la cabecera de la vista*/
        display: flex;
        justify-content: space-between; 
        align-items: center; 
        padding: 10px; 
    }

    .main-title-account{/*Estilos del titulo principal de la vista*/
        display: flex;      
        justify-content: center;  
        align-items: center;  
        padding-top: 15px;
        color: black;   
        border-bottom: 1px solid #ddd;
        padding-bottom: 8px;
        padding-left: 15px;
    }

    .profile-header {/*Estilos de la cabecera del perfil de usuario*/
        padding: 10px 20px;
        background-color: #f5f5f5;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .profile-main {/*Estilos de la seccion principal del perfil de usuario*/

        display: flex;
        align-items: rigth;
        margin-bottom: 20px;
        margin-top: 15px;
    }

    .profile-picture {/*Estilos de la imagen de perfil de usuario*/
        width: 60px;
        height: 60px;
        border-radius: 50%;
        object-fit: cover;
        border: 2px solid #ccc;
    }

    .profile-info {/*Estilos de la informacion del perfil de usuario*/
        display: flex;
        padding-left: 5px;
        flex-direction: column; 
        justify-content: center;
    }

    .profile-info h2 {/*Estilos del titulo de la informacion del perfil de usuario*/
        margin: 0;
        font-size: 30px;
        color: #333
    }

    .profile-info p {/*Estilos del parrafo de la informacion del perfil de usuario*/
        margin: 0;
        font-size: 19px;
        color: #777;
    }

    .card-account:hover{/*Estilos de la tarjeta de propiedades al pasar el cursor*/
        transform: scale(1.05);
    }

    .card-account img{/*Estilos de la imagen de la tarjeta de propiedades*/

        width: 100%;
        height: 200px;
        object-fit: cover;
    }

    .card-content-account h3{/*Estilos del titulo de la tarjeta de propiedades*/

        font-size: 1.5em;
        margin: 0.5em 0;
    }

    .card-content-account button{/*Estilos del boton de la tarjeta de propiedades*/

        background-color: #6d997a;
        border: none;
        border-radius: 4px;
        color: white;
        cursor: pointer;
        padding: 10px;
        width: 100%;
    }

    .card-content-account button:hover{/*Estilos del boton de la tarjeta de propiedades al pasar el cursor*/

        background-color: #42282c;
    }

    .card-container-account {/*Estilos del contenedor de tarjetas de propiedades*/
        margin-top: 30px;
        display: flex;
        justify-content: space-between; 
        flex-wrap: wrap; 
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
        gap: 20px;
    }

    .card-account {/*Estilos de la tarjeta de propiedades*/
        
        background-color: #f8f6f691;
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

    .card-content-account {/*Estilos del contenido de la tarjeta de propiedades*/
        color: black;
        padding: 15px; 
    }

    .button-container-account {/*Estilos del contenedor de botones de la vista*/
        text-align: right; 
        margin-top: 20px;
    }

    .main-logo-account{/*Estilos del logo principal de la vista*/
        margin-bottom: 10px;
        width: 250px;
        height: 80px;
    }

    .fade{/*Estilos de la animacion de desvanecimiento de la vista*/

        color: black;
    }
    .modal-body img{/*Estilos de la imagen del modal de la vista*/

        border-radius: 5%;
    }
    .modal-property{/*Estilos del titulo del modal de la vista*/
        padding-top: 3px;
    }
    .title-modal-description{/*Estilos del titulo de la descripcion del modal de la vista*/

        margin-bottom: 8px;
        border-bottom: 1px solid #ddd;
    
    }
</style>
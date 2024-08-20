<template>

    <div class="container main">

        <header>
            <router-link to ="/">
                <img class="main-logo-account" src="./media/logo.png">
            </router-link>
            <div class="button-container-acount">
                <button class="btn btn-secondary" v-if="!premium"> Hazte Premium </button>
            </div>
        </header>

        <div class="profile-main">
            <img src="./media/profile.jpg" alt="Foto de perfil" class="profile-picture">
            <div class="profile-info">
                <h2>Nombre del Usuario</h2>
                <p>correo@ejemplo.com</p>
            </div>
        </div>

        <section>
            <div class="account-propieties">
                <h1 class="main-title-account">Tus propiedades</h1>
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
        
        <section>
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

    export default {
        components: {
            mainComponent
        },
        data() {
            return {
                premium: false,
                id:'',
            }
        },
        methods: {
            submitForm() {
                alert('Formulario enviado');
            },
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
        justify-content: space-between; /* Espacia los elementos hijos */
        align-items: center; /* Alinea verticalmente los elementos */
        padding: 10px; /* Ajusta el espacio interno si es necesario */
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
        flex-direction: column; /* Alinea los elementos en una columna */
    }

    .profile-picture {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        margin-right: 15px;
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
        justify-content: space-between; /* Espacio igual entre las cartas */
        flex-wrap: wrap; /* Permite que las cartas se muevan a la siguiente línea si no caben en una sola línea */
    }

    .card-account {
        
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        transition: transform 0.3s;
        color: black;
        flex: 1 1 30%; /* Cada carta ocupará aproximadamente el 30% del ancho del contenedor, y se ajustará según sea necesario */
        margin: 10px; /* Espacio alrededor de cada carta */
        border: 1px solid #ddd; /* Borde alrededor de las cartas */
        border-radius: 8px; /* Bordes redondeados */
        overflow: hidden; /* Asegura que el contenido no se desborde fuera del borde de la carta */
    }

    .card-content-account {
        color: black;
        padding: 15px; /* Espacio interno dentro de la carta */
    }

    .button-container-account {
        text-align: right; /* Alinea los botones a la derecha */
    }

    .main-logo-account{
        margin-bottom: 10px;
        width: 250px;
        height: 80px;
    }

</style>
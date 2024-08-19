<template>

    <div class="container main">

        <header class="profile-header">
                <div class="header-section">
                    <router-link to ="/">
                        <h1 class="page-title">InstaHome</h1>
                    </router-link>
                </div>
                <div class="button-container">
                    <button class="btn btn-primary" v-if="!premium"> Hazte Premium </button>
                </div>
                <div class="profile-main">
                    <img src="./media/nah_id_win.jpg" alt="Foto de perfil" class="profile-picture">
                    <div class="profile-info">
                        <h2>Nombre del Usuario</h2>
                        <p>correo@ejemplo.com</p>
                    </div>
                </div>
        </header>

        <section>
            <div class="account-propieties">
                <h1 class="main-title">Tus propiedades</h1>
                <div class="card-container">
                    <div class="card">
                        <img src="./media/terreno_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content">
                            <h3 class="letter">Propiedad 5</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                    <div class="card">
                        <img src="./media/casa_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content">
                            <h3 class="letter">Propiedad 6</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                    <div class="card">
                        <img src="./media/casa_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content">
                            <h3 class="letter">Propiedad 6</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                    <div class="card">
                        <img src="./media/casa_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content">
                            <h3 class="letter">Propiedad 6</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                    <div class="card">
                        <img src="./media/terreno_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content">
                            <h3 class="letter">Propiedad 7</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                    <div class="card">
                        <img src="./media/dpto_stock.jpg" alt="Imagen de la propiedad">
                        <div class="card-content">
                            <h3 class="letter">Propiedad 8</h3>
                            <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                            <p class="letter">Valor:"Texto de ejemplo"</p>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>      
    </div>
</template>
<script>

    export default {
        data() {
            return {
                premium: false
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
    .main-title{
        margin-left: 700px;
        align-items: center;
        padding-top: 15px;
        color: black;
    }
    .profile-header {
        padding: 10px 20px;
        background-color: #f5f5f5;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .page-title {

        color: black;
    }

    .btn-primary {
        align-items: right;
        background-color: #6d997a;
        border: none;
        border-radius: 4px;
        color: white;
        cursor: pointer;
        padding: 10px 20px;
        text-align: center;
    }

    .btn-primary:hover {
        background-color: #42282c;
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

    .card-container{

        display: flex;
        flex-wrap: wrap; /* Permite que las cartas se envuelvan en múltiples filas si es necesario */
        justify-content: center; /* Centra las cartas en la fila */
        gap: 20px; /* Espacio entre las cartas */
        box-sizing: border-box;
        align-items: center;
        background-color: #f8f6f6f3;
        flex-direction: column;
        min-width: 300px;
        max-width: 1850px;
        width: 100%;
        margin: 0px 10px 0px 10px;
        min-height: 500px;
        max-height: 1000px;
        height: 100%;
        border-radius: 10px;

    }

    .card{

        background-color: #f8f6f691;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        margin: 20px;
        max-width: 300px;
        overflow: hidden;
        transition: transform 0.3s;
        flex: 1 1 auto;
    }

    .card:hover{
        transform: scale(1.05);
    }

    .card img{

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

    .main-title{

        color: black;
    }

    .button-container {
        text-align: right; /* Alinea los botones a la derecha */
    }
</style>
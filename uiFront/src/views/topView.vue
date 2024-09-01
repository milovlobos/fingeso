<template>

    <div class="container main">

        <header>
            <section class="header-section-publish">
                <router-link to="/">
                    <img class="main-logo-publish"src="./media/logo.png">
                </router-link>
            </section>
        </header>

        <div class="content"><!--Seccion que indica la navegacion entre las distintas secciones de la vista-->
            <div class="card">
                <img src="./media/dpto_stock.jpg" alt="Imagen de la propiedad">
                <div class="card-content">
                    <h3 class="letter">{{ propertyToPromote.propertyName }}</h3>
                    <p class="letter">{{ propertyToPromote.propertyDescription }}</p>
                    <p class="letter">{{ propertyToPromote.propertyPrice }}</p>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" >Ver mas</button>
                </div>
            </div>
        </div>

        <div class="content2" id="personal-form" @submit.prevent="handleSubmit"> <!--Seccion de rellenado de datos-->
        </div>
    </div>

</template>

<script>

    import mainComponent from '../components/mainComponent.vue';
    import logoComponent from '../components/logoComponent.vue';

    export default {
        components: {
            mainComponent,
            logoComponent
        },
        data(){

            return{

                propertyToPromote:[],
            }

        },
        mounted(){

            const property = JSON.parse(sessionStorage.getItem('propertyToPromote'));
            this.propertyToPromote = property;

        },
        methods:{


        }
    }

</script>


<style>

    .main{

        display: flex;
        justify-content: center;
        align-items: center;
        background: linear-gradient(45deg, #ded1b6, #ded1b6, #6ca19e, #6d997a);
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
        height: auto; 
        min-height: 100vh;
    }

    .content {
        box-sizing: border-box;
        min-width: 500px;
        max-width: 500px;
        width: 100%;
        margin: 0px 10px;
        min-height: 500px;
        max-height: 550px;
        height: 100%;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch; /* Asegura que los elementos hijos ocupen todo el ancho disponible */
    }

    .content2{

        box-sizing: border-box;
        align-items: left;
        background-color: #f8f6f6f3;
        flex-direction: column;
        min-width: 300px;
        max-width: 1000px;
        width: 100%;
        margin: 0px 10px 0px 10px;
        min-height: 500px;
        max-height: 550px;
        height: 100%;
        border-radius: 10px;
        display: flex; 
        justify-content: flex-start; 
    }

    .main-logo-publish{
        margin-bottom: 10px;
        width: 250px;
        height: 80px;

    }
    
    .colum-content1 {
        width: 300px; 
        height: 300px;
        padding: 0;
        margin: 0; 
    }

    .nav {
        width: 100%; 
        padding: 0; 
        margin: 0; 
        list-style: none; 
    }

    .nav-item {
        width: 100%; 
    }

    .nav-link {
        display: block; 
        width: 100%; 
        padding: 10px; 
        text-align: left; 
        color: #42282c; 
        text-decoration: none;
        border-radius: 10px;
    }

    .nav-link.active {
        background-color: #6d997a; 
        color: white; 
        width: 100%;
    }

    .btn-primary{
    
        padding-bottom: 10px;
        width: 100%;
    }

    form.d-flex {
        margin-top: auto;
    }

    form {
        width: 100%;
    }

    .form-label {
        margin-bottom: 0.5rem; 
    }

    .form-control, .form-select {
        margin-left: 10px;
        margin-bottom: 1rem; 
    }

    .row.g-3 {
        margin-top: 10px;
        display: flex;
        flex-wrap: wrap;
    }

    .row.g-3 .col-md-6, 
    .row.g-3 .col-md-4, 
    .row.g-3 .col-md-2 {
        box-sizing: border-box;
    }

    .form-check {
        display: flex;
        align-items: center;
    }

    .form-label{
        margin-left: 10px;
        color: black;
    }

    header {
        position: absolute; 
        top: 0; 
        left: 0; 
        width: 100%; 
        z-index: 1000; 
        padding: 10px; 
        box-sizing: border-box; 
        margin-left: 25px;
    }

    .header-section-publish {
        display: flex;
        justify-content: flex-start;
    }

    .page-title {
        color: black;
        margin: 0; 
        font-size: 2rem; 
    }

    a {
        text-decoration: none; 
    }

    .nav-link {
        text-decoration: none; 
    }

    .form-instruction{

        color: black;
        margin-left: 315px;
    }

    .btn-primary:disabled {
        background-color: #6ca19e; 
        border-color: #c0c0c0;
        color: #6c757d; 
        cursor: not-allowed; 
    }

    .progress-bar{
        transition: width 0.8s ease, background-color 0.3s ease;
        background-color: #42282c;
    }
    .progress-bar-full{
    
        background-color: #6ca19e;
    }
    .form-check-label{

        color:black;
    }
    .col-form-label{

        color: black;
    }

    .card-container {
        margin-top: 70px;
        display: flex;
        justify-content: space-between; 
        flex-wrap: wrap; 
    }

    .card {
        color: black;
        flex: 1; /* La carta toma todo el espacio disponible */
        margin: 10px 0; /* Añade márgenes verticales */
        border: 1px solid #ddd;
        border-radius: 8px;
        overflow: hidden;
        display: flex;
        flex-direction: column;
    }

.card img {
    width: 100%;
    height: auto;
}

.card-content {
    color: black;
    padding: 15px;
    flex: 1; /* Asegura que el contenido se expanda para llenar el espacio */
}

.card-content h3 {
    text-decoration: underline;
}

    .check-pay{

        margin-left: 10px;
    }

    .final-instructions{

        margin-top: 20px;
    }

    .final-publish {

        margin-top: auto; 
    }

    .card-container{

        margin-right: 3px;
    }

    .date-input-publish{

        margin-left: 8px;
    }

    .content-premium1 {
        align-items: center; /* Centra verticalmente la imagen y el texto */
    }

    .content-premium1 img {
        max-width: 400px; /* Ajusta el ancho máximo de la imagen según sea necesario */
        height: auto; /* Mantiene la proporción de la imagen */
        margin: 0 auto;/* Espacio entre la imagen y el texto */

    }

    .content-premium1 h1 {
        color: black;
        text-align: center;
        margin-top: 10px; /* Elimina el margen predeterminado del h1 */
    }

    .content-premium2 {
        text-align: center;
    }

    .content-premium2 img {
        max-width: 300px; /* Ajusta el ancho máximo de la imagen según sea necesario */
        height: auto; /* Mantiene la proporción de la imagen */
        margin-left: 10px;
    }

    .content-premium2 h1 {
        color: black;
        text-align: center;
        margin-top: 10px; /* Elimina el margen predeterminado del h1 */
        margin-left: 60px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .container-card {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
    }
</style>
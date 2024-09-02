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
                <img :src="propertyToPromote.propertyPhotoURL ? propertyToPromote.propertyPhotoURL : 'https://www.webempresa.com/foro/wp-content/uploads/wpforo/attachments/3200/318277=80538-Sin_imagen_disponible.jpg'" alt="Imagen de la propiedad">
                <div class="card-content-account">
                    <h3 class="letter">{{ propertyToPromote.propertyName }}</h3>
                    <p class="letter">Direccion: {{ propertyToPromote.propertyDress }}</p>
                    <p class="letter">Precio: ${{ propertyToPromote.propertyPrice }}</p>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" @click="viewDate()">Ver más</button>
                </div>
            </div>
        </div>

        <div class="modal fade" id="propertyModal" tabindex="-1" aria-labelledby="propertyModalLabel" aria-hidden="true"><!--Componente de despliegue de los detalles de la propiedad-->
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="propertyModalLabel">Detalles de la promocion en el Top diario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <img :src="propertyToPromote.propertyPhotoURL ? propertyToPromote.propertyPhotoURL : 'https://www.webempresa.com/foro/wp-content/uploads/wpforo/attachments/3200/318277=80538-Sin_imagen_disponible.jpg'" alt="Imagen de la propiedad">
                        <h2 class="modal-property" id="modalTitle">{{ propertyToPromote.propertyName }}</h2>
                        <h4 class="title-modal-description" id="modalDescription">Descripcion:</h4>
                        <p id="modalDescription">{{ propertyToPromote.propertyDescription }}</p>
                        <h4 class="title-modal-description" id="modalDescription">Caracteristicas:</h4>
                        <p id="modalValue">Precio: ${{ propertyToPromote.propertyPrice }}</p>
                        <p id="modalValue">Metros cuadrados: {{ propertyToPromote.propertyMeter2 }}</p>
                        <p id="modalValue">Direccion: {{ propertyToPromote.propertyDress }}</p>
                        <p id="modalValue">Disponible hasta: {{ propertyToPromote.propertyEnd_Date }}</p>
                        <h3 id="modalTitle" v-if="this.selectedDate==null">Fecha seleccionada invalida</h3>
                        <h3 id="modalTitle" v-if="this.selectedDate!=null">Dia en el top: {{ promotedDate }}</h3>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" @click="promoteProperty()" :disabled="this.selectedDate==null">Confirmar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="content2"> <!--Seccion de rellenado de datos-->

            <div class="calendar-container">
                <vue-cal
                    :disable-views="['years', 'year', 'month','day']"
                    @cell-click="onDateSelect"
                    :selected-date= today
                    :disabled-past="true"
                    :disabled-dates="dateNoDispo"
                    locale="es"
                ></vue-cal>
            </div>

        </div>
    </div>

</template>

<script>

    import mainComponent from '../components/mainComponent.vue';
    import logoComponent from '../components/logoComponent.vue';
    import VueCal from 'vue-cal';
    import 'vue-cal/dist/vuecal.css';
    import axios from 'axios';
    
    function redirectMain(){

        window.location.href = '/account';
    }

    export default {
        
        components: {
            mainComponent,
            logoComponent,
            VueCal,
        },
        data(){

            return{

                propertyToPromote:[],
                dateNoDispo:[],
                selectedDate: null,
                promotedDate: null,
                today: new Date(),
            }

        },
        mounted(){

            const property = JSON.parse(sessionStorage.getItem('propertyToPromote'));
            this.propertyToPromote = property;
            
            const noDispo = JSON.parse(sessionStorage.getItem('dateNoDispo'));
            this.dateNoDispo = noDispo;
        },
        methods:{

            onDateSelect(date) {
                this.selectedDate = date;
                this.promotedDate = date.date.toISOString().split('T')[0];
            },
            async promoteProperty(){
                
                const param = {

                    "date":this.promotedDate,
                    "PropertyId":this.propertyToPromote.id,
                };
                try{
                    
                    const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + "api/promoted/top_10",null,{params:param});
                    console.log(respuesta.data);
                    if(respuesta.data == 1){

                        alert("Tu publicacion se ha promocionado con exito!");
                        const param ={

                            "date" : new Date().toISOString().split('T')[0],
                        };
                        try{

                            const respuesta = await axios.get(import.meta.env.VITE_BASE_URL + "api/promoted/getTop10",{params:param});
                            sessionStorage.setItem('top10',JSON.stringify(respuesta.data));

                        }catch(error){

                            console.log("Error en axios: Top 10",error);

                        }
                        redirectMain();

                    } 
                    if(respuesta.data == 0){

                        alert("Tu publicacion ya esta listada en el top");
                        redirectMain();
                    }
                    
                }catch(error){

                    console.log("Error en axios: Promocion de la propiedad",error);
                }
            },
            viewDate(){

                const date1 = new Date();
                const date2 = new Date(this.promotedDate);
                const date3 = new Date(this.propertyToPromote.propertyEnd_Date);
                if(this.promotedDate in this.dateNoDispo){

                    alert('Dia lleno');
                    this.promotedDate = null;
                    this.selectedDate = null;
                }

                if(date2 < date1){

                    alert('No se puede promocionar en fechas anteriores a la actual');
                    this.promotedDate = null;
                    this.selectedDate = null;
                }

                if(date2 > date3){

                    alert('No se puede promocionar en fechas luego del termino de la publicacion'); 
                    this.promotedDate = null;
                    this.selectedDate = null;
                }


            },
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
        background-color: #f8f6f691;
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

    .calendar-container {
        width: 100%; /* Ajustar al 100% del ancho del contenedor padre */
        width: 1000px; /* O cualquier tamaño máximo que prefieras */
        height: 550px; /* Altura específica para el contenedor */
        margin: auto;
        border: 1px solid #ddd; /* Opcional: Añadir un borde para visualizar el contenedor */
        overflow: hidden; /* Asegurarse de que el contenido no desborde el contenedor */
    }

    .custom-calendar {
        height: 100%; /* Asegurar que el calendario ocupe todo el alto del contenedor */
    }

    .vuecal__body {
        height: 550px; /* Ajustar la altura del cuerpo del calendario */
        
    }

    .vuecal__body hover{
        height: 550px; /* Ajustar la altura del cuerpo del calendario */
    
    }

    .vuecal__header {
        color: black;
        margin-bottom: 42px;
    }

    .modal-content{

        color: black;
    }

    .modal-body img{

        border-radius: 5%;
    }
    
    .modal-property{
        padding-top: 3px;
    }

    .title-modal-description{

        margin-bottom: 8px;
        border-bottom: 1px solid #ddd;

    }
</style>
<template>

    <div class="container main">

        <header>
            <section class="header-section-publish">
                <router-link to="/">
                    <img class="main-logo-publish"src="./media/logo.png">
                </router-link>
            </section>
        </header>

        <div class="content">
            <div class="colum-content1">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a :class="{'nav-link': true, 'active': activeItem === 1}" @click="setActive(1)">Datos personales</a>
                    </li>
                    <li class="nav-item">
                        <a :class="{'nav-link': true, 'active': activeItem === 2}" @click="setActive(2)">Datos de la vivienda</a>
                    </li>
                    <li class="nav-item">
                        <a :class="{'nav-link': true, 'active': activeItem === 3}" @click="setActive(3)">Metodos de pago</a>
                    </li>
                    <li class="nav-item">
                        <a :class="{'nav-link': true, 'active': activeItem === 4}" @click="setActive(4)">Nivel de verificacion</a>
                    </li>
                </ul>
            </div>
            <form class="d-flex">
                <button class="btn btn-primary" @click.prevent="prevSection()" id="prevButton">Atras</button>
                <button class="btn btn-primary" @click.prevent="nextSection()" id="nextButton">Siguiente</button>
            </form>
        </div>

        <div class="content2" id="personal-form" @submit.prevent="handleSubmit">
            <div class="progress">
                <div class="progress-bar":class="{ 'progress-bar-full': progressWidth === '100%' }"  role="progressbar" :style="{ width: progressWidth }" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <form class="row g-3"v-if="progress==25">
                <div class="col-md-6" >
                    <label for="inputusername4" class="form-label">Nombre Completo</label>
                    <input type="username" class="form-control" id="inputusernamel4">
                </div>
                <div class="col-md-6">
                    <label for="inputRut4" class="form-label">Rut</label>
                    <input type="text" class="form-control" id="inputRut4">
                </div>
                <div class="col-12">
                    <label for="inputAddress" class="form-label">Direccion</label>
                    <input type="text" class="form-control" id="inputAddress" placeholder="Calle falsa #123">
                </div>
                <div class="col-12">
                    <label for="inputAddress2" class="form-label">Numero de telefono</label>
                    <input type="text" class="form-control" id="inputAddress2" placeholder="+56 9 1234 56789">
                </div>
                <div class="col-md-6">
                    <label for="inputRegion" class="form-label">Region</label>
                    <select id="inputRegion" class="form-select">
                        <option selected>...</option>
                        <option>Arica y parinacota</option>
                        <option>Tarapaca</option>
                        <option>Antofagasta</option>
                        <option>Atacama</option>
                        <option>Coquimbo</option>
                        <option>Valparaiso</option>
                        <option>O'Higgins</option>
                        <option>Maule</option>
                        <option>Ñuble</option>
                        <option>Araucania</option>
                        <option>Los rios</option>
                        <option>Los lagos</option>
                        <option>Aysen</option>
                        <option>Magallanes</option>
                        <option>Antartica</option>
                        <option>Isla de pascua</option>
                        <option>Metropolitana</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="inputState" class="form-label">Comuna</label>
                    <input type="text" class="form-control" id="inputCity">
                </div>
                <div class="col-md-2">
                    <label for="inputZip" class="form-label">Codigo Postal</label>
                    <input type="text" class="form-control" id="inputZip">
                </div>
                <div class="col-12">
            </div>
            </form>

            <form class="row g-3" v-if="progress==50">
                <div class="row">
                    <div class="col-md-6">
                        <label for="inputusername4" class="form-label">Tipo de propiedad*</label>
                        <select v-model="property.propertyType"id="inputRegion" class="form-select">
                            <option selected>...</option>
                            <option>Departamento</option>
                            <option>Casa</option>
                            <option>Terreno</option>
                        </select>
                    </div>
                <div class="col-md-6">
                    <label for="inputRut4" class="form-label">Direccion*</label>
                    <input v-model="property.propertyDirection"type="text" class="form-control" id="inputRut4">
                </div>
                <div class="col-md-6">
                    <label for="inputAddress" class="form-label">Metros cuadrados*</label>
                    <input v-model="property.propertyM2" type="text" class="form-control" id="inputAddress" >
                </div>
                <div class="col-md-6">
                    <label for="inputAddress" class="form-label">Nombre en publicacion*</label>
                    <input v-model="property.propertyName"type="text" class="form-control" id="inputAddress" >
                </div>
                <div class="col-md-6">
                    <label for="inputRegion" class="form-label">Region</label>
                    <select id="inputRegion" class="form-select">
                        <option selected>...</option>
                        <option>Arica y parinacota</option>
                        <option>Tarapaca</option>
                        <option>Antofagasta</option>
                        <option>Atacama</option>
                        <option>Coquimbo</option>
                        <option>Valparaiso</option>
                        <option>O'Higgins</option>
                        <option>Maule</option>
                        <option>Ñuble</option>
                        <option>Araucania</option>
                        <option>Los rios</option>
                        <option>Los lagos</option>
                        <option>Aysen</option>
                        <option>Magallanes</option>
                        <option>Antartica</option>
                        <option>Isla de pascua</option>
                        <option>Metropolitana</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="inputState" class="form-label">Precio*</label>
                    <input v-model="property.propertyPrice"type="text" class="form-control" id="inputCity">
                </div>
                <div class="col-12">
                    <label for="inputDescription" class="form-label">Descripcion*</label>
                    <textarea v-model="property.userDescription"id="inputDescription" class="form-control" rows="3"></textarea>
                </div>
            </div>
            </form>

            <form class="row g-3" v-if="progress==75">
                <div class="row">
                    <fieldset class="row mb-3">
                        <div class="col-md-6">
                            <label for="inputusername4" class="form-label">Tipo de publicacion</label>
                            <select id="inputRegion" class="form-select">
                                <option selected>...</option>
                                <option>Venta</option>
                                <option>Arriendo</option>
                            </select>
                        </div>
                        <div class="check-pay">
                        <legend class="col-form-label">Tipo de pago</legend>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="gridCheck1">
                                    <label class="form-check-label" for="gridCheck1">
                                    Transferencia
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="gridCheck1">
                                    <label class="form-check-label" for="gridCheck1">
                                    Efectivo
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="gridCheck1">
                                    <label class="form-check-label" for="gridCheck1">
                                    Debito o credito
                                    </label>
                                </div>
                            </div>
                        </div>
                    <div class="final-instructions">
                        <div class="col-12">
                            <label for="inputDescription" class="form-label">Indicaciones</label>
                            <textarea id="inputDescription" class="form-control" rows="3"></textarea>
                        </div>
                    </div>
                    </fieldset>
                </div>
            </form>

            <form class="row g-3" v-if="progress==100">
                <div class="card-container">
                    <div class="card">

                        <div class="card-content">
                            <h3 class="letter">Tier 1</h3>
                            <p class="letter">Descripción de la verificacion: "Texto de ejemplo"</p>
                            <p class="letter">Valor: "Texto de ejemplo"</p>
                            <router-link to="/buy">
                                <button class="btn btn-primary">Comprar</button>
                            </router-link>
                        </div>
                    </div>
                    <div class="card">

                        <div class="card-content">
                            <h3 class="letter">Tier 2</h3>
                            <p class="letter">Descripción de la verificacion: "Texto de ejemplo"</p>
                            <p class="letter">Valor: "Texto de ejemplo"</p>
                            <router-link to="/buy">
                                <button class="btn btn-primary">Comprar</button>
                            </router-link>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <h3 class="letter">Tier 3</h3>
                            <p class="letter">Descripción de la verificacion: "Texto de ejemplo"</p>
                            <p class="letter">Valor: "Texto de ejemplo"</p>
                            <router-link to="/buy">
                                <button class="btn btn-primary">Comprar</button>
                            </router-link>
                        </div>
                    </div>
                </div>
            </form>
            <div class="final-publish">
                <button class="btn btn-primary" type="submit" @click="publish" :disabled="!isPublishEnabled">Publicar</button>
            </div>
        </div>
    </div>

</template>

<script>
    import axios from 'axios';
    import mainComponent from '../components/mainComponent.vue'

    function redirectMain(){
        window.location.href = '/';
    }   

    export default{
        components: {
            mainComponent
        },
        data(){

            return{

                activeItem: 1,
                progress: 25,
                property: {
                    userID: '',
                    propertyType: '',
                    propertyName: '',
                    propertyDirection: '',
                    propertyPrice: '',
                    userDescription: '',
                    propertyM2: '',
                    propertyImg: '',
                },
            }
        },
        computed: {
            progressWidth() {
                return `${this.progress}%`;
            },
            isPublishEnabled() {
                return this.progress === 100;
            }
        },
        methods:{
            async publish(){

                if(this.propertyType == null || this.propertyName == null || this.propertyDirection == null || this.propertyPrice == null || this.userDescription == null || this.propertyM2 == null || this.propertyImg == null){

                    alert("Por favor complete todos los campos obligatorios (*)");
                    return;
                }

                const property = {

                    userID: this.userID,
                    propertyType: this.propertyType,
                    propertyName: this.propertyName,
                    propertyDirection: this.propertyDirection,
                    propertyPrice: this.propertyPrice,
                    userDescription: this.userDescription,
                    propertyM2: this.propertyM2,
                    propertyImg: this.propertyImg,
                };
                try{

                    const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + "api/inmueble/create",property);

                    if(respuesta.data != null){

                        alert("Publicacion exitosa");
                        redirectMain();
                    }

                }catch(error){

                    console.log(error);
                    alert("Fallo en la conexion con el servidor");
                }

            },
            setActive(index) {
                this.activeItem = index;
            },
            prevSection(){
                if (this.activeItem > 1) {
                    this.activeItem--;
                    this.progress = Math.max(this.progress - 25, 0);
                }
            },
            nextSection(){
                if (this.activeItem < 4) {
                    this.activeItem++;
                    this.progress = Math.min(this.progress + 25, 100);
                }
            },
            previewImage(event) {
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = (e) => {
                    this.imageSrc = e.target.result;
                };
                    reader.readAsDataURL(file);
                } else {
                    this.imageSrc = null;
                }
            },
            handleSubmit() {
                
                axios.post(import.meta.env.VITE_BASE_URL + "api/inmueble/create", this.form)
                .then(response => {
                    console.log('Datos enviados exitosamente:', response.data);
                })
                .catch(error => {
                    console.error('Error al enviar datos:', error);
                });
            }
            
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

    .content{

        box-sizing: border-box;
        align-items: left;
        background-color: #f8f6f6f3;
        flex-direction: column;
        min-width: 300px;
        max-width: 300px;
        width: 1%;
        margin: 0px 10px 0px 10px;
        min-height: 500px;
        max-height: 550px;
        height: 100%;
        border-radius: 10px;
        display: flex; 
        justify-content: flex-start; 
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
        flex: 1 1 30%; 
        margin: 10px; 
        border: 1px solid #ddd; 
        border-radius: 8px; 
        overflow: hidden; 
    }

    .card img {
        width: 100%;
        height: auto;
    }

    .card-content {
        color: black;
        padding: 15px; 
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

</style>